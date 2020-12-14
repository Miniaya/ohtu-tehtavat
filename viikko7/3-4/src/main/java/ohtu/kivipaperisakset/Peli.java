package ohtu.kivipaperisakset;

public class Peli {
    
    private KiviPaperiSakset pelimuoto;
    
    public static Peli luoKaksinpeli() {
        return new Peli(new KPSPelaajaVsPelaaja());
    }
    
    public static Peli luoHelppoYksinpeli() {
        return new Peli(new KPSTekoaly());
    }
    
    public static Peli luoVaikeaYksinpeli() {
        return new Peli(new KPSParempiTekoaly());
    }
    
    private Peli(KiviPaperiSakset pelimuoto) {
        this.pelimuoto = pelimuoto;
    }
    
    public void pelaa() {
        this.pelimuoto.pelaa();
    }
}
