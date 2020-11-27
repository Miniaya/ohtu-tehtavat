package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    
    public Erotus(TextField syotekentta, TextField tuloskentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, tuloskentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        int luku = sovellus.getTulos() - Integer.valueOf(syotekentta.getText());
        sovellus.setTulos(luku);
        tuloskentta.setText("" + sovellus.getTulos());
        syotekentta.setText("");
        
        if(sovellus.getTulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        
        undo.disableProperty().set(false);
    }
    
    @Override
    public void peru() {
        
    }
}