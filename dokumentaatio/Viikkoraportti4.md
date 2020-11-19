# Viikkoraportti 4

Käytetyt tunnit: __11__

## Mitä tein tällä viikolla?

Tällä viikolla korvasin Javan HashMapit omalla Hajautustaulu-luokalla. Toteutus- ja testausdokumentaatiota on hieman aloitettu.

## Miten ohjelma on edistynyt?

Ohjelman perustoiminnallisuudet valmistuivat viime viikolla, joten tällä viikolla keskityin omien tietorakenteiden luomiseen.

Oman hajautustaulun käyttöönoton myötä jouduin tekemään yllättävän paljon muokkauksia toiminnallisuuksista vastaavaan koodiin. Oma hajautustauluni ei toimi ihan yhtä näppärästi erityyppisten luokkien kanssa kuin Javan HashMap, mutta koska hajautustaulut ovat melko rajatussa käytössä ohjelmassa, totesin järkevämmäksi muuttaa toiminnalista koodia lisäämällä sinne tyypityksiä kuin parantaa Hajautustaulu-luokkaa tältä osin.

## Mitä opin tällä viikolla?

Hajautustaulun toimintaa toki käytiin jo TiRa-kurssilla läpi, mutta sen rakentaminen tällä kurssilla oli hyvää kertausta - toimintalogiikka oli paljon helpompi kuin muistinkaan.

Itse asiassa aloin pohtia, että muutamissa kohdissa koodiani hajautustaulun käyttö apuna taitaa olla turhaa tai ainakin sen muodostaminen kuluttaa turhaan laskenta-aikaa. Pitää miettiä, josko koodia kannattaisi tältä osin tehostaa - pitänee testata ensin ohjelman toimintaa pitemmillä testitiedostoilla.

## Mitä jäi epäselväksi?

Pakkaus-algoritmi kaatui jostain syystä muutamalla testitiedostolla nullPointerExceptioniin, jos hajautustaulun koko oli pieni. Tämä ongelma tuli esiin vasta aivan viikon lopussa. Väliaikaisena korjauksena toimi hajautustaulun oletuskoon kasvattaminen, mutta ongelman juurisyytä pitää vielä etsiä. Esiintymistilanteesta päätellen se on oletettavasti Hajautustaulu-luokan lisaa-metodin toteutuksessa silloin, kun usealla avaimella on sama hajautusarvo.

## Mitä seuraavaksi?

Seuraavalla viikolla korvaan aluksi PriorityQueuen omalla toteutuksella. Sen jälkeen yritän lisätä mukaan muita pakkaamisalgoritmeja.
