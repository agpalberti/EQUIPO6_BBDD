package org.example.modelo;

import com.mysql.cj.xdevapi.Table;
import org.example.modelo.clases.Tabla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorBBDD {
    private static final String URL = "jdbc:mysql://db4free.net:3306/equipo6hlc";
    private static final String USUARIO = "equipo6hlc";
    private static final String CLAVE = "equipo6hlc";

    private Connection conn = null;

    public void connect() {

        try {
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión realizada correctamente");

        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }
    }

    public void disconnect() throws SQLException {
        if (!conn.isClosed()) {
            conn.close();
            System.out.println("Desconexión realizada correctamente");
        } else {
            System.out.println("No existe la conexión");
        }
    }

    public boolean createTable(Tabla table) throws SQLException {
        if (conn != null && !conn.isClosed()){
            try {
                PreparedStatement ps = conn.prepareStatement(Sentencias.createTable);
                return true;
            } catch (Exception e){
                conn.rollback();
                e.getStackTrace();
            }
        }
        return false;
    }

    private void prepareQuery(Tabla table){
        String query = "CREATE TABLE " + table.getNombre() + table.getAtributos();
    }

}
