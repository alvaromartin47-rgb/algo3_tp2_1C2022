package edu.fiuba.algo3.modelo.vehiculos;

import edu.fiuba.algo3.modelo.celda.Celda;
import edu.fiuba.algo3.modelo.direccion.Direccion;
import edu.fiuba.algo3.modelo.modificador.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Vehiculo {

    protected Celda celdaInicial;
    protected long movimientos;
    protected Tablero tablero;
    protected Direccion direccionActual;

    public Vehiculo(Tablero tablero) {
        this.tablero = tablero;
        this.movimientos = 0;
    }

    public abstract void aplicarModificador(Modificador modificador);
    public abstract void aplicarModificador(Pozo pozo);
    public abstract void aplicarModificador(Piquete piquete);
    public abstract void aplicarModificador(ControlPolicial controlPolicial);
    public  abstract void aplicarModificador(CambioDeVehiculo cambioDeVehiculo);

    public void sorpresa(Sorpresa sorpresa) {
        this.movimientos = sorpresa.aplicarSorpresa(this.movimientos);
        this.actualizarASiguienteCelda();
    }

    public long movimientos() {
        return this.movimientos;
    }
    
    public Celda getPosicion() {
        return this.celdaInicial;
    }

    public void asignarCeldaInicial(Celda celda) {
        this.celdaInicial = celda;
    }

    public void mover(Direccion direccion) {
        this.movimientos++;
        this.direccionActual = direccion;

        this.celdaInicial.mover(this, direccion);
    }

    public void sumarMovimientos(long cantidad) {
        this.movimientos += cantidad;
    }

    public void actualizarASiguienteCelda() {
        this.celdaInicial = this.celdaInicial.buscarSiguiente(this.direccionActual);
    }

    public Boolean estaEn(Celda celda) {
        return this.celdaInicial.equals(celda);
    }

}