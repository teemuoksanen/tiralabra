# Toteutusdokumentti

## Ohjelman rakenne

### Toiminnallisuudet

Ohjelman toiminnallisuus koostuu karkeasti kahdesta loogisesta osuudesta: pakkaamistoiminnosta ja purkamistoiminnosta. Pakkaamistoiminto pakkaa syötteenä annetusta polusta löytyvän tiedoston Huffman-algoritmilla ja palauttaa näin pakatun tiedoston nimen. Purkamistoiminta puolestaan purkaa aiemmin samalla ohjelmalla pakatun, syötteenä annetusta polusta löytyvän tiedston alkuperäiseen muotoonsa ja palauttaa puretun tiedoston nimen.

Pakattu tiedosto tallennetaan samaan polkuun kuin alkuperäinen tiedosto, mutta tiedostonimeen loppuun lisätään *.pakattu*-tunniste. Purettu tiedosto tallennetaan vastaavasti samaan polkuun kuin pakattu tiedosto, mutta tiedostonimeen loppusta poistetaan *.pakattu*-tunniste ja tiedostonimeen lisätään *-purettu* varsinaisen tiedostonimen ja tiedoston tunnisteen väliin.

### Luokka- ja pakkausrakenne

![Pakkauskaavio](https://github.com/teemuoksanen/tiralabra-huffman/edit/main/dokumentaatio/kuvat/pakkauskaavio.png)

Pakkaaja on toteutettu kolmikerroksista kerrosarkkitehtuuria noudattaen. Käyttöliittymä, sovelluslogiikka ja tietorakenteet on jaettu omiksi pakkauksikseen. Käyttöliittymä kutsuu sovelluslogiikkaa, ja sovelluslogiikka puolestaan hyödyntää tietorakenteita.

Sovelluslogiikassa bittitason kirjoittamisesta ja lukemisesta huolehtivat luokat *BittiKirjoittaja.java* ja *BittiLukija.java* on erotettu omaksi *pakkaaja.logiikka.io*-pakkaukseksi.

Tietorakenteissa jokainen yksittäinen tietorakennekokonaisuus on erotettu omiksi pakkauksikseen *pakkaaja.tietorakenteet.hajautustaulu*, *pakkaaja.tietorakenteet.keko*, *pakkaaja.tietorakenteet.lista* ja *pakkaaja.tietorakenteet.puu*.

### Toteutetut tietorakenteet

__Hajautustaulu__ on tehty korvaamaan Javan *HashMap*-toteutus. Toteutus on geneerinen, joten tauluun voidaan tallettaa erimuotoisia avaimia ja arvioita. Uusi hajautustaulu voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 80) tai koko voidaan määrittää konstruktorissa. Hajautustaulun alkiot on toteutettu erillisellä *Alkio*-oliolla.

__Keko__ on tyypiltään minimikeko ja tehty korvaamaan Javan *PriorityQueue*-toteutus. Keko tallettaa alkioinaan ainoastaan *Puu*-olioita, joten sitä ei pysty käyttämään geneerisenä PriorityQueuen korvaajana. Uusi keko voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 80) tai koko voidaan määrittää konstruktorissa.

Kekoon liittyvä __Puu__-olio muodostuu geneerisestä Puu-luokasta ja sen aliluokista *Solmu* ja *Lehti*. Lehti-oliolla ei ole lapsisolmuja, vaan ainoastaan solmuvanhempi - se vastaa Huffman-puussa siis yksittäistä merkkiä. Solmu-oliolla puolestaan on vasen ja oikea lapsisolmu.

__Lista__ on tehty korvaamaan Javan *ArrayList*-toteutus. Toteutus on geneerinen, joten listaan voidaan tallettaa erimuotoisia alkoita. Uusi lista voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 16) tai koko voidaan määrittää konstruktorissa.

## Saavutetut aika- ja tilavaativuudet

Tilavaativuutta on tutkittu vain pakkaamisen osalta. Tilavaativuuden näkökulmasta pakkamis- ja purkamistoiminnallisuudet ovat luonnollisesti toistensa vastakohtia - jos ohjelma toimii oikein, puretun tiedoston pitäisi olla saman kokoinen kuin alkuperäisen tiedoston.

Aikavaativuutta on sen sijaan mielekästä tutkia erikseen sekä pakkaamis- että purkamistoiminnallisuuksien ja -algoritmien osalta, koska niiden toimintalogiikka on hieman erilainen.

### Pakkaamisen tilavaativuus

...

### Pakkaamisen aikavaativuus

...

### Purkamisen aikavaativuus

...

## Puuteet ja parannusehdotukset

Graafinen käyttöliittymä helpottaisi ohjelman käyttöä, kun käyttäjä voisi valita pakattavan tai purettavan tiedoston käyttöjärjestelmän oman tiedostovalitsimen avulla. Toisaalta osa käyttäjistä saattaisi hyvinkin toivoa, että ohjelma olisi käskettävissä suoraan komentoriviltä. Tällöin esimerkiksi tiedostonimen täydennys komentorivillä olisi mahdollista.

## Lähteet


- Cormen, Thomas H. - Leiserson, Charles E. - Rivest, Ronald L. - Stein, Clifford: Introduction to Algorithms - Second Edition. Massachusetts Institute of Technology, 2001. (s. 385-391)
- Laaksonen, Antti: [Kisakoodarin käsikirja](https://www.cs.helsinki.fi/u/ahslaaks/kkkk.pdf). 2018. (s. 61-63)
- [Wikipedia: Huffman coding](https://en.wikipedia.org/wiki/Huffman_coding)
