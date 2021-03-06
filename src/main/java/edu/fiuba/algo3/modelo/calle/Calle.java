package edu.fiuba.algo3.modelo.calle;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.celda.Celda;
import edu.fiuba.algo3.modelo.modificador.Modificador;
import edu.fiuba.algo3.modelo.vehiculos.Vehiculo;
import edu.fiuba.algo3.modelo.modificador.Nulo;

public class Calle {

    private Modificador modificador;
    private ArrayList<Celda> celdas;

    public Calle(Celda esq1, Celda esq2, Modificador modificador) {
        this.celdas = new ArrayList<Celda>();
        this.celdas.add(esq1);
        this.celdas.add(esq2);
        this.modificador = modificador;
    }

    public void cruzarCon(Vehiculo vehiculo) {
        this.modificador.cruzarCon(vehiculo);
        // this.modificador = new Nulo();
    }

    public Celda siguienteEsquina(Celda esquinaActual) {
        // Solo funciona para 2 celdas

        if (esquinaActual.equals(this.celdas.get(0))) {
            return this.celdas.get(1);
        } else return this.celdas.get(0);
    }

    public ArrayList<Celda> obtenerEsquinas() {
        return this.celdas;
    }

    public boolean contiene(Celda celda) {
        return this.celdas.contains(celda);
    }

    public void agregarCelda(Celda celda) {
        celdas.add(celda);
    }

    public void borrarModificadores() {
        this.modificador = new Nulo();
    }

    public void agregarModificador(Modificador mod) {
        this.modificador = mod;
    }

    public Modificador getModificador() {
        return this.modificador;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Calle)) {
            return false;
        }

        Calle c = (Calle) obj;
        
        for (Celda celda : c.celdas) {
            if (!this.celdas.contains(celda)) {
                return false;
            }
        }
        
        return true;
    }
    
}
