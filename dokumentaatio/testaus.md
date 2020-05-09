# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkötestein JUnitilla että manuaalisesti järjestelmätasolla.

## Yksikkötestaus

Automatisoidut testit testaavat sekä tiedon tallennusta, että pelilogiikan toimintaa.

### Sovelluslogiikka
Pakkauksen ristinollaapp.domain toimintaa testaa luokat GameLogicTest ja TopListLogicTest. GameLogicTest-luokka testaa ristinollapelin eri vaiheita ja mahdollsia pelitilanteita. TopListLogicTest testaa tiedon tallennusta pelien päättyessä ja sen lukemista tilastoja varten. TopListLogicTest käyttää apunaan myös dao-luokkaa, koska sen avulla on helppo lisätä tietoja tietokantaan ilman, että tarvitsee luoda uusia TopListLogic-luokkia.
Näin testit pysyvät huomattavasti selkeämpinä.

### Dao
Pelkkää tiedon tallentamista ja lukemista testaa luokka TopListDaoTest.

Kaikki tiedon tallenusta testaavat luokat hakevat tallennukseen käytetyn tiedoston nimen sovelluksen juuressa sijaitsevasta config.properties-tiedostosta.

### Testikattavuus

Sovelluksen testauksen rivikattavuus on 88% ja haaraumakattavuus on 89%.
 
![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/testikattavuus.png)
 
## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Toiminnallisuudet

Kaikki vaatimusmäärittelyn toiminnallisuudet on testattu, myös tilanteet jossa käyttäjä syöttää virheellisiä syötteitä, kuten tyhjän nimimerkin tai liian ison ruudukkokoon.

## Sovellukseen jääneet laatuongelmat

Sovelluksessa on muutamia liian pitkiä metodeja, esimerkiksi molemmat diagonaalin voittosuoran tarkastavat metodit ovat yli 20 riviä pitkiä. Näiden metodien lyhentäminen olisi kuitenkin altistanut sovelluksen bugeille.

