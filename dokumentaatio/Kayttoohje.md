# Pakkaajan käyttöohje

## Asentaminen ja käynnistäminen

### Julkaistu versio

Lataa viimeisin julkaistu versio tästä: [pakkaaja.jar](https://github.com/teemuoksanen/tiralabra-huffman/releases/download/1.0/pakkaaja.jar)

Siirry hakemistoon, jonne latasit ohjelman, ja käynnistä ohjelma komennolla

`java -jar pakkaaja.jar`

Ohjelman voit sulkea sen päävalikosta komennolla __q__. 

### Lähdekoodista käännettävä versio

Lataa repositorion sisältö [ZIP-pakettina](https://github.com/teemuoksanen/tiralabra-huffman/archive/main.zip) tai kloonaa sisältö komennolla

`git clone git@github.com:teemuoksanen/tiralabra-huffman.git`

Siirry alihakemistoon *pakkaaja* siinä hakemistossa, jonne purit paketin tai kloonasit sisällön, ja käynnistä ohjelma komennolla

`gradle -q –console plain run`

Ohjelman voit sulkea sen päävalikosta komennolla __q__. 

## Tiedoston pakkaaminen

Voit pakata tiedoston valitsemalla päävalikossa komennon __1__.

Anna pakattavan tiedoston nimi sekä tarvittaessa polku. Jos tiedosto sijaitsee samassa hakemistossa ohjelman kanssa, voit antaa pelkän tiedostonimen. Jos haluatkin palata päävalikkoon, jätä tiedoston nimi tyhjäksi ja paina *enter*.

Valitse käytettävä pakkausalgoritmi ja paina *enter*. Komennolla __1__ pakkaamisessa käytetään Huffman-algoritmia ja komennolla __2__ LZW-algoritmia. Komennolla __0__ pääset takaisin päävalikkoon pakkaamatta tiedostoa.

Ohjelma pakkaa tiedoston samaan hakemistoon, missä alkuperäinen pakattava tiedosto sijaitsee. Pakatun tiedoston nimen perään lisätään pääte *.huff*, jos käytetään Huffman-algoritmia, tai *.lzw*, jos käytetään LZW-algoritmia. Esimerkiksi *teksti.txt* pakataan Huffman-algoritmilla tiedostoksi *teksti.txt.huff*. Mikäli samanniminen tiedosto on jo olemassa, pakkaaminen ei onnistu. Siirrä tai poista tällöin kyseinen tiedosto ja yritä uudelleen.

## Pakatun tiedoston purkaminen

Voit purkaa Pakkaajalla pakatun tiedoston valitsemalla päävalikossa komennon __2__.

Anna purettavan tiedoston nimi sekä tarvittaessa polku. Pakatun tiedoston päätteen tulee olla *.huff*, jos se on pakattu Huffman-algoritmilla, tai *.lzw*, jos se on pakattu LZW-algoritmilla. Jos pääte on jokin muu, ohjelma ei suostu purkamaan tiedostoa. Jos tiedosto sijaitsee samassa hakemistossa ohjelman kanssa, voit antaa pelkän tiedostonimen. Jos haluatkin palata päävalikkoon, jätä tiedoston nimi tyhjäksi ja paina *enter*.

Ohjelma purkaa tiedoston samaan hakemistoon, missä pakattu tiedosto sijaitsee. Puretun tiedston varsinaiseen nimeen lisätään tunniste *-purettu* ja pääte *.huff* tai *.lzw* poistetaan. Esimerkiksi *teksti.txt.huff* puretaan tiedostoksi *teksti-purettu.txt*. Mikäli samanniminen tiedosto on jo olemassa, pakkaaminen ei onnistu. Siirrä tai poista tällöin kyseinen tiedosto ja yritä uudelleen.

## Asetukset

Mikäli haluat nähdä tilastotietoja pakatusta tiedostosta (pakkaamiseen kulunut aika, tiedostojen koko sekä pakkausteho), valitse päävalikossa komento __3__. Tällöin lisätiedot näytetään sinulle pakkaamisen yhteydessä.

Jos haluat tilastotietojen näyttämisen pois päältä, valitse päävalikossa uudelleen komento __3__.

## Pakkausalgoritmien vertailu

Voit vertailla eri pakkausalgoritmien tehokkuutta valitsemalla päävalikossa komennon __4__.

Anna pakattavan tiedoston nimi sekä tarvittaessa polku. Jos tiedosto sijaitsee samassa hakemistossa ohjelman kanssa, voit antaa pelkän tiedostonimen. Jos haluatkin palata päävalikkoon, jätä tiedoston nimi tyhjäksi ja paina *enter*.

Ohjelma kokeilee pakata ja purkaa tiedoston käyttäen ensin Huffman-algoritmia ja sitten LZW-algoritmia. Ohjelma myös poistaa pakatut sekä puretut tiedostot. Lopuksi ohjelma tulostaa tilastotiedot eri pakkaustavoilla sekä vertaa niitä keskenään.
