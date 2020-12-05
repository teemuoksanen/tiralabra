
package pakkaaja.ui;

import java.io.*;
import java.util.Scanner;
import pakkaaja.logiikka.LzwPakkaaja;
import pakkaaja.logiikka.LzwPurkaja;
import pakkaaja.logiikka.Pakkaaja;
import pakkaaja.logiikka.Purkaja;
import pakkaaja.tietorakenteet.lista.Lista;

/**
 * Pakkaajan tekstipohjainen käyttöliittymä.
 */
public class Kayttoliittyma {
    
    private Scanner lukija;
    private boolean tilastot;
    
    /**
     * Tekstikäyttöliittymän konstruktori.
     */
    public Kayttoliittyma() {
        lukija = new Scanner(System.in);
        tilastot = false;
    }
    
    
    /**
     * Tekstikäyttöliittymän käynnistävä luokka.
     */
    public void kaynnista() {
        System.out.println("TERVETULOA KÄYTTÄMÄÄN PAKKAAJAA!");
        
        while (true) {
            this.tulostaKomennot();

            String komento = lukija.nextLine();
            System.out.println("");
            
            switch (komento) {
                case "q":
                    this.exit();
                    break;
                case "1":
                    this.pakkaa();
                    break;
                case "2":
                    this.pura();
                    break;
                case "3":
                    this.vaihdaTilastot();
                    break;
                case "4":
                    this.lzwPakkaa();
                    break;
                case "5":
                    this.lzwPura();
                    break;
                default:
                    System.out.println("Virheellinen komento: " + komento);
                    break;
            }
        }        
    }

    
    /**
     * Ajaa pakaamistoiminnallisuuden Huffman-algoritmilla.
     */
    private void pakkaa() {
        File tiedosto = kysyTiedostonimi(false);
        if (tiedosto == null) {
            return;
        }

        try {
            System.out.println("Pakataan tiedosto '" + tiedosto.getName() + "'...\n");
            long alku = System.nanoTime();
            Pakkaaja pakkaaja = new Pakkaaja(tiedosto);
            File pakattu = pakkaaja.pakkaaTiedosto();
            long loppu = System.nanoTime();
            System.out.println("Tiedosto on pakattu ja tallennettu nimellä:");
            System.out.println(pakattu.getAbsoluteFile());
            if (tilastot) {
                tulostaTilastot(alku, loppu, tiedosto, pakattu);
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }
    
    /**
     * Ajaa pakaamistoiminnallisuuden LZW-algoritmilla - KESKEN.
     */
    private void lzwPakkaa() {
        File tiedosto = kysyTiedostonimi(false);
        if (tiedosto == null) {
            return;
        }

        try {
            System.out.println("Pakataan tiedosto '" + tiedosto.getName() + "'...\n");
            long alku = System.nanoTime();
            LzwPakkaaja lzwPakkaaja = new LzwPakkaaja(tiedosto);
            File pakattu = lzwPakkaaja.pakkaaTiedosto();
            long loppu = System.nanoTime();
            System.out.println("Tiedosto on pakattu ja tallennettu nimellä:");
            System.out.println(pakattu.getAbsoluteFile());
            if (tilastot) {
                tulostaTilastot(alku, loppu, tiedosto, pakattu);
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }

    
    /**
     * Ajaa purkutoiminnallisuuden Huffman-algoritmilla.
     */
    private void pura() {
        File tiedosto = kysyTiedostonimi(true);
        if (tiedosto == null) {
            return;
        }

        try {
            System.out.println("Puretaan tiedosto '" + tiedosto.getName() + "'...\n");
            long alku = System.nanoTime();
            Purkaja purkaja = new Purkaja(tiedosto);
            File purettu = purkaja.puraTiedosto();
            long loppu = System.nanoTime();
            System.out.println("Tiedosto on purettu ja tallennettu nimellä:");
            System.out.println(purettu.getAbsoluteFile());
            if (tilastot) {
                tulostaTilastot(alku, loppu, null, null);
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }

    
    /**
     * Ajaa purkutoiminnallisuuden LZW-algortimilla.
     */
    private void lzwPura() {
        File tiedosto = kysyTiedostonimi(true);
        if (tiedosto == null) {
            return;
        }

        try {
            System.out.println("Puretaan tiedosto '" + tiedosto.getName() + "'...\n");
            long alku = System.nanoTime();
            LzwPurkaja purkaja = new LzwPurkaja(tiedosto);
            File purettu = purkaja.puraTiedosto();
            long loppu = System.nanoTime();
            System.out.println("Tiedosto on purettu ja tallennettu nimellä:");
            System.out.println(purettu.getAbsoluteFile());
            if (tilastot) {
                tulostaTilastot(alku, loppu, null, null);
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }
    
    
    /**
     * Kysyy käyttäjältä pakattavan/purettavan tiedoston nimen.
     */
    private File kysyTiedostonimi(boolean purkaja) {
        String tiedostonimi = "";
        File tiedosto = null;

        while (true) {
            if (purkaja) {
                System.out.println("Anna purettavan tiedoston nimi (ja polku):");
            } else {
                System.out.println("Anna pakattavan tiedoston nimi (ja polku):");
            }
            
            System.out.println("(Jos haluat takaisin päävalikkoon, anna tyhjä nimi.)");

            tiedostonimi = lukija.nextLine();
            tiedosto = new File(tiedostonimi);
            System.out.println("");
            
            if (tiedostonimi.equals("")) {
                return null;
            } else if (!tiedosto.exists()) {
                System.out.println("VIRHE: Tiedostoa '" + tiedostonimi + "' ei löytynyt.\n");
            } else if (tiedosto.isDirectory()) {
                System.out.println("VIRHE: '" + tiedostonimi + "' on hakemisto. Anna yksittäisen tiedoston nimi.\n");
            } else if (!tiedosto.isFile()) {
                System.out.println("VIRHE: '" + tiedostonimi + "' ei ole kelvollinen tiedosto.\n");
            } else {
                return tiedosto;
            }
        }
    }
    
    
    /**
     * Tulostaa tilastot (käytetty aika ja pakkausteho).
     */
    private void tulostaTilastot(long alku, long loppu, File tiedosto, File pakattu) {
        System.out.println("\nTILASTOT:");
        System.out.println("Aikaa kului: " + (loppu - alku) / 1e9 + " s");
        if (tiedosto != null) {
            double pakkaus = 1.0 * pakattu.length() / tiedosto.length();
            System.out.println("Aluperäinen tiedosto: " + (double) tiedosto.length() / 1024 + " kt");
            System.out.println("Pakattu tiedosto: " + (double) pakattu.length() / 1024 + " kt");
            System.out.println("Pakkausteho: " + (double) (1 - pakkaus) * 100 + " %");
        }
    }
    
    
    /**
     * Vaihtaa tilastotulosteet päälle tai pois päältä.
     */
    private void vaihdaTilastot() {
        if (tilastot) {
            tilastot = false;
            System.out.println("Tilastotulosteet on kytetty pois päältä.");
        } else {
            tilastot = true;
            System.out.println("Tilastotulosteet on kytetty päälle.");
            System.out.println("Ohjelma näyttää jatkossa, kuinka nopeasti pakkaaminen ja purkaminen tapahtui, sekä pakatessa pakkaustehon.");
        }
    }
    
    
    /**
     * Käsittelee poikkeuksen.
     */
    private void kasittelePoikkeus(Exception ex) {
        System.out.println("VIRHE:");
        System.out.println(ex.getMessage());
    }
    
    
    /**
     * Tulostaa valittavissa olevat komennot.
     */
    private void tulostaKomennot() {
        System.out.println("\nValitse, mitä haluaisit tehdä:\n");
        System.out.println("[1]\t pakkaa tiedosto");
        System.out.println("[2]\t pura pakattu tiedosto");
        if (tilastot) {
            System.out.println("[3]\t kytke tilastotulosteet pois päältä");
        } else {
            System.out.println("[3]\t kytke tilastotulosteet päälle");
        }
        System.out.println("[q]\t poistu\n");
        System.out.println("Komento + enter: ");
    }
    
    
    /**
     * Sulkee ohjelman.
     */
    private void exit() {
        System.out.println("Kiitos, kun käytit Pakkaajaa.");
        System.exit(0);
    }
    
}