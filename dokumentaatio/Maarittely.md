# Määrittelydokumentti

## Taustatiedot

Opinto-ohjelma: __Tietojenkäsittelytieteen kandiohjelma__  
Ohjelmointikieli: __Java__  
Dokumentointikieli: __suomi__

## Ratkaistava ongelma

Tarkoituksenani on toteuttaa tiedonpakkausalgoritmi. Ensisijaisesti tulen toteuttamaan __Huffmanin algortmiin__ perustuvan pakkauksen. Mikäli aikaa riittää, saatan toteuttaa myös vaihtoehtoiseen algoritmiin perustuvan pakkauksen (esim. LZ) taikka jonkin Huffman-algoritmin variaatioista, sekä vertailla näiden tehokkuutta (pakkausteho, aikavaativuus) keskenään. Pääasiallisena tavoitteena on kuitenkin hyvin toimivan Huffman-pakkauksen toteuttaminen.

Huffman-algoritmilla päästään 20-90 % pakkaustehoon, mutta todellinen pakkausteho riippuu suuresti lähtöaineistosta - mitä suurempi merkkimäärä ja merkkien vaihtelu, sitä pienempi pakkausteho.

Huffman-algoritmin mormaali aikavaativuus on luokkaa _O(n log n)_. Järjestämiseen menee aikaa _O(log n)_ ja varsinaisen puun rakentamiseen _O(n)_.

## Tarvittavat tietorakenteet

Alussa käytän Javan omia tietorakenteita Huffman-algoritmin rakentamiseen. Kun algoritmi on valmis, korvaan Javan valmiit tietorakenteet omilla tietorakenteillani. Alustavan käsitykseni mukaan Huffman vaatii seuraavien tietorakenteiden toteuttamisen:

- __keko__ (minimikeko) - merkkien järjestämiseksi esiintymismäärän mukaan
- __puu__ (binääripuu) - varsinaisen Huffman-koodin generoimiseksi
- __hajautustaulu__ - kunkin merkin Huffman-koodin tallentamiseksi varsinaista pakkaamista varten

## Syöte ja tulosteet

Syötteenä on tarkoitus käyttää valmiita tekstitiedostoja. Tulosteena saadaan pakattu tiedosto, joka sisältää purkamiseen tarvittavan puun sekä itse koodatun tekstin.

## Lähteet

- Cormen, Thomas H. - Leiserson, Charles E. - Rivest, Ronald L. - Stein, Clifford: Introduction to Algorithms - Second Edition. Massachusetts Institute of Technology, 2001. (s. 385-391)
- Laaksonen, Antti: [Kisakoodarin käsikirja](https://www.cs.helsinki.fi/u/ahslaaks/kkkk.pdf). 2018. (s. 61-63)
- [Wikipedia: Huffman coding](https://en.wikipedia.org/wiki/Huffman_coding)
