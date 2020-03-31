# Ristinolla

Harjoitustyö kursille ohjelmistotekniikka. Sovelluksella voi pelata ristinollaa kaveria tai vihollista vastaan yhdellä koneella. Käyttäjä voi itse päättää peliruudukon koon ja kuinka pitkä voittirivin tulee olla.

## Huom

* Pelin käyttöliittymä toimii valikon osalta, mutta pelin siirroista tulostuu tiedot syötteenä.

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


### Ohjelman suorittaminen

 Sovellusta voi käyttää terminaalin kautta komennolla:
 ```
  mvn compile exec:java -Dexec.mainClass=ristinollaapp.main.Main
 ``` 

### Jar-ohjelma

 Ohjelman luominen
 ```
 mvn package
 ```

 Jar ohjelman suorittaminen target-kansion sisällä
 ```
 java -jar Ristinolla-1.0-SNAPSHOT.jar
 ```

## Dokumentaatiota

* [Vaatimusmäärittely](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Työaikakirjanpito](https://github.com/elmanevala/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

