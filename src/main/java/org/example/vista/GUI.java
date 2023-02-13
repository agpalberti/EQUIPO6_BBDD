package org.example.vista;

import org.example.modelo.GestorBBDD;
import org.example.modelo.clases.Tabla;
import org.example.vista.strings.Literales;

import java.util.List;
import java.util.Scanner;

public class GUI {

    GestorBBDD model;
    static Scanner s = new Scanner(System.in);

    public GUI(GestorBBDD model) {
        this.model = model;
    }

    // Imprime el menú principal y obtiene el int introducido
    public int startMenu() {

        try{
            int input = s.nextInt();

            if (input > 0 && input <= 6) {
                return input;
            } else return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    // Pide los datos para crear una tabla
    public Tabla crearTabla() {
        try {
            System.out.println(Literales.nombreTabla);
            String nombreTabla = s.nextLine();
            System.out.println(Literales.atributos);
            String atributos = s.nextLine();
            System.out.println(Literales.tipoAtributos);
            String tipoAtributos = s.nextLine();

            //Ahora secuencio atributos y tipoAtributos en lista y los paso en Tabla
            String[] listaAtributos = atributos.split(",");
            String[] listaTipoAtributos = tipoAtributos.split(",");

            if (listaAtributos.length == listaTipoAtributos.length && !nombreTabla.isBlank() && listaAtributos.length != 0) {
                //Si miden lo mismo ambas listas es que no se ha olvidado de introducir un campo y puedo pasarlo
                return new Tabla(nombreTabla, listaAtributos, listaTipoAtributos);
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

    // Pide los datos para hacer una consulta
    public Tabla consultarTabla() {

        try{
            String nombreTabla = s.nextLine();
            getColumnas(nombreTabla);
            System.out.println(Literales.atributos);
            String atributos = s.nextLine();
            System.out.println(Literales.clausulaWhere);
            String where = s.nextLine();

            String[] listaAtributos = atributos.split(",");

            if (!nombreTabla.isBlank() && listaAtributos.length != 0 && !where.isBlank()) {
                return new Tabla(nombreTabla, listaAtributos, where);
            } else return null;
        } catch (Exception e) {
            return null;
        }
    }

    // Pide los datos para actualizar un registro
    public Tabla actualizarTabla() {

        try {
            System.out.println(Literales.nombreTabla);
            String nombreTabla = s.nextLine();
            getColumnas(nombreTabla);
            System.out.println(Literales.insertarValores);
            String valores = s.nextLine();
            System.out.println(Literales.clausulaWhere);
            String where = s.nextLine();
            //Si no introduce un where se pasa un null
            if (where.isBlank()) {
                where = null;
            }

            String[] listaValores = valores.split(",");

            if (!nombreTabla.isBlank() && listaValores.length != 0) {
                return new Tabla(nombreTabla, where, listaValores);
            } else return null;
        } catch (Exception e) {
            return null;
        }
    }


    // Pide los datos para eliminar un registro
    public Tabla eliminarRegistrosTabla() {

        try{
            String nombreTabla = s.nextLine();
            getColumnas(nombreTabla);
            System.out.println(Literales.clausulaWhere);
            String where = s.nextLine();

            if (!nombreTabla.isBlank() && !where.isBlank()) {
                return new Tabla(nombreTabla, where);
            } else return null;

        } catch (Exception e) {
            return null;
        }
    }

    // Pide los datos para insertar un registro
    public Tabla insertarRegistroTabla() {

        try{
            String nombreTabla = s.nextLine();
            System.out.println(Literales.insertarRegistro);
            String valores = s.nextLine();

            String[] listaValores = valores.split(",");

            if (!nombreTabla.isBlank() && listaValores.length != 0) {
                return new Tabla(nombreTabla, listaValores);
            } else return null;

        } catch (Exception e) {
            return null;
        }
    }

    public void getColumnas(String tabla) {
        try {
            List<String> lista = model.getListaColumnas(tabla);
            if (!lista.isEmpty()) {
                System.out.println("\nColumnas de la tabla " + tabla + ":");

                for (String columna : lista) {
                    System.out.println("- " + columna);
                }
            }
        } catch (Exception ignored) {
        }

    }

    public static void exit(){
        System.out.println(Literales.exit);
    }

}