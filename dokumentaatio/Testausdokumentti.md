# Testausdokumentti

## Yksikkötestaus

Ohjelman yksikkötestaus on toteutettu JUnitilla. Jokaiselle tietorakenteelle on luotu oma testiluokka. Lisäksi ohjelman logiikkaosio on testattu omalla testiluokallaan.

__PakkaajaTest__ testaa yleisesti ohjelman toiminnallisuuksia. PakkaajaTest testaa sekä yksittäisiä metodeja, kuten puiden (sekä lehtien että solmujen) luomista ja puiden järjestämistä. Lisäksi PakkaajaTest testaa, että pakkaminen ja purkaminen todellisuudessa toimii, pakkaamalla esimerkkitiedoston ja purkamalla sen jälkeen kyseisen pakatun tiedoston. Testi tarkastaa, että alkuperäinen ja purettu tiedosto vastaavat toisiaan merkki merkiltä. Tämä testaa kerralla hyvin laajan osan koodista, koska muilta kuin edellisessä kappaleessa mainituilta osilta pakkaamis- ja purkamislogiikan osittainen testaaminen tuntui melko hankalalta.

*(TODO: Mockito-testit voisi ottaa ehkä tässä käyttöön?)*

__HajautustauluTest__ testaa puhtaasti Hajautustaulu-luokan toiminnallisuuksia: arvojen hakemista, avain-arvo -parien lisäämistä, alkion arvon muuttamista, alkioiden poistamista, hajautustaulun koon (alkioiden määrä) ja avaintaulukon palauttamista. Lisäksi erikseen varmistetaan, että hajautustaulun koko voi kasvaa alunperin määritetystä lisäysten myötä, että olemattoman alkion poistaminen ei pienennä taulun kokoa ja että hajauttaminen toimii.

__KekoTest__ testaa puhtaasti Keko-luokan toiminnallisuuksia: alkioiden lisäämistä, kurkistamista ja poistamista sekä sitä, että alkiot järjestyvät oikeaan suuruusjärjestykseen. Erikseen varmistetaan, että keon koko voi kasvaa alunperin määritetystä lisäysten myötä. Lisäksi testataan Puu-olioiden (Solmu- ja Lehti-luokat mukaanlukien) toimivuus niin, että esimerkiksi niiden vertailu toimii oikein.

__ListaTest__ testaa puhtaasti Lista-luokan toiminnallisuuksia: alkioiden lisäämistä, listan koon palauttamista ja sitä, että listan koko voi kasvaa alunperin määritetystä lisäysten myötä.

## Suorituskykytestaus

Ohjelman suorituskykyä on testattu erilaisilla testitiedostoilla. Osa tulee ohjelman mukana (ks. *Testimateriaali*), jolloin testit voi toistaa.

|  **Tiedosto** | **Koko (kt)** | **Pakattu (kt)** | **Pakkausteho** | **Pakkausaika (s)** | **Purkuaika (s)** | **Aikojen suhde** |
| --- | --- | --- | --- | --- | --- | --- |
|  lyhyt.txt | 0.015 | 0.321 | -2093.33% | 0.0053 | 0.0263 | 5.0 |
|  testikuva2.png | 11.471 | 5.173 | 54.90% | 0.0072 | 0.0181 | 2.5 |
|  loremipsum.txt | 11.947 | 6.689 | 44.01% | 0.0100 | 0.0719 | 7.2 |
|  gitohje.html | 21.363 | 13.546 | 36.59% | 0.0061 | 0.0111 | 1.8 |
|  pitkaloremipsum.txt | 88.269 | 47.487 | 46.20% | 0.0129 | 0.1145 | 8.8 |
|  testikuva.png | 101.799 | 98.884 | 2.86% | 0.0344 | 0.1457 | 4.2 |
|  kalevala.txt | 642.938 | 363.236 | 43.50% | 0.0789 | 0.3109 | 3.9 |
|  englishkalevala.txt | 885.753 | 508.848 | 42.55% | 0.0936 | 0.3834 | 4.1 |
|  kirja2.pdf | 2698.243 | 2418.905 | 10.35% | 0.3416 | 1.9192 | 5.6 |
|  tuomiokirkko.jpg | 2709.832 | 2707.048 | 0.10% | 0.3901 | 1.8949 | 4.9 |
|  podcast.mp3 | 22879.771 | 21992.152 | 3.88% | 3.2811 | 16.4045 | 5.0 |
|  video.mp4 | 31861.565 | 31824.021 | 0.12% | 4.5843 | 22.9726 | 5.0 |
|  kirja.pdf | 39344.890 | 36061.028 | 8.35% | 4.0606 | 23.6578 | 5.8 |

### Pakkausteho

Testien perusteella näyttäisi siltä, että puhtaissa tekstitiedostoissa pakkausteho on noin 44-47 % riipumatta kielestä (testattu suomeksi, englanniksi ja lorem ipsum -tekstillä). Kun mukana on muutakin kuin tekstiä, alkaa pakkausteho pienentyä nopeasti. HTML-tiedosto pakkautuu vielä melko tehokkaasti, mutta esimerkiksi tekstin lisäksi kuvia ja muotoilua sisältävissä PDF-tiedostoissa pakkausteho on enää 10 % luokka. Kuvien, äänen ja videon pakkaamisessa pakkausteho on enää alle 5 %, ja suurikokoista valokuvaa sekä videokuvaa ei Huffman-algoritmilla saatu juurikaan pakattua.

Poikkeuksena yleislinjasta on esimerkiksi hyvin lyhyt 15-merkkinen testitiedosto, jonka koko kasvoi suhteessa huomattavasti pakattuna. Tämä on ymmärrettävää, koska pakatun tiedoston alussa on vakiona 32-bittiä pitkä osuus merkkimäärän tallentamiseksi sekä koodattuna Huffman-koodisto - vasta tämän jälkeen tallennetaan itse tiedosto pakatussa muodossa. Pakkaus ei ole myöskään kovin tehokasta, koska merkit eivät lyhyessä tiedostossa toistu kovin paljoa.

Positiivinen yllätys oli "graafisempi" PNG-muotoinen testikuva, joka muodostui pääosin suurista erivärisistä suorakulmioista. Sen pakkausteho oli lähes 55 % eli selvästi enemmän kuin tekstitiedostojen. Tätä selittänee se, että kuvan sisällössä oli huomattavan paljon toistoa.

### Suoritusaika

Suoritusajan testaaminen ei tässä ohjelmassa ole yhtä keskeisessä osassa kuin pakkausteho. Yleishuomiona voidaan kuitenkin todeta, että pakatun tiedoston purkaminen oli selkeästi alkuperäisen tiedon pakkaamista hitaampaa. Purkaminen kesti keskimäärin viisi kertaa niin kauan kuin pakkaaminen.

## Testimateriaali

Ohjelman mukana tulee pieni määrä testitiedostoja, joilla tehdyt testit voi helposti toistaa:

- __lyhyt.txt__: Lyhyt 15 merkin pituinen tekstitiedosto.
- __loremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 20 kappaletta.
- __pitkaloremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 1000 kappaletta.
- __kalevala.txt__: Kalevala tekstitiedostona.
- __englishkalevala.txt__: Kalevala tekstitiedostona englanninkielisenä.
- __gitohje.html__: Tiralabran GIT-ohjesivu HTML-muodossa. Lähde: (https://tiralabra.github.io/2020_p2/fi/git-ohje/)
- __testikuva.png__: TV:n testikuva PNG-muodossa.
- __testikuva2.png__: Yksinkertaisempi TV:n testikuva PNG-muodossa.
- __tuomiokirkko.jpg__: JPEG-muotoinen valokuva Helsingin tuomiokirkosta. (c) Kallerna, lähde: (https://commons.wikimedia.org/wiki/File:Helsingin_tuomiokirkko.jpg)

Lisäksi ohjelmaa on testattu seuraavilla tiedostoilla, joita ei tekijänoikeuksien, koon tai vastaavan syyn takia ole liitetty ohjelmaan mukaan:

- __kirja.pdf__: Tekstiä ja kuvia sisältävä PDF-versio kirjasta *Paul R. Krugman, Maurice Obstfeld, Marc J. Melitz: International Economics -  Theory and Policy*.
- __kirja2.pdf__: Tekstiä ja kuvia sisältävä PDF-versio kirjasta *McGrayne, Sharon Bertsch: The Theory that would not Die - How Bayes Rule cracked the enigma code, hunted down russian submarines, emerged triumphant from two centuries of controversy*.
- __video.mp4__: Itse kuvattu 19 sekunnin pituinen ja 1080x1920 kokoinen video AAC, H.264 -kodekilla pakattuna.
- __podcast.mp3__: [ATK-castin](https://atk-cast.pinecast.co/) ensimmäinen jakso MP3-pakattuna, noin 35 minuuttia.
