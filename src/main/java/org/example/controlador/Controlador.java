package org.example.controlador;

import org.example.modelo.GestorBBDD;
import org.example.modelo.clases.Tabla;
import org.example.vista.GUI;

import java.sql.SQLException;
import java.util.List;

public class Controlador {
    private static final GestorBBDD model = new GestorBBDD();

    private final GUI gui = new GUI(model);

    static {
        model.connect();
    }

    public int startMenu() {

        int intMenu = gui.startMenu();
        if (intMenu != -1) return intMenu;
        else gui.printIncorrectInput();
        return -1;
    }

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

    public void onExit() {
        try {
            model.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gui.exit();
    }

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
