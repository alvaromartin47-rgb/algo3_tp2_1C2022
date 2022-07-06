package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorBotonIrAJuego;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BotonIrAJuego extends Button {

    public BotonIrAJuego(IngresoDeNombre ingresoDeNombre,Tablero tablero, Stage stage, Scene scene){
        super();
        this.setText("Clickear para Jugar");
        this.setMinHeight(20);
        this.setOnAction(new ControladorBotonIrAJuego(ingresoDeNombre, tablero, stage, scene));
    }
}