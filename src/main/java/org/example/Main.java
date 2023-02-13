package org.example;

import org.example.controlador.Controlador;

public class Main {
    public static void main(String[] args)
    {

        Controlador controlador = new Controlador();
        boolean bucle = true;

        do{

            switch (controlador.startMenu()) {
                case 1 -> controlador.crearTabla();
                case 2 -> controlador.consultar();
                case 3 -> controlador.actualizarTabla();
                case 4 -> controlador.borrarRegistro();
                case 5 -> controlador.insertarRegistro();
                case 6 -> {
                    controlador.onExit();
                    bucle = false;
                }
            }

        } while (bucle);

    }
}