package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void rahamaaraAlussaOikea() {

        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void myytyjaLounaitaAlussaNolla() {

        assertEquals(0, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kateisellaEdullisestiMaksettaessaKassaanOikeaMaaraRahaa() {
        paate.syoEdullisesti(500);
        assertEquals(100240, paate.kassassaRahaa());
    }

    @Test
    public void kateisellaMaukkaastiMaksettaessaKassaanOikeaMaaraRahaa() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }

    @Test
    public void kateisellaEdullisestiMaksettaessaTakaisinOikeaSumma() {
        assertEquals(260, paate.syoEdullisesti(500));
    }

    @Test
    public void kateisellaMaukkaastiMaksettaessaTakaisinOikeaSumma() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }

    @Test
    public void riittavassaKateismaksussaMyydytEdullisetLounaatLisaantyy() {
        paate.syoEdullisesti(500);

        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void riittavassaKateismaksussaMyydytMaukkaatLounaatLisaantyy() {
        paate.syoMaukkaasti(500);

        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void liianVahallaKateisellaEdullisestiMaksettaessaKassanSummaSama() {
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void LiianVahallaKateisellaMaukkaastiMaksettaessaKassanSummaSama() {
        paate.syoMaukkaasti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void LiianVahallaKateisellaEdullisestiMaksettaessaKaikkiTakaisin() {
        assertEquals(200, paate.syoEdullisesti(200));
    }

    @Test
    public void LiianVahallaKateisellaMaukkaastiMaksettaessaKaikkiTakaisin() {
        assertEquals(100, paate.syoMaukkaasti(100));
    }

    @Test
    public void liianPienessaKateismaksussaMyydytEdullisetLounaatPysyySamana() {
        paate.syoEdullisesti(100);

        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void liianPienessaKateismaksussaMyydytMaukkaatLounaatPysyySamana() {
        paate.syoMaukkaasti(100);

        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void riittavassaEdullisessaKorttiostossaSummaVeloitetaanKortilta() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void riittavassaMaukkaassaKorttiostossaSummaVeloitetaanKortilta() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void kortillaEdullisestiMaksettaessaKassanSaldoSama() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void kortillaMaukkaastiMaksettaessaKassanSaldoSama() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void riittavassaEdullisessaKorttiostossaPalauttaaTrue() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }

    @Test
    public void riittavassaMaukkaassaKorttiostossaPalauttaaTrue() {
        assertEquals(true, paate.syoMaukkaasti(kortti));    
    }
    
    @Test
    public void riittavassaEdullisessaKorttiostossaEdullisetLounaatLisaantyy() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());    
    }
    
    @Test
    public void riittavassaMaukkaassaKorttiostossaMaukkaatLounaatLisaantyy() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());    
    }
    
    
    
    
        @Test
    public void epaonnistuneessaEdullisessaKorttiostossaKortinSaldoPysyy() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void epaonnistuneessaMaukkaassaKorttiostossaKortinSaldoPysyy() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }


    @Test
    public void epaonnistuneessaEdullisessaKorttiostossaPalauttaaFalse() {
        kortti.otaRahaa(900);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }

    @Test
    public void epaonnistuneessaMaukkaassaKorttiostossaPalauttaaFalse() {
        kortti.otaRahaa(900);
        assertEquals(false, paate.syoMaukkaasti(kortti));    
    }
    
    @Test
    public void epaonnistuneessaEdullisessaKorttiostossaEdullisetPysyy() {
        kortti.otaRahaa(900);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());    
    }
    
    @Test
    public void epaonnistuneessaMaukkaassaKorttiostossaMaukkaatLounaatPysyy() {
        kortti.otaRahaa(900);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());    
    }
    
    @Test
    public void kortillaLisaaRahaaLataamisenJalkeen(){
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kassassaLisaaRahaaLataamisenJalkeen(){
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void kassassaSamaSummaJosLataaNegatiivisen(){
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
        @Test
    public void kortillaSamaSummaJosLataaNegatiivisen(){
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
}
