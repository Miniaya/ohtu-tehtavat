
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusta(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        
        alusta(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko ei voi olla negatiivinen");//heitin vaan jotain :D
        }

        alusta(kapasiteetti, kasvatuskoko);
    }
    
    private void alusta(int kapasiteetti, int kasvatuskoko) {
        joukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            joukko[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            
            if (alkioidenLkm % joukko.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }
    
    private void kasvataTaulukkoa() {
        int[] pieniJoukko = joukko;
        joukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(pieniJoukko, joukko);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                joukko[kohta] = 0;
                break;
            }
        }
        
        if (kohta != -1) {    
            tiivistaTaulukko(kohta);
            return true;
        }
        return false;
    }
    
    private void tiivistaTaulukko(int kohta) {
        int apu;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = joukko[j];
                joukko[j] = joukko[j + 1];
                joukko[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            default:
                String tuotos = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuotos += joukko[i];
                    tuotos += ", ";
                }
                tuotos += joukko[alkioidenLkm - 1];
                tuotos += "}";
                return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukko, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        IntJoukko x = lisaaEkaTaulukko(a);
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        int[] bTaulu = b.toIntArray();
        IntJoukko x = lisaaEkaTaulukko(a);
        for (int i = 0; i < bTaulu.length; i++) {
            x.poista(bTaulu[i]);
        }
        return x;
    }
    
    private static IntJoukko lisaaEkaTaulukko(IntJoukko a) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        return x;
    }
        
}