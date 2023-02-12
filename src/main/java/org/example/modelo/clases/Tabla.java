package org.example.modelo.clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tabla {
    private String nombre;
    private List<String> atributos;
    private List<String> tipoAtributos;
    private String where;
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


}
