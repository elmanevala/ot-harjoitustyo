# Sovelluksen arkkitehtuuria

## Rakenne

 Sovelluksen pakkausrakenne:

 ![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/pakkausrakenne.jpg)

## Käyttöliittymä

 Sovelluksen käyttöliittymä koostuu kolmesta kokonaisuudesta:
 * Peli ja siihen liittyvän valinnat
	* Aloitusnäkymä, jossa voi valita peliruudukon sekä voittosuoran koon
	* Pelinäkymä
 * Näkymä top-listoille ja mahdollisuus nimen lisäykseen top-listoille
	* Pelin jälkeinen näkymä näyttää voittajan
	* Jos pääsee listoille, tässä näkymässä myös mahdollisuus nimimerkin lisäykseen
	* Top-listanäkymä näyttää pienimmillä siirroilla voitetut pelit tietyllä ruudukon ja voittosuoran koolla.
 * Tilastonäkymä
	* Tilastonäkymä näyttää tilastoja pelatuista peleistä

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/kayttoliittyma.jpg)
 
 Jokainen näkymä on oma BorderPane-olio, joka asetetaan GameUi-luokassa luotuun päänäkymään.

 Käyttäjä syöttää käyttöliittymään tietoja esimerkiksi mihin hän asettaa seuraavan siirtonsa, mikä on käyttäjän nimimerkki ja mikä on haluttu peliruudukon koko.
 TopListLogic- sekä GameLogic-luokka käyttävät näitä tietoja pelin suorittamiseen ja tiedon tallennukseen. 

 Kaikki käyttäjän syöttämä tieto validoidaan, jotteivat väärät syötteet kaada sovellusta.

## Sovelluslogiikka

 Sovellus toimii kolmessa eri kerroksessa. Ensimmäinen kerros eli pakkaus ristinollaapp.ui sisältää käyttöliittymän koodin. Pakkaus ristinollaapp.domain sisältää sovelluksen sovelluslogiikan
 ja viimeinen pakkaus eli ristinollaapp.dao sisältää tietojen pysyväistalletuksen.

 Käyttäjän toiminnan mukaan ui-kerroksesta siirtyy metodien avulla tietoja ja käskyjä sovelluslogiikkaan, joka mm. ylläpitää käynnissä olevan ristinollapelin tilannetta kaksiulotteisessa taulukossa. Kun pelilogiikassa huomataan, että peli 
 on päättynyt top-listalogiikka tallettaa kyseisen pelin tietoja dao-pakkauksen tietokantaan. 

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/luokkapakkauskaavioUusi.jpg)

## Tietojen pysyväistalletus

 Pakkauksen ristinollaapp.dao luokka TopListDao huolehtii pelien tietojen talletuksesta.
 Pelin päätyttyä sen tiedot tallennetaan TopList tietokantatauluun. Jos peli ei pääse TopListalle, se tallennetaan ilman voittajan nimeä. Tauluun tallennetaan tiedot muodossa
 (id INTEGER PRIMARY KEY, name TEXT NOT NULL, gridsize INTEGER, rowsize INTEGER, moves INTEGER).

 Testeille on oma tietokanta, jotta testit eivät vaikuttaisi pelin Top-listoihin tai tilastointiin.

### Tiedostot

 Käyttäjä voi itse päättää, minkä nimiseen tiedostoon pelit ja voittajat tallenetaan.

 Sovellukseen juureen on sijoitettu tiedosto config.properties, jonka sisältö on seuraava:

 ```
 topListsFile=toplists.db
 testFile=test.db
 ```

 "test.db" ja "toplists.db" -kohtia muuttamalla voi vaihtaa tiedostoja, johon sovellus tallentaa tietoja.

## Sekvenssikaavioita

* Muutamia sovelluksen toiminnalle oleellisia sekvenssikaavioita.

### Voitto

* Sovelluksen toimintalogiikka, kun kayttäjä tekee voittavan siirron.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/voitonSekvenssikkaavio.png)

Käyttöliittymä kutsuu GameLogic-luokkaa, jossa on tietoja käynnissä olevasta pelistä. Ensin peliluokasta tarkistetaan, että paikka on tyhjä ja luokka palauttaa arvon true. Sitten pelilogiikkassa vaihdetaan vuoroa ja se palauttaa uuden vuoron käyttöliittymään. Nyt käyttöliittymä voi päivittää näkymän uudella vuorolla. Tarkistetaan vielä onko peli päättänyt. Koska siirto oli voittava, palauttaa pelilogiikka arvon true ja käyttöliittymä luo uuden näkymän.

### Nimi TOP-listalle

* Sovelluksen toimintalogiikka, kun käyttäjä tallentaa nimimerkkinsä TOP-listalle.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/nimenTallennusSekvenssikkaavio.png)

Käyttöliittymä kutsuu top-listalogiikkaa lisämään parametrina annetun nimen tietokantaan. Top-listalogiikka puolestaan kutsuu TopListDao-luokkaa ja asettaa nimen tietokantaan. Muut parametriarvot logiikka on saanut jo
pelin päätyttyä konstruktorissa. Käyttöliittymä päivittää näkymää ja samalla kutsuu myös logiikkaa ja daoa, saadakseen top-listan muodostamiseen tarvittavat tiedot.

### Tilastojen tarkastelu

* Sovelluksen toimintalogiikka, kun käyttä tarkastelee tilastoja.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/tilastonakymaSekvenssikaavio.png)

Käyttäjä painaa Pelien tilastoihin -nappulaa ja käyttöliittymä luo uuden näkymän. Uutta näkymään varten tarvitaan tallennettuja tietoja peleistä, joten luodaan uusi TopListLogic-luokka joka puolestaan luo uuden
TopListDao-luokan. StatsUi-luokkaa luodessa haetaan monta kertaa tietoja TopListLogic- ja TopListDao-luokkia käyttämällä. Koska käskyjä on hyvin monta ja ne toimivat kaikki samalla tavalla, selkeyden vuoksi ne ovat sekvenssikaaviossa kuvattu yhdellä haulla. Tietokannasta saadut tiedot asetetaan Stats-näkymään listEntry metodia hyödyntämällä metodissa stats. Lopulta näkymä asetetaan päänäkymään käyttäjän tarkasteltavaksi.


### Ohjelman rakenteeseen jääneet heikkoidet

## Käyttöliittymä

 Sovellus luo uusia näkymiä aina, kun sellaiseen siirrytään. Javan roskankerääjä kerää ne pois, mutta olisi ehkä käytännöllisempää uusien näkymien luonnin sijaan päivittää jo olemassa olevia näkymiä.

## Dao  
 Sovellus luo uuden yhteyden tietokantaan käytännössä jokaisessa metodissa.
