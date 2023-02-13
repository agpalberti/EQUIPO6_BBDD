package org.example.controlador;

import org.example.modelo.GestorBBDD;
import org.example.modelo.clases.Tabla;
import org.example.vista.GUI;

import java.sql.SQLException;
import java.util.List;

public class Controlador {
    private static final GestorBBDD model = new GestorBBDD();

    private final GUI gui = new GUI(model);

    // Al iniciar la clase, se conecta a la base de datos
    static {
        model.connect();
    }

    // Devuelve la opción elegida mediante la GUI
    public int startMenu() {

        int intMenu = gui.startMenu();
        if (intMenu != -1) return intMenu;
        else gui.printIncorrectInput();
        return -1;
    }

    // Comunica la tabla creada mediante la interfaz de usuario hacia la base de datos, devuelve boolean según si se ha podido crear o no
    public boolean crearTabla() {
        Tabla tabla = gui.crearTabla();
        try {
            if (model.createTable(tabla)) {
                gui.printCorrectInput();
                return true;
            } else return false;
        } catch (SQLException e) {
            gui.printIncorrectInput();
            return false;
        }
    }

    // Comunica la tabla a actualizar a la BD
    public boolean actualizarTabla() {
        Tabla tabla = gui.actualizarTabla();
        try {
            if (model.actualizarRegistro(tabla)) {
                gui.printCorrectInput();
                return true;
            } else return false;
        } catch (SQLException e) {
            gui.printIncorrectInput();
            return false;
        }
    }

    // Comunica la tabla a crear desde la GUI hacia la BBDD
    public boolean insertarRegistro() {
        Tabla tabla = gui.insertarRegistroTabla();
        try {
            if (model.insertarRegistro(tabla)) {
                gui.printCorrectInput();
                return true;
            } else return false;
        } catch (SQLException e) {
            gui.printIncorrectInput();
            return false;
        }
    }

    // Igual para borrar
    public boolean borrarRegistro() {
        Tabla tabla = gui.eliminarRegistrosTabla();
        try {
            if (model.eliminarRegistro(tabla)) {
                gui.printCorrectInput();
                return true;
            } else return false;
        } catch (SQLException e) {
            gui.printIncorrectInput();
            return false;
        }
    }

    // Imprime todos los datos elegidos de una tabla
    public void consultar() {
        Tabla tabla = gui.consultarTabla();
        try {
            List<String> lista = model.consultarTabla(tabla);
            for (String s : lista) {
                System.out.println(s);
            }
        } catch (SQLException e) {
            gui.printIncorrectInput();
        }
    }

    // Acaba la conexión e imprime que la interfaz terminará
    public void onExit() {
        try {
            model.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gui.exit();
    }

    // Imprime todas las tablas existentes
    public void printTablas() {
        System.out.println("\nTablas disponibles:");
        try {
            for (String tabla : model.getListaTablas()) {
                System.out.println("- " + tabla);
            }
        } catch (Exception ignored) {
        }
    }
}
