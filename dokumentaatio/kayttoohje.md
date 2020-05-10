# Kayttöohje

[Lataa tiedosto](https://github.com/elmanevala/ot-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Sovellukseen juureen tulee sijoittaa tiedosto config.properties, jonka sisältö on seuraava:

```
topListsFile=toplists.db
testFile=test.db
```

"test.db" ja "toplists.db" -kohtia muuttamalla voi vaihtaa tiedostoja, johon sovellus tallentaa tietoja.


## Ohjelman käynnistäminen

 Jar ohjelman käynnistäminen
 ```
 java -jar ristinollaapp.jar
 ```

## Pelin kutsomointi

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/aloitusnaytto.png)

* Pelaaja voi valita peliruudukon koon ja voittosuoran pituuden.
* Kutsomoinnin rajoitukset näkyvät aloitusnäytössä: ruudukon koon tulee olla  3–7 ja sen on oltava voittosuoraa isompi.
* Aloitusnäytöstä voi myös siirtyä tarkastelemaan tilastoja.

## Pelaaminen

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/pelinaytto.png)

* Pelin voittaa se pelaaja, joka saa ensimmäisenä tehtyä voittosuoran.
* Vaikka peli kävisi kuumaksi, konetta ei saa vahingoittaa.

## Nimimerkin lisääminen

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/nimiListalle.png)

* Jos voittaa erityisen vähillä siirroilla, voi lisätä nimensä listaan.
* Nimi ei saa olla tyhjä. Sovellus huomauttaa, jos tätä yrittää.

## Listalla

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/listaNaytto.png)

* Nimen lisäämisen jälkeen pääsee tarkastelemaan top-listaa.
* Listan näkee myös, vaikka ei itse pääsisi listalle.
* Listalta pelaaja voi siirtyä alkuun ja aloittaa uuden pelin tai tarkastella tilastoja.
