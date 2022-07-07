package edu.fiuba.algo3.vista.puntaje;

import java.util.Observable;
import java.util.Observer;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vista.tablero.VistaTablero;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VistaPuntaje extends Group implements Observer {

    private HBox contenedor;
    private Text contenedorTexto;
    private long puntaje;
    private VistaTablero vistaTablero;
    
    public VistaPuntaje(VistaTablero vistaTablero, long puntaje) {
        this.puntaje = puntaje;
        this.vistaTablero = vistaTablero;

        this.contenedorTexto = new Text((String.valueOf(puntaje)));
        this.contenedor = new HBox(this.contenedorTexto);
     
        this.vistaTablero.agregarPuntaje(this.contenedor);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.puntaje = (long) arg;
        this.contenedorTexto.setText(String.valueOf(this.puntaje));
        this.vistaTablero.agregarPuntaje(this.contenedor);
    }
    
}
