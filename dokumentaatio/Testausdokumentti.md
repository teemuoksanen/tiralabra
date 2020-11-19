# Testausdokumentti

## Yksikkötestaus

Ohjelman yksikkötestaus on toteutettu JUnitilla. Ohjelmasta löytyy kaksi eri testiluokkaa: PakkajaTest ja HajautustauluTest.

__HajautustauluTest__ testaa puhtaasti Hajautustaulu-luokan toiminnallisuuksia: arvojen hakemista, avain-arvo -parien lisäämistä, alkion arvon muuttamista, alkioiden poistamista, hajautustaulun koon (alkioiden määrä) ja avaintaulukon palauttamista. Lisäksi erikseen varmistetaan, että hajautustaulun koko kasvaa lisäysten myötä, että olemattoman alkion poistaminen ei pienennä taulun kokoa ja että hajauttaminen toimii.

__PakkaajaTest__ testaa yleisesti ohjelman toiminnallisuuksia. Apuna käytetään lisäksi AakkostoMock-luokkaa, johon on kovakoodattu kaksi valmista testiaakkostoa. PakkaajaTest testaa sekä yksittäisiä metodeja, kuten aakkoston luomista, puiden (sekä lehtien että solmujen) luomista, puiden järjestämistä sekä koodiston luomista.

Lisäksi PakkaajaTest testaa, että pakkaminen ja purkaminen todellisuudessa toimii, pakkaamalla esimerkkitiedoston ja purkamalla sen jälkeen kyseisen pakatun tiedoston. Testi tarkastaa, että alkuperäinen ja purettu tiedosto vastaavat toisiaan merkki merkiltä. Tämä testaa kerralla hyvin laajan osan koodista, koska muilta kuin edellisessä kappaleessa mainituilta osilta pakkaamis- ja purkamislogiikan osittainen testaaminen tuntui melko hankalalta.

*(TODO: Mockito-testit voisi ottaa ehkä tässä käyttöön?)*
