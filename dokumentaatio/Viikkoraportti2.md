# Viikkoraportti 2

Käytetyt tunnit: __12__

## Mitä tein tällä viikolla?

Huffman-algortimi ja suoraan sen vaatimat tietorakenteet on toteutettu. Lisäksi otin checkstyle-tarkistuksen käyttöön sekä täydensin Javadoc-dokumentaation jo toteutetuilta osin.

Checkstyle ja yksikkötestaus tähän mennessä tehdyille logiikka-luokille on tehty.

## Miten ohjelma on edistynyt?

Tekstipohjainen käyttöliittymäpohja on valmis, samoin Huffman-algoritmi on toteutettu. Sen sijaan varsinainen pakkaaminen ei vielä toimi, vaan ohjelma tulostaa vasta Huffman-koodiston, jota tullaan pakkaamiseen käyttämään. Purkamista ei ole toteutettu vielä ollenkaan.

## Mitä opin tällä viikolla?

Huffman-algoritmin rakentaminen onnistui hyvin - se oli tosiaan oletettua helpompi. Todennäköisesti tulenkin myöhempinä viikkoina täydentämään projektia esim. LZ-pakkauksella.

Opin lisäksi tiettyjen luokkien jättämisen huomioimatta jacoco-testikattavuusraportista. Oikeiden asetusten löytäminen build.gradle'en vei hieman aikaa.

## Mitä jäi epäselväksi?

Eniten ehkä mietityttää koodiston sekä binäärikoodin tallentaminen, joihin en vielä ole ehtinyt perehtyä.

## Mitä seuraavaksi?

Jatkan todennäköisesti pakkaus- ja purkutoiminnallisuuden rakentamista kuntoon. Sen jälkeen vuorossa on ainakin PriorityQueuen korvaaminen omalla tietorakenteella.
