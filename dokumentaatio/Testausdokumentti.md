# Testausdokumentti

## Yksikkötestaus

Ohjelman yksikkötestaus on toteutettu JUnitilla. Jokaiselle tietorakenteelle on luotu oma testiluokka. Lisäksi ohjelman logiikkaosio on testattu omalla testiluokallaan.

__PakkaajaTest__ testaa yleisesti ohjelman toiminnallisuuksia. Apuna käytetään lisäksi AakkostoMock-luokkaa, johon on kovakoodattu kaksi valmista testiaakkostoa. PakkaajaTest testaa sekä yksittäisiä metodeja, kuten aakkoston luomista, puiden (sekä lehtien että solmujen) luomista, puiden järjestämistä sekä koodiston luomista.

Lisäksi PakkaajaTest testaa, että pakkaminen ja purkaminen todellisuudessa toimii, pakkaamalla esimerkkitiedoston ja purkamalla sen jälkeen kyseisen pakatun tiedoston. Testi tarkastaa, että alkuperäinen ja purettu tiedosto vastaavat toisiaan merkki merkiltä. Tämä testaa kerralla hyvin laajan osan koodista, koska muilta kuin edellisessä kappaleessa mainituilta osilta pakkaamis- ja purkamislogiikan osittainen testaaminen tuntui melko hankalalta.

*(TODO: Mockito-testit voisi ottaa ehkä tässä käyttöön?)*

__HajautustauluTest__ testaa puhtaasti Hajautustaulu-luokan toiminnallisuuksia: arvojen hakemista, avain-arvo -parien lisäämistä, alkion arvon muuttamista, alkioiden poistamista, hajautustaulun koon (alkioiden määrä) ja avaintaulukon palauttamista. Lisäksi erikseen varmistetaan, että hajautustaulun koko voi kasvaa alunperin määritetystä lisäysten myötä, että olemattoman alkion poistaminen ei pienennä taulun kokoa ja että hajauttaminen toimii.

__KekoTest__ testaa puhtaasti Keko-luokan toiminnallisuuksia: alkioiden lisäämistä, kurkistamista ja poistamista sekä sitä, että alkiot järjestyvät oikeaan suuruusjärjestykseen. Erikseen varmistetaan, että keon koko voi kasvaa alunperin määritetystä lisäysten myötä. Lisäksi testataan Puu-olioiden (Solmu- ja Lehti-luokat mukaanlukien) toimivuus niin, että esimerkiksi niiden vertailu toimii oikein.

__ListaTest__ testaa puhtaasti Lista-luokan toiminnallisuuksia: alkioiden lisäämistä, listan koon palauttamista ja sitä, että listan koko voi kasvaa alunperin määritetystä lisäysten myötä.
