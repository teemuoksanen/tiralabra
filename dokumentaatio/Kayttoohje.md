# Pakkaajan käyttöohje

## Asentaminen ja käynnistäminen

Lataa repositorion sisältö [ZIP-pakettina](https://github.com/teemuoksanen/tiralabra-huffman/archive/main.zip) tai kloonaa sisältö komennolla

`git clone git@github.com:teemuoksanen/tiralabra-huffman.git`

Siirry hakemistoon, jonne purit paketin tai kloonasit sisällön, ja käynnistä ohjelma komennolla

`gradle -q –console plain run`

Ohjelman voit sulkea sen päävalikosta komennolla __q__. 

*(Ohjelman lopullinen versio tulee saataville myös JAR-muodossa, jolloin ohjelma on yksinkertaisempi käynnistää.)*

## Tiedoston pakkaaminen

Voit pakata tiedoston valitsemalla päävalikossa komennon __1__. Anna pakattavan tiedoston nimi sekä tarvittaessa polku. Jos tiedosto sijaitsee samassa hakemistossa ohjelman kanssa, voit antaa pelkän tiedostonimen. Jos haluatkin palata päävalikkoon, jätä tiedoston nimi tyhjäksi ja paina *enter*.

Ohjelma pakkaa tiedoston samaan hakemistoon, missä alkuperäinen pakattava tiedosto sijaitsee. Pakatun tiedoston nimen perään lisätään pääte *.huff*. Esimerkiksi *teksti.txt* pakataan tiedostoksi *teksti.txt.huff*. Mikäli samanniminen tiedosto on jo olemassa, pakkaaminen ei onnistu. Siirrä tai poista tällöin kyseinen tiedosto ja yritä uudelleen.

## Pakatun tiedoston purkaminen ##

Voit purkaa Pakkaajalla pakatun tiedoston valitsemalla päävalikossa komennon __2__. Anna purettavan tiedoston nimi sekä tarvittaessa polku. Pakatun tiedoston pääte on *.huff*, ellet ole muuttanut nimeä pakkaamisen jälkeen. Jos tiedosto sijaitsee samassa hakemistossa ohjelman kanssa, voit antaa pelkän tiedostonimen. Jos haluatkin palata päävalikkoon, jätä tiedoston nimi tyhjäksi ja paina *enter*.

Ohjelma purkaa tiedoston samaan hakemistoon, missä pakattu tiedosto sijaitsee. Puretun tiedston varsinaiseen nimeen lisätään tunniste *-purettu* ja pääte *.huff* poistetaan. Esimerkiksi *teksti.txt.huff* puretaan tiedostoksi *teksti-purettu.txt*. Mikäli samanniminen tiedosto on jo olemassa, pakkaaminen ei onnistu. Siirrä tai poista tällöin kyseinen tiedosto ja yritä uudelleen.

## Asetukset ##

Mikäli haluat nähdä tilastotietoja pakatusta tiedostosta (pakkaamiseen kulunut aika, tiedostojen koko sekä pakkausteho), valitse päävalikossa komento __3__. Tällöin lisätiedot näytetään sinulle pakkaamisen yhteydessä.

Jos haluat tilastotietojen näyttämisen pois päältä, valitse päävalikossa uudelleen komento __3__.
