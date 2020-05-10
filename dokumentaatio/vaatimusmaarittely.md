# Vaatimusmäärittely

## Sovelluksen tarkoitus 

Sovellus on perinteinen Ristinolla, jossa kaksi pelaajaa voi pelata toisiaan vastaan. Sovelluksessa on mahdollista valita ristinollaruudukon koko ja voittorivin pituus.
Jos pelaaja voittaa erityisen vähillä siirroilla, hän voi päästä TOP-listoille.

## Käyttäjät

Ainostaan yksi käyttäjärooli eli pelaajat. Top-listalle päästäessä valitaan aina uusi nimi.

## Käyttöliittymäluonnos

Sovellus koostuu viidestä eri näkymästä.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/kayttoliittyma.jpg)

* Aloitusnäkymässä voi kustomoida pelistä haluamansa kokoisen.
* Pelinäkymässä voi pelata valitulla ruudukolla ja voittosuoralla ristinollaa.
* Voittonäkymä ilmoittaa voitosta tai tasapelistä.
	* Tässä näkymässä myös mahdollisuus nimimerkin lisäykseen top-listoille.
* Top-listanäkymä näyttää voitot kaikista pienimmillä siirtomäärillä.
* Tilastonäkymä, jonne pääsee aloitusnäytöstä, näyttää pelien tilastoja.


## Perusversion toiminnallisuudet

* Käyttäjä voi kustomoida peliä :heavy_check_mark:
  * Käyttäjä voi valita ruudukon koon.
  * Käyttäjä voi voittavan rivin pituuden. 
* Käyttäjä voi pelata ristinollaa samalla koneella toisen pelaajan kanssa. :heavy_check_mark:
* Jos käyttäjä voittaa erityisen vähillä siirroilla, hän voi päästä TOP-listalle. :heavy_check_mark:
* Käyttäjä voi tarkastella päättyneen pelin TOP-listoja. :heavy_check_mark:
* Käyttäjä voi tarkastella pelin tilastoja. :heavy_check_mark:
  * Kuinka monta peliä on pelattu.
  * Mikä koko, voittosuora ja niiden yhdistelmä on suosituin.
  * Keskiverto siirtojen määrä tarvittu voittoihin


## Jatkokehitysideoita

Sovellusta voisi laajentaa mm. näillä tavoilla:

* Lisää kustomointia
  * Ristin ja nollan sijaan peliin voi valita eri symboleja
  * Pelin taustaväriä voi vaihtaa
  * Suoran sijaan voidaan tavoitella esimerkiksi L-muotoa
* Mahdollisuus turnaukseen
  * Pelien määrän voi valita
  * Voittaja ilmoitetaan vasta kaikkien pelien jälkeen
