# Sovelluksen arkkitehtuuria

## Luokka/pakkauskaavio

* Kaavio on alustava ja siitä puuttuu TOP-listoja pitävä tietokanta, sillä en ole vielä täysin varma, miten toteutan ne.


![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/luokkapakkauskaavio.jpg)

## Sekvenssikaavio voitolle

* Sovelluksen toimintalogiikka, kun kayttäjä tekee voittoavan siirron.

![alt-text](https://raw.githubusercontent.com/elmanevala/ot-harjoitustyo/master/dokumentaatio/voitonSekvessikaavio.png)

Käyttöliittymä kutsuu GameLogic-luokkaa, jossa on tietoja käynnissä olevasta pelistä. Ensin peliluokasta tarkistetaan, että paikka on tyhjä ja luokka palauttaa arvon true. Sitten pelilogiikkassa vaihdetaan vuoroa ja se palauttaa uuden vuoron käyttöliittymään. Nyt käyttöliittymä voi päivittää näkymän uudella vuorolla. Tarkistetaan vielä onko peli päättänyt. Koska siirto oli voittava, palauttaa pelilogiikka arvon true ja käyttöliittymä luo uuden näkymän.
