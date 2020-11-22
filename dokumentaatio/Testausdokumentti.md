# Testausdokumentti

## Yksikkötestaus

Ohjelman yksikkötestaus on toteutettu JUnitilla. Jokaiselle tietorakenteelle on luotu oma testiluokka. Lisäksi ohjelman logiikkaosio on testattu omalla testiluokallaan.

__PakkaajaTest__ testaa yleisesti ohjelman toiminnallisuuksia. PakkaajaTest testaa sekä yksittäisiä metodeja, kuten puiden (sekä lehtien että solmujen) luomista ja puiden järjestämistä. Lisäksi PakkaajaTest testaa, että pakkaminen ja purkaminen todellisuudessa toimii, pakkaamalla esimerkkitiedoston ja purkamalla sen jälkeen kyseisen pakatun tiedoston. Testi tarkastaa, että alkuperäinen ja purettu tiedosto vastaavat toisiaan merkki merkiltä. Tämä testaa kerralla hyvin laajan osan koodista, koska muilta kuin edellisessä kappaleessa mainituilta osilta pakkaamis- ja purkamislogiikan osittainen testaaminen tuntui melko hankalalta.

*(TODO: Mockito-testit voisi ottaa ehkä tässä käyttöön?)*

__HajautustauluTest__ testaa puhtaasti Hajautustaulu-luokan toiminnallisuuksia: arvojen hakemista, avain-arvo -parien lisäämistä, alkion arvon muuttamista, alkioiden poistamista, hajautustaulun koon (alkioiden määrä) ja avaintaulukon palauttamista. Lisäksi erikseen varmistetaan, että hajautustaulun koko voi kasvaa alunperin määritetystä lisäysten myötä, että olemattoman alkion poistaminen ei pienennä taulun kokoa ja että hajauttaminen toimii.

__KekoTest__ testaa puhtaasti Keko-luokan toiminnallisuuksia: alkioiden lisäämistä, kurkistamista ja poistamista sekä sitä, että alkiot järjestyvät oikeaan suuruusjärjestykseen. Erikseen varmistetaan, että keon koko voi kasvaa alunperin määritetystä lisäysten myötä. Lisäksi testataan Puu-olioiden (Solmu- ja Lehti-luokat mukaanlukien) toimivuus niin, että esimerkiksi niiden vertailu toimii oikein.

__ListaTest__ testaa puhtaasti Lista-luokan toiminnallisuuksia: alkioiden lisäämistä, listan koon palauttamista ja sitä, että listan koko voi kasvaa alunperin määritetystä lisäysten myötä.

## Testimateriaali

- __lyhyt.txt__: Lyhyt 15 merkin pituinen tekstitiedosto.
- __loremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 20 kappaletta.
- __pitkaloremipsum.txt__: Satunnainen *Lorem ipsum* -teksti, 1000 kappaletta.
- __kalevala.txt__: Kalevala tekstitiedostona.
- __englishkalevala.txt__: Kalevala tekstitiedostona englanninkielisenä.
- __gitohje.html__: Tiralabran GIT-ohjesivu HTML-muodossa. Lähde: (https://tiralabra.github.io/2020_p2/fi/git-ohje/)
- __testikuva.png__: TV:n testikuva PNG-muodossa.
- __testikuva2.png__: Yksinkertaisempi TV:n testikuva PNG-muodossa.
- __tuomiokirkko.jpg__: JPEG-muotoinen valokuva Helsingin tuomiokirkosta. (c) Kallerna, lähde: (https://commons.wikimedia.org/wiki/File:Helsingin_tuomiokirkko.jpg)
