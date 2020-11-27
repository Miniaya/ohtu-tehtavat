package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    
    protected TextField syotekentta, tuloskentta;
    protected Button nollaa, undo;
    protected Sovelluslogiikka sovellus;
    
    public Komento(TextField syotekentta, TextField tuloskentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    public abstract void suorita();
    
    public abstract void peru();
    
}
