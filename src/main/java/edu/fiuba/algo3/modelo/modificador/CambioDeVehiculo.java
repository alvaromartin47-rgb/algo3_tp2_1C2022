package edu.fiuba.algo3.modelo.modificador;

import edu.fiuba.algo3.modelo.vehiculos.Vehiculo;

public class CambioDeVehiculo implements Modificador {

    @Override
    public void cruzarCon(Vehiculo vehiculo) {
        vehiculo.reemplazarVehiculo();
    }

    @Override
    public String simbolo() {
        return "CV";
    }
        
}
