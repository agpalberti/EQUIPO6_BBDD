package org.example.modelo.clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Clase para la transferencia de datos, que hace intermediaria entre el controlador, el gestor y nuestra interfaz
public class Tabla {
    // Nombre de la tabla, ya sea a la hora de crearla o buscarla
    private String nombre;
    // Lista de columnas de la tabla
    private List<String> atributos;
    // Tipo que almacena cada tabla
    private List<String> tipoAtributos;
    // Claúsula where para una sentencia sql
    private String where;
    // Valor al insertar un nuevo dato en una columna
    private List<String> valoresAtributos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<String> atributos) {
        this.atributos = atributos;
    }

    public List<String> getTipoAtributos() {
        return tipoAtributos;
    }

    public void setTipoAtributos(List<String> tipoAtributos) {
        this.tipoAtributos = tipoAtributos;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public List<String> getValoresAtributos() {
        return valoresAtributos;
    }

    public void setValoresAtributos(List<String> valoresAtributos) {
        this.valoresAtributos = valoresAtributos;
    }

    public Tabla(String nombreTabla, String[] listaAtributos, String[] listaTipoAtributos) {
        setNombre(nombreTabla);
        setAtributos(Arrays.stream(listaAtributos).toList());
        setTipoAtributos(Arrays.stream(listaTipoAtributos).toList());
    }

    public Tabla(String nombreTabla, String[] listaAtributos, String where) {
        setNombre(nombreTabla);
        setAtributos(Arrays.stream(listaAtributos).toList());
        setWhere(where);
    }

    public Tabla(String nombreTabla, String where) {
        setNombre(nombreTabla);
        setWhere(where);
    }

    public Tabla(String nombreTabla, String[] valoresAtributos) {
        setNombre(nombreTabla);
        setValoresAtributos(Arrays.stream(valoresAtributos).toList());
    }

    public Tabla(String nombreTabla, String where, String[] valores) {
        setNombre(nombreTabla);
        setValoresAtributos(Arrays.stream(valores).toList());
        setWhere(where);
    }

    // String que fusiona las columnas con el tipo de dato que almacenan, cada una separada por comas

    public String fusionarAtributosTipos() {

        StringBuilder fusion = new StringBuilder();

        for (int i = 0; i < getAtributos().size(); i++) {
            fusion.append(getAtributos().get(i)).append(" ").append(getTipoAtributos().get(i)).append(",");
        }

        return fusion.deleteCharAt(fusion.length() - 1).toString();

    }

    // String que junta los datos a insertar en un solo string
    public String valoresAtributosToString() {
        StringBuilder fusion = new StringBuilder();

        for (String valor : valoresAtributos) {
            fusion.append(" '").append(valor).append("',");
        }

        return fusion.deleteCharAt(fusion.length() - 1).toString();

    }
}
