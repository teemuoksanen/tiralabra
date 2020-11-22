# Viikkoraportti 5 - *kesken*

Käytetyt tunnit: __12__

## Mitä tein tällä viikolla?

Parantelin yksikkötestausta poistamalla väliaikaisesti käytöstä laajaa testausta tekevän *PakkaajaTest*-luokan. Nyt tietorakenteiden omilla testeillä on lähes 100% testikattavuus, mikä helpottaa refaktorointia.

Siirsin myös kaiken tulostamisen Pakkaaja- ja Purkaja-luokista käyttöliittymän vastuulle. Nyt Pakkaaja ja Purkaja palauttavat käsitellyn tiedoston käyttöliittymälle, joka hoitaa tulostamisen onnistuneen pakkaamisen tai purkamisen jälkeen. Jos pakattu tai purettu tiedosto on jo olemassa, luokat heittävät TiedostoOlemassaPoikkeus-tyyppisen poikkeuksen virheilmoituksen tulostamisen sijasta.

Käyttöliittymää on lisäksi refaktoroitu, jotta sen jatkokehittäminen on helpompaa ja ymmärrettävyys parempaa.

## Miten ohjelma on edistynyt?

Viime viikon lopussa juuri ennen palautusta valmistuneesta Keko-tietorakenteesta paljastui muutamia puutteita - kiitos tekemäni yksikkötestauksen. Nämä puutteet on nyt korjattu.

Koska kaikki tulostus on siirretty logiikkaluokista käyttöliittymän vastuulle, on esimerkiksi graafisen käyttöliittymän luominen nyt suoraviivaisempaa. (Todennäköisesti en kuitenkaan tule toteuttamaan graafista käyttöliittymää kurssin aikana.)

Pakkaja-luokassa muutin tapaa, jolla pakattava tiedosto luetaan muistiin, koska aiempi versio tuki vain tekstitiedostoja. Nyt myös esimerkiksi kuvien pakkaaminen on ainakin periaatteessa mahdollista.

## Mitä opin tällä viikolla?

Opettelin tällä viikolla rakentemaan oman poikkeustyypin (TiedostoOlemassaPoikkeus). Kyse on hyvin yksinkertaisesta poikkeusluokasta, mutta ajaa asiansa tässä ohjelmassa.

## Mitä jäi epäselväksi?

xxx

## Mitä seuraavaksi?

xxx
