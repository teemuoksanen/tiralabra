# Viikkoraportti 4

Käytetyt tunnit: __16__

## Mitä tein tällä viikolla?

Tällä viikolla korvasin Javan HashMapit omalla Hajautustaulu-luokalla sekä ArrayListin omalla Lista-luokalla. Lisäksi Keko-luokan rakentaminen on aloitettu, mutta sitä ei ole vielä kunnolla testattu eikä siten otettu käyttöön. Toteutus- ja testausdokumentaatiota on hieman aloitettu.

## Miten ohjelma on edistynyt?

Ohjelman perustoiminnallisuudet valmistuivat viime viikolla, joten tällä viikolla keskityin omien tietorakenteiden luomiseen.

Oman hajautustaulun käyttöönoton myötä jouduin tekemään yllättävän paljon muokkauksia toiminnallisuuksista vastaavaan koodiin. Oma Hajautustauluni ja Listani eivät toimi ihan yhtä näppärästi erityyppisten luokkien kanssa kuin Javan HashMap ja ArrayList, mutta koska ne ovat melko rajatussa käytössä ohjelmassa, totesin järkevämmäksi muuttaa toiminnalista koodia lisäämällä sinne tyypityksiä kuin parantaa Hajautustaulu- ja Lista-luokkia tältä osin.

Aloittamani Keko-luokan on tarkoitus korvata Javan PriorityQueue, mutta se ei ole vielä valmis käyttöönotettavaksi.

## Mitä opin tällä viikolla?

Hajautustaulun toimintaa toki käytiin jo TiRa-kurssilla läpi, mutta sen rakentaminen tällä kurssilla oli hyvää kertausta - toimintalogiikka oli paljon helpompi kuin muistinkaan. Samoin keon toimintaperiaatetta on tullut kerrattua.

Itse asiassa aloin pohtia, että muutamissa kohdissa koodiani hajautustaulun käyttö apuna taitaa olla turhaa tai ainakin sen muodostaminen kuluttaa turhaan laskenta-aikaa. Pitää miettiä, josko koodia kannattaisi tältä osin tehostaa - pitänee testata ensin ohjelman toimintaa pitemmillä testitiedostoilla.

## Mitä jäi epäselväksi?

Pakkaus-algoritmi kaatui jostain syystä muutamalla testitiedostolla nullPointerExceptioniin, jos hajautustaulun koko oli pieni. Tämä ongelma tuli esiin vasta aivan viikon lopussa. Väliaikaisena korjauksena toimi hajautustaulun oletuskoon kasvattaminen, mutta ongelman juurisyytä pitää vielä etsiä. Esiintymistilanteesta päätellen se on oletettavasti Hajautustaulu-luokan lisaa-metodin toteutuksessa silloin, kun usealla avaimella on sama hajautusarvo.

## Mitä seuraavaksi?

Seuraavalla viikolla jatkan aluksi Keko-luokan testaamista ja korvaan sillä PriorityQueuen. Sen jälkeen yritän lisätä mukaan muita pakkaamisalgoritmeja.
