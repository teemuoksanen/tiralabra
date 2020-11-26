# Viikkoraportti 5

Käytetyt tunnit: __14__

## Mitä tein tällä viikolla?

Parantelin yksikkötestausta poistamalla väliaikaisesti käytöstä laajaa testausta tekevän *PakkaajaTest*-luokan. Nyt tietorakenteiden omilla testeillä on lähes 100% testikattavuus, mikä helpottaa refaktorointia.

Siirsin myös kaiken tulostamisen Pakkaaja- ja Purkaja-luokista käyttöliittymän vastuulle. Nyt Pakkaaja ja Purkaja palauttavat käsitellyn tiedoston käyttöliittymälle, joka hoitaa tulostamisen onnistuneen pakkaamisen tai purkamisen jälkeen. Jos pakattu tai purettu tiedosto on jo olemassa, luokat heittävät TiedostoOlemassaPoikkeus-tyyppisen poikkeuksen virheilmoituksen tulostamisen sijasta.

Käyttöliittymää on lisäksi refaktoroitu, jotta sen jatkokehittäminen on helpompaa ja ymmärrettävyys parempaa.

Toteutus- ja testausdokumentteja aloitin kirjoittamaan myös kunnolla tällä viikolla.

## Miten ohjelma on edistynyt?

Viime viikon lopussa juuri ennen palautusta valmistuneesta Keko-tietorakenteesta paljastui muutamia puutteita - kiitos tekemäni yksikkötestauksen. Nämä puutteet on nyt korjattu.

Koska kaikki tulostus on siirretty logiikkaluokista käyttöliittymän vastuulle, on esimerkiksi graafisen käyttöliittymän luominen nyt suoraviivaisempaa. (Todennäköisesti en kuitenkaan tule toteuttamaan graafista käyttöliittymää kurssin aikana.)

Pakkaja-luokassa muutin tapaa, jolla pakattava tiedosto luetaan muistiin, koska aiempi versio tuki vain tekstitiedostoja. Nyt myös esimerkiksi kuvien pakkaaminen on ainakin periaatteessa mahdollista.

Lemplel-Ziv-Welchin pakkausalgortimin laatiminen on myös aloitettu, mutta se ei ole vielä käytössä.

## Mitä opin tällä viikolla?

Opettelin tällä viikolla rakentemaan oman poikkeustyypin (TiedostoOlemassaPoikkeus). Kyse on hyvin yksinkertaisesta poikkeusluokasta, mutta ajaa asiansa tässä ohjelmassa.

Myös Lemplel-Ziv-Welchin pakkausalgortimin toimintaa tuli opiskeltua alustavasti ja ensimmäisiä versioita siitä on jo koodattukin.

## Mitä jäi epäselväksi?

Vaikka Lemplel-Ziv-Welchin algoritmin rakentaminen on jo käynnissä esimerkkien avulla, niin kovin syvällisesti en ole vielä sitä ymmärtänyt. Parempi ymmärrys on tarpeen, jotta saisin sen liitettyä tehokkaasti osaksi olemassa olevaa ohjelmaa. 

## Mitä seuraavaksi?

Ensisijaisena tavoitteena on LZW-pakkauksen logiikan rakentaminen valmiiksi ja implementointi ohjelmaan. Sen jälkeen keskityn todennäköisesti dokumentaatioon.
