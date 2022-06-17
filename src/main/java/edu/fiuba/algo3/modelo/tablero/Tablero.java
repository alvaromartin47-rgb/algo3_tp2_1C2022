package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.celda.Celda;
import edu.fiuba.algo3.modelo.direccion.Direccion;
// import edu.fiuba.algo3.modelo.generadorDeCiudad.GeneradorDeCiudad;
import edu.fiuba.algo3.modelo.vehiculos.Vehiculo;

public class Tablero {
    
    private Vehiculo vehiculo;
    // private GeneradorDeCiudad generador;

    public Tablero(int filas, int columnas) {
        // this.generador = new GeneradorDeCiudad(filas, columnas);
    }

    public void iniciarEn(Celda celda) {
        this.vehiculo.asignarCeldaInicial(celda);
    }

    public void agregarvehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void mover(Direccion direccion) {
        this.vehiculo.mover(direccion);
    }

    public void reemplazarVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Celda obtenerPosicion() {
        return this.vehiculo.getPosicion();
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

}
