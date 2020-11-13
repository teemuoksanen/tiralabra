# Viikkoraportti 3

Käytetyt tunnit: __21__

## Mitä tein tällä viikolla?

Tällä viikolla selvästi eniten aikaa meni pakkaamis- ja purkamis-logiikkojen tekemiseen. Tein jo tässä vaiheessa melko paljon refaktorointia, jotta ohjelman logiikka pysyisi selkeänä.

Alun perin tarkoituksena oli jo aloittaa Javan valmiiden tietorakenteiden korvaaminen omilla versioilla, mutta valitettavasti siihen ei jäänyt tällä viikolla aikaa.

## Miten ohjelma on edistynyt?

Ohjelmasta on nyt olemassa ensimmäinen toimiva versio! Sillä pystyy pakkaamaan tiedoston Huffman-pakkausalgortimilla sekä purkamaan pakatun tiedoston takaisin alkuperäiseen muotoon. Ohjelma nojaa vielä Javan omiin tietorakenteisiin - pidin tärkeimpänä tavoitteena saada ohjelma toimimaan, jonka jälkeen valmiiden tietorakenteiden korvaaminen omilla versioilla ja näiden testaaminen on todennäköisesti helpompaa. (Ja onhan toimivalla ohjelmalla jo oma arvonsa verrattuna pelkkiin tietorakenteisiin, joita ei käytettäisi mihinkään.)

## Mitä opin tällä viikolla?

Keskeisin oppiminen tällä viikolla oli tiedostojen käsittely bittitasolla. Tämä tuntui melkoisen haastavalta - logiikan ymmärtämiseen sekä sen jälkeen toimivien metodien rakentamiseen meni yllättävän paljon aikaa. Tähän olisi löytynyt valmiita malleja, mutta halusin ymmärtää, miten logiikka näissä toimii, joten rakensin logiikan itse pala palalta. Ongelmia tuli vastaan tämän tästä ja debuggaamiseen meni aika paljon aikaa. Pakkaamis- ja purkamislogiikan toimimaan saaminen oli onnistumiskokemus!

(Bittitason kirjoittaminen ei ole ehkä keskeisin asia tällä kurssilla, mutta toisaalta viikko oli kuitenkin huomattavan opettavainen!)

## Mitä jäi epäselväksi?

Esimerkiksi bittivirtojen käsittely ja muuntaminen sopiviin muotoihin ei ehkä vielä ole kaikkein tehokkainta. Jossain vaiheessa yritän vielä varmaan testata ja selvittää, olisiko esimerkiksi tehokkaampaa muuntaa pakatun tiedoston bittivirta merkeiksi suoraan Huffman-puun avulla sen sijaan että (kuten tällä hetkellä) Huffman-puu puretaan ensin hajautustauluksi, josta merkit haetaan bittijonon perusteella.

## Mitä seuraavaksi?

Seuraavalla viikolla ensimmäisenä asiana on aloittaa Javan valmiiden tietorakenteiden korvaaminen omilla tietorakenteilla. Sen jälkeen yritän lisätä mukaan muita pakkaamisalgoritmeja. Käyttöliittymän logiikassa olisi toki parantamisen varaa, mutta tämä on pienimmällä prioriteetilla muihin tehtäviin verrattuna.
