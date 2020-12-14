# Testausdokumentti

## Yksikkötestaus

Ohjelman yksikkötestaus on toteutettu JUnitilla. Jokaiselle tietorakenteelle on luotu oma testiluokka. Lisäksi ohjelman logiikkaosio sekä I/O-toiminnallisuus on testattu omilla testiluokillaan.

### Logiikka

Ohelman logiikkaosuus on testattu pitkälti integraatiotestauksena, joka kattaa ohjelman logiikan lisäksi sen käyttämät tietorakenteet sekä I/O-toiminnallisuudet. Ainoastaan käyttöliittymä on jätetty tässä testaamatta. Integraatiotestaus ilman varsinaisia yksikkötestejä on perusteltua, koska pakkaamis- ja purkamislogiikan osittainen testaaminen ei mielestäni tuo juuri lisäarvoa - ne ovat yksi kokonaisuus, jotka käyttävät hyödykseen ohjelman muita osia.

__HuffmanTest__ testaa yksittäisiä metodeja, kuten puiden (sekä lehtien että solmujen) luomista ja puiden järjestämistä, sekä sitä, että pakkaminen ja purkaminen todellisuudessa toimii, pakkaamalla esimerkkitiedoston ja purkamalla sen jälkeen kyseisen pakatun tiedoston. Testi tarkastaa, että alkuperäinen ja purettu tiedosto vastaavat toisiaan merkki merkiltä. Lisäksi testit testaavat, että luokka heittää poikkeuksen esimerkiksi silloin, jos pakattavan/purettavan tiedoston kanssa samanniminen tiedosto on jo olemassa.

__LzwTest__ keskittyy testaamaan vastaavalla tavalla sitä, että pakkaminen ja purkaminen todellisuudessa toimii ja että luokka heittää tarvittaessa poikkeuksen. LzwTest ei testaa HuffmanTest'in tavoin yksittäisiä metodeja, koska LzwPakkaajan (ja LzwPurkajan) keskeinen toimintalogiikka on puristettu oikeastaan yhteen ainoaan metodiin.

### Tietorakenteet

__HajautustauluTest__ testaa puhtaasti Hajautustaulu-luokan toiminnallisuuksia: arvojen hakemista, avain-arvo -parien lisäämistä, alkion arvon muuttamista, alkioiden poistamista, hajautustaulun koon (alkioiden määrä) ja avaintaulukon palauttamista. Lisäksi erikseen varmistetaan, että hajautustaulun koko voi kasvaa alunperin määritetystä lisäysten myötä, että olemattoman alkion poistaminen ei pienennä taulun kokoa ja että hajauttaminen toimii.

__KekoTest__ testaa puhtaasti Keko-luokan toiminnallisuuksia: alkioiden lisäämistä, kurkistamista ja poistamista sekä sitä, että alkiot järjestyvät oikeaan suuruusjärjestykseen. Erikseen varmistetaan, että keon koko voi kasvaa alunperin määritetystä lisäysten myötä. Lisäksi testataan Puu-olioiden (Solmu- ja Lehti-luokat mukaanlukien) toimivuus niin, että esimerkiksi niiden vertailu toimii oikein.

__ListaTest__ testaa puhtaasti Lista-luokan toiminnallisuuksia: alkioiden lisäämistä, listan koon palauttamista ja sitä, että listan koko voi kasvaa alunperin määritetystä lisäysten myötä.

__TilastoTest__ testaa, että Tilasto-olio palauttaa eri tilanteissa oikeanlaiset tilastot.

### I/O

I/O-pakkauksen sisältö testataan pääosin osana sovelluslogiikan integraatiotestausta. Tämän lisäksi __IOTest__ testaa, että BittiKirjoittaja ja BittiLukija heittävät tietyissä tilanteissa poikkeuksen.

## Suorituskykytestaus

Ohjelman suorituskykyä on testattu erilaisilla testitiedostoilla. Osa tulee ohjelman mukana (ks. *Testimateriaali*), jolloin testit voi toistaa.

Alla on taulukkomuodossa eri algoritmien testitulokset järjestettynä pakkaustehon mukaan huonoimmasta parhaimpaan tulokseen.

### Huffman-algoritmi

|  **Tiedosto** | **Koko (kt)** | **Pakattu (kt)** | **Pakkausteho** | **Pakkausaika (s)** | **Purkuaika (s)** |
| --- | --- | --- | --- | --- | --- |
|  lyhyt.txt | 0.01 | 0.32 | -2093.3% | 0.392 | 0.023 |
|  tuomiokirkko.jpg | 2709.83 | 2707.05 | 0.1% | 1.167 | 1.895 |
|  video.mp4 | 31861.57 | 31824.02 | 0.1% | 6.607 | 19.217 |
|  pakkaaja.jar | 28.89 | 28.81 | 0.3% | 0.015 | 0.224 |
|  testikuva.png | 101.80 | 98.88 | 2.9% | 0.343 | 0.089 |
|  podcast.mp3 | 22879.77 | 21992.15 | 3.9% | 4.973 | 18.338 |
|  kirja.pdf | 39344.89 | 36061.03 | 8.3% | 6.393 | 30.263 |
|  kirja2.pdf | 2698.24 | 2418.91 | 10.4% | 0.576 | 2.571 |
|  gitohje.html | 21.36 | 13.55 | 36.6% | 0.200 | 0.025 |
|  englishkalevala.txt | 885.75 | 508.85 | 42.6% | 0.222 | 0.467 |
|  kalevala.txt | 642.94 | 363.24 | 43.5% | 0.349 | 0.394 |
|  loremipsum.txt | 11.95 | 6.69 | 44.0% | 0.153 | 0.011 |
|  pitkaloremipsum.txt | 88.27 | 47.49 | 46.2% | 0.088 | 0.070 |
|  testikuva2.png | 11.47 | 5.17 | 54.9% | 0.168 | 0.010 |

### LZW-algoritmi

|  **Tiedosto** | **Koko (kt)** | **Pakattu (kt)** | **Pakkausteho** | **Pakkausaika (s)** | **Purkuaika (s)** |
| --- | --- | --- | --- | --- | --- |
|  pakkaaja.jar | 28.89 | 45.35 | -57.0% | 0.012 | 0.043 |
|  lyhyt.txt | 0.01 | 0.02 | -46.7% | 0.023 | 0.005 |
|  video.mp4 | 31861.57 | 46020.34 | -44.4% | 13.639 | 7.020 |
|  tuomiokirkko.jpg | 2709.83 | 3760.83 | -38.8% | 1.167 | 1.274 |
|  podcast.mp3 | 22879.77 | 29924.67 | -30.8% | 8.806 | 4.729 |
|  testikuva.png | 101.80 | 115.45 | -13.4% | 0.062 | 0.027 |
|  kirja2.pdf | 2698.24 | 2355.17 | 12.7% | 0.870 | 0.458 |
|  kirja.pdf | 39344.89 | 28613.35 | 27.3% | 11.724 | 5.716 |
|  gitohje.html | 21.36 | 13.95 | 34.7% | 0.009 | 0.028 |
|  loremipsum.txt | 11.95 | 7.26 | 39.2% | 0.027 | 0.007 |
|  kalevala.txt | 642.94 | 263.77 | 59.0% | 0.149 | 0.063 |
|  englishkalevala.txt | 885.75 | 359.13 | 59.5% | 0.182 | 0.116 |
|  pitkaloremipsum.txt | 88.27 | 32.06 | 63.7% | 0.041 | 0.033 |
|  testikuva2.png | 11.47 | 3.63 | 68.4% | 0.006 | 0.030 |

### Pakkausteho

![Pakkausteho](https://github.com/teemuoksanen/tiralabra-huffman/blob/main/dokumentaatio/kuvat/pakkausteho.png?raw=true)

Testien perusteella näyttäisi siltä, että puhtaissa tekstitiedostoissa Huffman-algortimin pakkausteho on noin 45 % ja LZW-algoritmin vastaavasti noin 55 %. LZW-algoritmin teho korostui erityisesti pitemmissä teksteissä.

Kun mukana on muutakin kuin tekstiä, alkaa pakkausteho pienentyä molemmilla algoritmeilla nopeasti. HTML-tiedosto pakkautuu molemmilla vielä melko tehokkaasti (n. 35 % pakkausteho), mutta esimerkiksi tekstin lisäksi kuvia ja muotoilua sisältävissä PDF-tiedostoissa pakkausteho on Huffman-algoritmilla enää 10 % luokka ja LZW-algoritmillakin 13-27 % luokkaa. Kuvien, äänen ja videon pakkaamisessa pakkausteho Huffman-algortimilla on enää alle 5 %, ja suurikokoista valokuvaa sekä videokuvaa ei Huffman-algoritmilla saatu juurikaan pakattua. Tämä selittynee sillä, että valokuvien ja videon sisällössä on huomattavaa vaihtelua. LZW-algoritmilla tehon pudotus tekstiä sisältäneistä PDF-tiedostoista kuva-, ääni- ja videotiedostoihin oli vielä huomattavasti selkeämpi: LZW ei onnistunut pakkaamaan niitä ollenkaan, vaan "pakattujen" tiedostojen koot suurenivat.

Poikkeuksena yleislinjasta on esimerkiksi hyvin lyhyt 15-merkkinen testitiedosto, jonka koko Huffman-algoritmilla kasvoi suhteessa hyvin huomattavasti pakattuna. Tämä on ymmärrettävää, koska pakatun tiedoston alussa on vakiona 32-bittiä pitkä osuus merkkimäärän tallentamiseksi sekä koodattuna Huffman-koodisto - vasta tämän jälkeen tallennetaan itse tiedosto pakatussa muodossa. Pakkaus ei ole myöskään kovin tehokasta, koska merkit eivät lyhyessä tiedostossa toistu kovin paljoa. Myös LZW-algortimilla "pakatun" tiedoston koko paisui noin 1,5-kertaiseksi.

Positiivinen yllätys oli "graafisempi" PNG-muotoinen testikuva, joka muodostui pääosin suurista erivärisistä suorakulmioista. Sen pakkausteho oli molemmilla algortimeilla testin parhaimistoa: Huffman-algortimilla lähes 55 % ja LZW-algoritmilla jopa yli 68 % eli selvästi enemmän kuin tekstitiedostoilla. Tätä selittänee se, että kuvan sisällössä oli huomattavan paljon toistoa.

Mielenkiintoinen huomio oli se, että JAR-muotoinen Pakkaaja (eli ohjelma itse) pakkautui LZW-algoritmilla huonommin kuin mikään muu tiedosto. Huffman-algoritmi sai siitä nipistettyä sentään muutamia tavuja.

Yhteenvetona voitaneen todeta, että LZW-pakkauksen teho korostuu erityisesti pitkissä tekstitiedostoissa. Sen sijaan monissa muissa tiedostoissa se on melko tehoton - tähän saattaa hyvinkin olla syynä se, että LZW-pakkausta käytetään valmiiksi monissa kuvan- ja äänenpakkaustekniikoissa. Huffman-algoritmi pystyy yleensä pakkaamaan näitäkin ainakin jossain määrin, ja ilmeisesti Huffman-algoritmia käytetäänkin joskus "viimeisenä silauksena" vielä muiden pakkausalgoritmien käyttämisen jälkeen.

### Suoritusaika

Suoritusajan testaaminen ei tässä ohjelmassa ole yhtä keskeisessä osassa kuin pakkausteho. Tästä syytä en testauksessa keskittynyt toistamaan saman tiedoston pakkaamista ja purkamista useita kertoja.

Yleishuomiona voidaan kuitenkin todeta, että pakatun tiedoston purkaminen Huffman-algortimilla oli useimmissa tapauksissa alkuperäisen tiedon pakkaamista hitaampaa. Sen sijaan LZW-algoritmilla purkaminen oli yleensä noin puolet pakkaamista nopeampaa.

Lisäksi testauksessa huomaa, että LZW-algoritmi on lähes aina Huffman-algoritmia nopeampi. Tämä olikin oletettava tulos, kun Huffman-algoritmin aikavaativuus on *O*(*n* log *n*) ja LZW-algoritmin taas *O*(*n*).

## Testimateriaali

Ohjelman mukana tulee pieni määrä testitiedostoja, joilla tehdyt testit voi helposti toistaa:

- __lyhyt.txt__: Lyhyt 15 merkin pituinen tekstitiedosto.
- __loremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 20 kappaletta.
- __pitkaloremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 1000 kappaletta.
- __kalevala.txt__: Kalevala tekstitiedostona.
- __englishkalevala.txt__: Kalevala tekstitiedostona englanninkielisenä.
- __gitohje.html__: Tiralabran GIT-ohjesivu HTML-muodossa. Lähde: (https://tiralabra.github.io/2020_p2/fi/git-ohje/)
- __pakkaaja.jar__: Pakkaajan suorituskelpoinen JAR-pakkaus - eli ohjelma itse.
- __testikuva.png__: TV:n testikuva PNG-muodossa.
- __testikuva2.png__: Yksinkertaisempi TV:n testikuva PNG-muodossa.
- __tuomiokirkko.jpg__: JPEG-muotoinen valokuva Helsingin tuomiokirkosta. (c) Kallerna, lähde: (https://commons.wikimedia.org/wiki/File:Helsingin_tuomiokirkko.jpg)

Lisäksi ohjelmaa on testattu seuraavilla tiedostoilla, joita ei tekijänoikeuksien, koon tai vastaavan syyn takia ole liitetty ohjelmaan mukaan:

- __kirja.pdf__: Tekstiä ja kuvia sisältävä PDF-versio kirjasta *Paul R. Krugman, Maurice Obstfeld, Marc J. Melitz: International Economics -  Theory and Policy*.
- __kirja2.pdf__: Tekstiä ja kuvia sisältävä PDF-versio kirjasta *McGrayne, Sharon Bertsch: The Theory that would not Die - How Bayes Rule cracked the enigma code, hunted down russian submarines, emerged triumphant from two centuries of controversy*.
- __video.mp4__: Itse kuvattu 19 sekunnin pituinen ja 1080x1920 kokoinen video AAC, H.264 -kodekilla pakattuna.
- __podcast.mp3__: [ATK-castin](https://atk-cast.pinecast.co/) ensimmäinen jakso MP3-pakattuna, noin 35 minuuttia.
