package edu.fiuba.algo3;

import java.util.ArrayList;

import edu.fiuba.algo3.controlador.ControladorVehiculo;
import edu.fiuba.algo3.modelo.calle.Calle;
import edu.fiuba.algo3.modelo.celda.Celda;
import edu.fiuba.algo3.modelo.registro.Puntaje;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vehiculos.Moto;
import edu.fiuba.algo3.modelo.vehiculos.Vehiculo;
import edu.fiuba.algo3.vista.celda.VistaCeldaLlegada;
import edu.fiuba.algo3.vista.modificador.VistaModificador;
import edu.fiuba.algo3.vista.puntaje.VistaPuntaje;
import edu.fiuba.algo3.vista.*;
import edu.fiuba.algo3.vista.tablero.VistaTablero;
import edu.fiuba.algo3.vista.vehiculo.VistaVehiculo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

/**
 * JavaFX App
 */
public class App extends Application {

    private TableView<Puntaje> tablaDePuntajes;

    private VistaTablero vistaTablero;
    private VistaVehiculo vistaVehiculo;
    private VistaPuntaje vistaPuntaje;

    private Tablero tablero;

    private Stage escenario;

    private Scene escenaJuego;
    private VBox contenedor;
    private BotonIrAMenu botonIrAMenu;

    private Scene escenaMenu;
    private VBox vbox2;
    private BotonIrAJuego botonIrAJuego;
    // private BotonIrAPuntajes botonIrAPuntajes;
    private Button botonIrAPuntajes;


    private Button botonNoJuego;
    private BotonSiSalirDeJuego botonSiJuego;

    private BotonNo botonNoMenu;
    private Button botonSiMenu;

    private Scene escenaMejoresPuntajes;
    private Scene escenaSalirDeJuego;
    private Scene escenaSalirDeMenu;

    private Scene escenaSalirDeMejoresPuntajes;


    @Override
    public void start(Stage stage) {
        escenario = stage;
        escenario.setTitle("GPS Challenge");

        escenaJuego = crearEscenaJuego();
        escenaMenu = crearEscenaMenu();
        escenaSalirDeJuego = crearEscenaSalirDeJuego();
        escenaMejoresPuntajes = crearEscenaMejoresPuntajes();
        escenaSalirDeMenu = crearEscenaSalirDeMenu();
        escenaSalirDeMejoresPuntajes = crearEscenaSalirDeMejoresPuntajes();

        escenario.setScene(escenaMenu);
        escenario.show();

    }

    private Scene crearEscenaJuego() {

        tablero = new Tablero(15, 15);
        tablero.generarAleatorio();
        Vehiculo vehiculo = new Moto(tablero);
        tablero.usarVehiculo(vehiculo);

        Celda salida = new Celda(1, 0);
        Celda llegada = new Celda(5, 14);

        tablero.iniciarEn(salida);
        tablero.finalizarEn(llegada);

        this.vistaTablero = new VistaTablero(tablero);
        this.vistaTablero.agregarVistaAPosicion(new VistaCeldaLlegada(), llegada);

        for (Calle calle : tablero.getCalles()) {
            ArrayList<Celda> esquinas = calle.obtenerEsquinas();
            String nombre = calle.getModificador().getNombre();

            VistaModificador vista = new VistaModificador(this.vistaTablero, nombre, esquinas.get(0), esquinas.get(1));
            calle.addObserver(vista);
        }

        this.vistaVehiculo = new VistaVehiculo(this.vistaTablero, tablero);
        this.vistaVehiculo.setFocusTraversable(true);

        ControladorVehiculo controlador = new ControladorVehiculo(tablero);

        this.vistaPuntaje = new VistaPuntaje(this.vistaTablero, tablero.movimientos());

        tablero.addObserver(vistaPuntaje);
        vehiculo.addObserver(vistaVehiculo);

        botonIrAMenu = new BotonIrAMenu(this.escenario, crearEscenaSalirDeJuego());
        botonIrAMenu.setFocusTraversable(false);

        contenedor = new VBox();

        contenedor.getChildren().add(this.vistaTablero);
        contenedor.getChildren().add(this.vistaVehiculo);
        contenedor.setOnKeyPressed(controlador);

        contenedor.getChildren().add(botonIrAMenu);

        contenedor.setAlignment(Pos.CENTER);

        contenedor.setSpacing(10);

        escenaJuego = new Scene(contenedor, 640, 640);
        return escenaJuego;

    }

    private Scene crearEscenaMenu() {

        Label label = new Label("Nombre:");

        IngresoDeNombre ingresoDeNombre = new IngresoDeNombre();

        // botonIrAPuntajes = new BotonIrAPuntajes(this.escenario, crearEscenaMejoresPuntajes());
        botonIrAPuntajes = new Button("Ir a puntajes");
        botonIrAPuntajes.setOnAction(e -> cambiarEscenas(crearEscenaMejoresPuntajes()));

        botonIrAJuego = new BotonIrAJuego(ingresoDeNombre, this.tablero, this.escenario, crearEscenaSalirDeMenu());

        vbox2 = new VBox(label, ingresoDeNombre, botonIrAJuego, botonIrAPuntajes);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(10);
        vbox2.setPadding(new Insets(20));

        escenaMenu = new Scene(vbox2, 640, 640);

        return escenaMenu;
    }

    private TableView<Puntaje> crearTablaDePuntajes() {

        tablaDePuntajes = new TableView<>();

        //Columna Usuarios
        TableColumn<Puntaje, String> columnaUsuario = new TableColumn<>("Usuario");
        columnaUsuario.setMinWidth(20);
        columnaUsuario.setCellValueFactory(new PropertyValueFactory<Puntaje, String>("usuario"));

        //Columna Puntajes
        TableColumn<Puntaje, Long> columnaPuntaje = new TableColumn<>("Puntaje");
        columnaPuntaje.setMinWidth(20);
        columnaPuntaje.setCellValueFactory(new PropertyValueFactory<Puntaje, Long>("puntaje"));

        tablaDePuntajes.getColumns().add(columnaUsuario);
        tablaDePuntajes.getColumns().add(columnaPuntaje);
        tablaDePuntajes.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        tablaDePuntajes.setItems(getPuntajes());

        return tablaDePuntajes;
    }

    public void reiniciarJuego() {
        this.juego.reiniciar();
        escenaJuego = crearEscenaJuego();
        this.cambiarEscenas(escenaJuego);
    }

    public ObservableList<Puntaje> getPuntajes() { //HACER LISTA SOLO 5
        ObservableList<Puntaje> puntajes = FXCollections.observableArrayList();

        puntajes.addAll(tablero.getPuntajes());

        // puntajes.addAll(
        //         new Puntaje("juan", 3),
        //         new Puntaje("juan", 3),
        //         new Puntaje("a", 100)
        // );
        return puntajes;
    }

    private Scene crearEscenaMejoresPuntajes() {
        Label label = new Label("Mejores Puntajes");

        Button botonSalirDePuntajes = new Button("Clickear para ir al Menu");
        botonSalirDePuntajes.setOnAction(e -> cambiarEscenas(escenaSalirDeMejoresPuntajes)); //Stack overflow

        VBox vbox6 = new VBox(10, label, crearTablaDePuntajes(), botonSalirDePuntajes);
        vbox6.setAlignment(Pos.CENTER);
        vbox6.setPadding(new Insets(20));

        escenaMejoresPuntajes = new Scene(vbox6, 640, 640);

        return escenaMejoresPuntajes;
    }

    private Scene crearEscenaSalirDeMenu() {

        Label label = new Label("Desea ir al Juego?");

        botonNoMenu = new BotonNo(escenario, escenaMenu); //se traba, al salir de juego lo deberia cerrar

        botonSiMenu = new Button("SI");
        botonSiMenu.setOnAction(e -> {
            escenaJuego = crearEscenaJuego();
            cambiarEscenas(escenaJuego);
        });
        VBox vbox7 = new VBox(label, botonNoMenu, botonSiMenu);
        vbox7.setAlignment(Pos.CENTER);

        escenaSalirDeMenu= new Scene(vbox7, 640, 640);

        return escenaSalirDeMenu;
    }

    private Scene crearEscenaSalirDeJuego() {

        Label label = new Label("Desea ir al Menu?");

        Button botonNo = new Button("NO");
        botonNo.setOnAction(e -> cambiarEscenas(escenaJuego));
        Button botonSi = new Button("SI");
        botonSi.setOnAction(e -> cambiarEscenas(escenaMenu));
        VBox vbox8 = new VBox(label, botonNo, botonSi);
        vbox8.setAlignment(Pos.CENTER);

        escenaSalirDeJuego= new Scene(vbox8, 640, 640);
        return escenaSalirDeJuego;

    }

    private Scene crearEscenaSalirDeMejoresPuntajes() {

        Label label = new Label("Desea ir al Menu?");

        BotonNo botonNoMenu2 = new BotonNo(this.escenario, crearEscenaMejoresPuntajes());

        BotonSi botonSiMenu2 = new BotonSi(this.escenario, crearEscenaMenu());

        VBox vbox9 = new VBox(label, botonNoMenu2, botonSiMenu2);
        vbox9.setAlignment(Pos.CENTER);
        vbox9.setSpacing(10);

        escenaSalirDeMejoresPuntajes = new Scene(vbox9, 640, 640);

        return escenaSalirDeMejoresPuntajes;
    }

    public void cambiarEscenas(Scene escena){
        escenario.setScene(escena);
    }

    public static void main(String[] args) {
        launch();
    }

}