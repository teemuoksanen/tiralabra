# Viikkoraportti "7" (eli viimeisen viikkopalautuksen jälkeen)

Käytetyt tunnit: __20__

## Mitä tein tällä viikolla?

LZW-algoritmin viimeistelyä, vertailu-ominaisuuden tekeminen eri algoritmeille, koodin refaktorointia ja dokumentaation viimeistelyä.

## Miten ohjelma on edistynyt?

LZW-algoritmi on saatu toimimaan - ohjelma tuli siis periaatteessa valmiiksi!

Käytin kuitenkin viimeisen viikkopalautuksen jälkeen dokumentoinnin lisäksi aika paljon aikaa refaktorointiin ja sain ohjelman mielestäni huomattavasti siistimmäksi: toisteisuutta on poistettu, tilastojen tulostamiseen liittyneet osat käyttöliittymästä on siirretty osaksi sovelluslogiikka, jne.

Testaamisen kannalta merkittävä asia oli eri algoritmien vertailuominaisuuden tekeminen - tätä olikin ehdotettu myös vertaisarvioinnissa. Tästä oli suuresti apua, kun testasin ohjelman pakkausalgoritmien tehokkuutta erilaisilla tiedostoilla.

## Mitä opin tällä viikolla?

LZW-pakkaus- ja -purkualgortimien virheet alkoivat viimeisen viikkopalautuksen jälkeen ratketa, kun kävin koodia läpi rivi riviltä.

Pakkaaja- ja Purkaja-luokkien muuttaminen abstrakteiksi luokiksi oli myös opettavaista - en ollut aiemmin juuri käyttänyt abstrakteja luokkia hyväksi, mutta tässä niistä oli apua, kun eri algoritmeilla toimivilla pakkaajilla oli paljon "yhteistä" koodia.
