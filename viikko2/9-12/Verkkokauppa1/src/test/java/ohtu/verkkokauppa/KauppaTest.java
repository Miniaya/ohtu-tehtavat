package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa kauppa;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeallaAsiakkaalla() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void kahdenEriTuotteenOstaminenOikeallaVeloituksella() {
        when(viite.uusi()).thenReturn(35);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 8));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("jaakko", "98765");
        
        verify(pankki).tilisiirto(eq("jaakko"), anyInt(), eq("98765"), anyString(), eq(13));
    }
    
    @Test
    public void varastossaTarpeeksiSamaaTuotetta() {
        when(viite.uusi()).thenReturn(13);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "mansikka", 7));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("heikki", "24680");
        
        verify(pankki).tilisiirto(eq("heikki"), anyInt(), eq("24680"), anyString(), eq(14));
    }
    
    @Test
    public void loppunuttaTuotettaEiVoiOstaa() {
        when(viite.uusi()).thenReturn(65);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "jäätelö", 6));
        
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito", 5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("henna", "13579");
        
        verify(pankki).tilisiirto(eq("henna"), anyInt(), eq("13579"), anyString(), eq(6));
    }
    
    @Test
    public void uusiAsiointiNollaaOstoskorin() {
        when(viite.uusi()).thenReturn(18)
                .thenReturn(32);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "leipä", 3));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("hertta", "360247");
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(3));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("hertta", "360247");
        
        // Edellisten ostosten hinta ei ole lisättynä tähän tilisiirtoon
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(6));
    }
    
    @Test
    public void uudessaMaksussaAinaUusiViite() {
        when(viite.uusi()).thenReturn(13)
                .thenReturn(26)
                .thenReturn(39);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "jäätelö", 6));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("hannele", "11111");

        //metodia uusi() kutsuttu kerran
        verify(viite, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("elias", "12345");

        //metodia uusi() kutsuttu kaksi kertaa
        verify(viite, times(2)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("elisa", "37221");

        // metodia uusi() kutsuttu kolme kertaa      
        verify(viite, times(3)).uusi();
    }
    
}

