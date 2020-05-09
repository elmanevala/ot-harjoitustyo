# Ristinolla

Harjoitustyö kursille ohjelmistotekniikka. Sovelluksella voi pelata ristinollaa kaveria tai vihollista vastaan yhdellä koneella. Käyttäjä voi itse päättää peliruudukon koon ja kuinka pitkä voittorivin tulee olla. Jos pelaaja voittaa erityisen vähillä siirroilla, hän pääsee TOP-5-listalle. Käyttäjä voi myös tarkastella pelien tilastoja.

## GitHub release

* [Ensimmäinen release](https://github.com/elmanevala/ot-harjoitustyo/releases/tag/viikko5)
* [Toinen release](https://github.com/elmanevala/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Testaus

 Testit suoritetaan komennolla:
 ```
  mvn test
 ```

 Testikattavuusraportti luodaan komennolla:
 ```
  mvn test jacoco:report
 ```

 Koodin luettavuutta voi testata komennolla:
 ```
 mvn jxr:jxr checkstyle:checkstyle
 ```

### Ohjelman suorittaminen

 Sovellusta voi käyttää terminaalin kautta komennolla:
 ```
  mvn compile exec:java -Dexec.mainClass=ristinollaapp.main.Main
 ``` 

### Jar-ohjelma

 Jarin generoiminen target-hakemistoon:
 ```
 mvn package
 ```

 Jar ohjelman suorittaminen target-kansion sisällä:
 ```
 java -jar Ristinolla-1.0-SNAPSHOT.jar
 ```

### Javadoc

 Javadoc luodaan komennolla
 ```
 mvn javadoc:javadoc
 ```


## Dokumentaatiota

* [Vaatimusmäärittely](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Työaikakirjanpito](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)
* [Arkkitehtuuria](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
* [Kayttöohje](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
* [Testausta](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

