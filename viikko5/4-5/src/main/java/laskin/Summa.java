package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    
    private int edellinen;
    
    public Summa(TextField syotekentta, TextField tuloskentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(syotekentta, tuloskentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        edellinen = sovellus.getTulos();
        int luku = Integer.valueOf(syotekentta.getText()) + edellinen;
        sovellus.setTulos(luku);
        tuloskentta.setText("" + sovellus.getTulos());
        syotekentta.setText("");
        
        disableProperties();
    }
    
    @Override
    public void peru() {
        sovellus.setTulos(edellinen);
        tuloskentta.setText("" + edellinen);
        syotekentta.setText("");
        
        disableProperties();
        undo.disableProperty().set(true);
    }
    
    private void disableProperties() {
        if(sovellus.getTulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        
        undo.disableProperty().set(false);
    }
}
