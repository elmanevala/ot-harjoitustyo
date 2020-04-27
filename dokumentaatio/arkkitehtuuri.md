# Sovelluksen arkkitehtuuria

## Sovelluslogiikka

 Sovellus toimii kolmessa eri kerroksessa. Ensimmäinen kerros eli pakkaus ristinollaapp.ui sisältää käyttöliittymän koodin. Pakkaus ristinollaapp.domain sisältää sovelluksen sovelluslogiikan
 ja viimeinen pakkaus eli ristinollaapp.dao sisältää tietojen pysyväistalletuksen.

 Käyttäjän toiminnan mukaan ui-kerroksesta siirtyy metodien avulla tietoja ja käskyjä sovelluslogiikkaan, joka mm. ylläpitää käynnissä olevan ristinollapelin tilannetta. Kun pelilogiikassa huomataan, että peli 
 on päättynyt Top-listalogiikka tallettaa kyseisen pelin tietoja dao-pakkauksen tietokantaan. 

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/luokkapakkauskaavioUusi.jpg)

## Tietojen pysyväistalletus

 Pakkauksen ristinollaapp.dao luokka TopListDao huolehtii pelien tietojen talletuksesta. Tietokanta tallennetaan tiedostoon, jonka nimi on annettu pelilogiikassa parametrina.
 Pelin päätyttyä sen tiedot tallennetaan TopList tietokantatauluun. Jos peli ei pääse TopListalle, se tallennetaan ilman voittajan nimeä. Tauluun tallennetaan tiedot muodossa
 (id INTEGER PRIMARY KEY, name TEXT NOT NULL, gridsize INTEGER, rowsize INTEGER, moves INTEGER).

 Testeille on oma tietokanta, jotta testit eivät vaikuttaisi pelin Top-listoihin tai tilastointiin.

## Sekvenssikaavioita

### Voitto

* Sovelluksen toimintalogiikka, kun kayttäjä tekee voittavan siirron.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/voitonSekvenssikkaavio.png)

Käyttöliittymä kutsuu GameLogic-luokkaa, jossa on tietoja käynnissä olevasta pelistä. Ensin peliluokasta tarkistetaan, että paikka on tyhjä ja luokka palauttaa arvon true. Sitten pelilogiikkassa vaihdetaan vuoroa ja se palauttaa uuden vuoron käyttöliittymään. Nyt käyttöliittymä voi päivittää näkymän uudella vuorolla. Tarkistetaan vielä onko peli päättänyt. Koska siirto oli voittava, palauttaa pelilogiikka arvon true ja käyttöliittymä luo uuden näkymän.

### Nimi TOP-listalle

* Sovelluksen toimintalogiikka, kun käyttäjä tallentaa nimimerkkinsä TOP-listalle.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/nimenTallennusSekvenssikkaavio.png)


