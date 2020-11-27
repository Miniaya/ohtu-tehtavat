package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    public Nollaa(TextField syotekentta, TextField tuloskentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, tuloskentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        sovellus.setTulos(0);
        tuloskentta.setText("" + sovellus.getTulos());
        syotekentta.setText("");
        
        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }
    
    @Override
    public void peru() {
        
    }
}
