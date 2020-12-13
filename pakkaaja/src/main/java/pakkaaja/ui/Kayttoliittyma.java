
package pakkaaja.ui;

import java.io.*;
import java.util.Scanner;
import pakkaaja.logiikka.lzw.LzwPakkaaja;
import pakkaaja.logiikka.lzw.LzwPurkaja;
import pakkaaja.logiikka.huffman.HuffmanPakkaaja;
import pakkaaja.logiikka.huffman.HuffmanPurkaja;
import pakkaaja.logiikka.Pakkaaja;
import pakkaaja.logiikka.Purkaja;

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
                    this.vertaaAlgoritmeja();
                    break;
                default:
                    System.out.println("Virheellinen komento: " + komento);
                    break;
            }
        }        
    }

    
    /**
     * Ajaa pakaamistoiminnallisuuden.
     */
    private void pakkaa() {
        File tiedosto = kysyTiedostonimi(false);
        if (tiedosto == null) {
            return;
        }
        
        try {
            Pakkaaja pakkaaja = kysyPakkausalgoritmi(tiedosto);
            if (pakkaaja == null) {
                return;
            }

            System.out.println("Pakataan tiedosto '" + tiedosto.getName() + "'...\n");
            File pakattu = pakkaaja.pakkaaTiedosto();
            System.out.println("Tiedosto on pakattu ja tallennettu nimellä:");
            System.out.println(pakattu.getAbsoluteFile());
            if (tilastot) {
                System.out.println("\n" + pakkaaja.getTilasto());
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }

    
    /**
     * Ajaa purkutoiminnallisuuden.
     */
    private void pura() {
        try {
            File tiedosto = null;
            Purkaja purkaja = null;
            
            while (purkaja == null) {
                tiedosto = kysyTiedostonimi(true);
                if (tiedosto == null) {
                    return;
                }
                
                String nimi = tiedosto.getName();
                String paate = "";
                int erotin = nimi.lastIndexOf('.');
                if (erotin != -1) {
                    paate = nimi.substring(erotin);
                }

                if (paate.equals(".huff")) {
                    purkaja = new HuffmanPurkaja(tiedosto);
                } else if (paate.equals(".lzw")) {
                    purkaja = new LzwPurkaja(tiedosto);
                } else {
                    System.out.println("VIRHE: Purettavaksi valitun tiedoston on oltava tyyppiä .huff tai .lzw.\n");
                }   
            }
            
            System.out.println("Puretaan tiedosto '" + tiedosto.getName() + "'...\n");
            File purettu = purkaja.puraTiedosto();
            System.out.println("Tiedosto on purettu ja tallennettu nimellä:");
            System.out.println(purettu.getAbsoluteFile());
            if (tilastot) {
                System.out.println("\n" + purkaja.getTilasto());
            }
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        }
    }
    
    
    /**
     * Ajaa algoritmien vertailutoiminnallisuuden.
     */
    private void vertaaAlgoritmeja() {
        /* File tiedosto = kysyTiedostonimi(false);
        if (tiedosto == null) {
            return;
        }
        
        try {
            Pakkaaja pakkaaja = new HuffmanPakkaaja(tiedosto);
            if (pakkaaja == null) {
                return;
            }

            long alku = System.nanoTime();
            File pakattu = pakkaaja.pakkaaTiedosto();
            long loppu = System.nanoTime();
            //tulostaTilastot(alku, loppu, tiedosto, pakattu);
        } catch (Exception ex) {
            kasittelePoikkeus(ex);
        } */
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
     * Kysyy käyttäjältä käytettävän pakkausalgoritmin ja palauttaa sen.
     */
    private Pakkaaja kysyPakkausalgoritmi(File tiedosto) throws IOException {
        Pakkaaja pakkaaja = null;
        
        while (pakkaaja == null) {
            System.out.println("Valitse pakkausalgoritmi:\n");
            System.out.println("[1]\tHuffman");
            System.out.println("[2]\tLZW");
            System.out.println("[0]\tPalaa päävalikkoon\n");
            System.out.println("Komento + enter:");
            String algoritmiKomento = lukija.nextLine();
            System.out.println("");
            switch (algoritmiKomento) {
                case "1":
                    pakkaaja = new HuffmanPakkaaja(tiedosto);
                    break;
                case "2":
                    pakkaaja = new LzwPakkaaja(tiedosto);
                    break;
                case "0":
                    return null;
                default:
                    System.out.println("Virheellinen komento: " + algoritmiKomento);
            }
        }
        
        return pakkaaja;
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
        System.out.println(ex.toString());
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
        System.out.println("[4]\t vertaa pakkausalgoritmeja");
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