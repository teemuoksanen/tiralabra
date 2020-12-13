# Toteutusdokumentti

## Ohjelman rakenne

### Toiminnallisuudet

Ohjelman toiminnallisuus koostuu karkeasti kahdesta loogisesta osuudesta: pakkaamistoiminnosta ja purkamistoiminnosta.

Pakkaamistoiminto pakkaa syötteenä annetusta polusta löytyvän tiedoston valitulla algoritmilla (Huffman tai LZW) ja palauttaa näin pakatun tiedoston nimen. Purkamistoiminta puolestaan purkaa aiemmin samalla ohjelmalla pakatun, syötteenä annetusta polusta löytyvän tiedston alkuperäiseen muotoonsa ja palauttaa puretun tiedoston nimen. Purkamistoiminto tunnistaa käytetyn algortmin pakatun tiedoston nimeen sisältyvän päätteen perusteella.

Pakattu tiedosto tallennetaan samaan polkuun kuin alkuperäinen tiedosto, mutta tiedostonimeen loppuun lisätään pakkausalgoritmia kuvaava tunniste *.huff* tai *.lzw*. Purettu tiedosto tallennetaan vastaavasti samaan polkuun kuin pakattu tiedosto, mutta tiedostonimeen loppusta poistetaan pakatun tiedoston tunniste (*.huff* tai *.lzw*) ja tiedostonimeen lisätään *-purettu* varsinaisen tiedostonimen ja tiedoston tunnisteen väliin.

### Luokka- ja pakkausrakenne

![Pakkauskaavio](https://github.com/teemuoksanen/tiralabra-huffman/blob/main/dokumentaatio/kuvat/pakkauskaavio.png?raw=true)

Pakkaaja on toteutettu kolmikerroksista kerrosarkkitehtuuria noudattaen. Käyttöliittymä, sovelluslogiikka sekä tietorakenteet ja tiedostojen luku-/kirjoitustoiminnot on jaettu omiksi pakkauksikseen. Käyttöliittymä kutsuu sovelluslogiikkaa, ja sovelluslogiikka puolestaan hyödyntää tietorakenteita sekä luku-/kirjoitustoimintoja.

*pakkaaja.ui*-pakkaus sisältää ainoastaan ohjelman tekstikäyttöliittymän.

*pakkaaja.logiikka*-pakkaus sisältää pakkaamisesta ja purkamisesta huolehtivan varsinaisen sovelluslogiikan sekä erityisesti suorituskykyvertailuun käytettävän *Tilasto*-olioluokan. Sovelluslogiikka koostuu abstrakteista *Pakkaaja*- ja *Purkaja*-luokista. Varsinaiset pakkaamis- ja purkualgortimit on erotettu omiksi pakkauksikseen *pakkaaja.logiikka.huffman* ja *pakkaaja.logiikka.lzw*.

Tietorakenteet on koottu *pakkaaja.tietorakenteet*-pakkaukseen, ja jokainen yksittäinen tietorakennekokonaisuus on erotettu omiksi pakkauksikseen *pakkaaja.tietorakenteet.hajautustaulu*, *pakkaaja.tietorakenteet.keko*, *pakkaaja.tietorakenteet.lista* ja *pakkaaja.tietorakenteet.puu*.

*pakkaaja.io*-pakkaukseen on koottu bittitason kirjoittamisesta ja lukemisesta huolehtivat luokat *BittiKirjoittaja* ja *BittiLukija*.

### Toteutetut tietorakenteet

__Hajautustaulu__ on tehty korvaamaan Javan *HashMap*-toteutus. Toteutus on geneerinen, joten tauluun voidaan tallettaa erimuotoisia avaimia ja arvioita. Uusi hajautustaulu voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 80) tai koko voidaan määrittää konstruktorissa. Hajautustaulun alkiot on toteutettu erillisellä *Alkio*-oliolla.

__Keko__ on tyypiltään minimikeko ja tehty korvaamaan Javan *PriorityQueue*-toteutus. Keko tallettaa alkioinaan ainoastaan *Puu*-olioita, joten sitä ei pysty käyttämään geneerisenä PriorityQueuen korvaajana. Uusi keko voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 80) tai koko voidaan määrittää konstruktorissa.

Kekoon liittyvä __Puu__-olio muodostuu geneerisestä Puu-luokasta ja sen aliluokista *Solmu* ja *Lehti*. Lehti-oliolla ei ole lapsisolmuja, vaan ainoastaan solmuvanhempi - se vastaa Huffman-puussa siis yksittäistä merkkiä. Solmu-oliolla puolestaan on vasen ja oikea lapsisolmu.

__Lista__ on tehty korvaamaan Javan *ArrayList*-toteutus. Toteutus on geneerinen, joten listaan voidaan tallettaa erimuotoisia alkoita. Uusi lista voidaan alustaa joko oletuskokoiseksi (tällä hetkellä 16) tai koko voidaan määrittää konstruktorissa.

## Saavutetut aika- ja tilavaativuudet

### Aikavaativuus

Kun tiedoston sisältämien merkkien määrä on *n*, niin Huffman-pakkauksen aikavaativuus on luokkaa __*O*(*n* log *n*)__. Aikavaativuuden kannalta merkittävin tehtävä on puun rakentaminen. Puun rakentaminen on riippuvainen koko aakkoston pituudesta, joka on pahimmillaan *n*, jos tiedoston kaikki merkit ovat erilaisia - aikavaativuus on luokkaa *O*(*n* log *n*), koska pienimmän painon selvittäminen keossa vie kultakin merkiltä aikaa *O*(log *n*) ja merkkejä on siis enimmillään *n* kappaletta. Merkkien lukeminen tiedostosta, aakkoston luominen, koodiston luominen sekä avaimen ja merkkien kirjoittaminen tiedostoon vievät aikaa sen sijaan *O*(*n*).

Kun tiedoston sisältämien merkkien määrä on *n*, niin LZW-pakkauksen aikavaativuus on luokkaa __*O*(*n*)__. Merkkien lukeminen tiedostosta, merkkien pakkaaminen ja merkkien kirjoittaminen tiedostoon vievät kaikki aikaa *O*(*n*). Toisin kuin Huffman-algoritmissa, LZW-algoritmissa ei siis ole sisäkkäisiä silmukoita.

### Tilavaativuus

Sekä Huffman- että LZW-pakkauksissa tilavaativuus on luokkaa __*O*(*n*)__. Tilavaativuuden kannalta merkittävintä on molemmissa oikeastaan lopullisen tiedoston kirjoittaminen, sillä pahimmassa tapauksessa pakkaaminen kasvattaakin tiedoston kokoa pienenemisen sijasta. Tämä johtuu tavasta, jolla pakattu tiedosto koodataan. 

Jos jokainen pakattavan tiedoston merkki on eri, Huffman-pakkaaminen ei tuo etua: pakatun tiedoston alkuun on tallennettava purkamista varten alkuperäisen tiedoston merkkimäärä sekä Huffman-puu binäärikoodattuna. Koska Huffman-pakkaus perustuu tiedostossa olevien merkkien toistuuvuuteen, ei pakkaamista yleensäkään tapahdu, jos merkit eivät toistu.

LZW-pakkauksessa pakkaustehoon vaikuttaa se, kuinka monella bitillä sen pakkaamat merkkijonot koodataan. Pakkaajassa käytetään 16-bittistä koodausta, joten kukin pakattu merkkijono vie tilaa kaksi tavua. Tästä syystä tiedoston koko voi kasvaa, jos merkkijonot eivät tiedostossa toistu.

Normaalikäytössä tilavaativuus ei nykytietokoneilla ole ohjelman kannalta kovinkaan merkittävä asia. Esimerkiksi LZW-pakkauksen tilavaativuutta voitaisiin eri keinoin pienentää, mutta tämä vaikuttaisi käänteisesti aikavaativuuteen - nykyaikaisella tietokoneella on käytettävyyden kannalta parempi uhrata hieman enemmän muistitilaa kuin tuhlata laskentatehoa.

## Puuteet ja parannusehdotukset

Graafinen käyttöliittymä helpottaisi ohjelman käyttöä, kun käyttäjä voisi valita pakattavan tai purettavan tiedoston käyttöjärjestelmän oman tiedostovalitsimen avulla. Toisaalta osa käyttäjistä saattaisi hyvinkin toivoa, että ohjelma olisi käskettävissä suoraan komentoriviltä. Tällöin esimerkiksi tiedostonimen täydennys komentorivillä olisi mahdollista.

Myös Huffman- ja LZW-algoritmien yhteiskäytöllä voisi lähdemateriaalin perusteella saavuttaa jonkin verran lisää pakkaustehoa. LZW:stä on lisäksi olemassa jatkokehitettyjä versioita, joten sitäkin voisi kehittää eteenpäin. Luonnollisesti myös uusien pakkausalgoritmien käyttöönotto olisi mahdollista.

## Lähteet

- Bhat, Sooraj: [LZW Data Compression](https://www2.cs.duke.edu/csed/curious/compression/lzw.html). 2002.
- Cormen, Thomas H. - Leiserson, Charles E. - Rivest, Ronald L. - Stein, Clifford: Introduction to Algorithms - Second Edition. Massachusetts Institute of Technology, 2001. (s. 385-391)
- Laaksonen, Antti: [Kisakoodarin käsikirja](https://www.cs.helsinki.fi/u/ahslaaks/kkkk.pdf). 2018. (s. 61-63)
- [Wikipedia: Huffman coding](https://en.wikipedia.org/wiki/Huffman_coding)
- [Wikipedia: Lempel-Ziv-Welch](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
