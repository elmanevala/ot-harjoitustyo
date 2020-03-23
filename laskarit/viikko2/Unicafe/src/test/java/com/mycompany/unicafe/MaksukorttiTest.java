package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        // kortilla rahaa nyt 5.10 euroa

        assertEquals("saldo: 5.10", kortti.toString());
    }

    @Test
    public void saldoVaheneeKunRahaaOnTarpeeksiJaSitaNostetaan() {
        kortti.lataaRahaa(500);
        kortti.otaRahaa(100);

        assertEquals("saldo: 4.10", kortti.toString());
    }

    @Test
    public void saldoEiMuutuKunOnLiianVahanJaSitaNostetaan() {
        kortti.lataaRahaa(500);
        kortti.otaRahaa(600);

        assertEquals("saldo: 5.10", kortti.toString());
    }

    @Test
    public void palauttaaTrueJosRahaRiitti() {
        kortti.lataaRahaa(500);

        assertEquals(true, kortti.otaRahaa(100));
    }

    @Test
    public void palauttaaFalseJosRahaEiRiittanyt() {
        kortti.lataaRahaa(500);

        assertEquals(false, kortti.otaRahaa(600));
    }
    
    @Test
    public void palauttaaOikeanSaldon(){
        assertEquals(10, kortti.saldo());
    }

}
