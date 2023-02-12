package org.example.modelo;


import org.example.modelo.clases.Tabla;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean createTable(Tabla tabla) throws SQLException {
        if (conn != null && !conn.isClosed()){
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String sql = "CREATE TABLE " + tabla.getNombre() + " (" + tabla.fusionarAtributosTipos() + ")";
                stmt.executeUpdate(sql);
                conn.commit();
                return true;
            } catch (Exception e){
                System.out.println(e);
                conn.rollback();
            }
        }
        return false;
    }

    public List<String> consultarTabla(Tabla tabla) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            try {
                if (conn != null && !conn.isClosed()) {
                    String sql = "SELECT * FROM " + tabla.getNombre();
                    if (tabla.getWhere() != null) {
                        sql += " WHERE " + tabla.getWhere();
                    }
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    return iterateResultSet(tabla,stmt);
                }
            } catch (Exception e) {
                System.out.println("Error en la consulta");
            }
        }
        return null;
    }

    private List<String> iterateResultSet (Tabla table, PreparedStatement ps) throws SQLException {
        List<String> listaResult = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            for (String atributo : table.getAtributos()) {
                try {
                    listaResult.add(resultSet.getString(atributo));
                } catch (Exception ignored) {
                }
            }
        }
        return listaResult;
    }

    public boolean actualizarRegistro(Tabla tabla) throws SQLException{
        if (conn != null && !conn.isClosed()){
            try{
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String sql = "UPDATE " + tabla.getNombre() + " SET ";

                List<String> columnNames = getListaColumnas(tabla.getNombre());

                for (int i = 0; i < tabla.getValoresAtributos().size(); i++) {
                    assert columnNames != null;
                    sql += columnNames.get(i) + " = " + "'" + tabla.getValoresAtributos().get(i) + "'";
                    if (i != tabla.getValoresAtributos().size() - 1) {
                        sql += ", ";
                    }
                }
                if (tabla.getWhere() != null) {
                    sql += " WHERE " + tabla.getWhere();
                }
                stmt.executeUpdate(sql);
                conn.commit();
                return true;
            }
            catch (Exception e){
                conn.rollback();
            }
        }
        return false;
    }

    public boolean insertarRegistro(Tabla tabla) throws SQLException{
        if (conn != null && !conn.isClosed()){
            try{
                conn.setAutoCommit(false);
                Statement ps = conn.createStatement();
                String sql = "INSERT INTO " + tabla.getNombre() + " VALUES " + "(" + tabla.valoresAtributosToString() + ")";
                ps.execute(sql);
                conn.commit();
                return true;
            }
            catch (Exception e){
                System.out.println(e);
                conn.rollback();
            }
        }
        return false;
    }

    public boolean eliminarRegistro(Tabla tabla) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            try {
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM " + tabla.getNombre();
                if (tabla.getWhere() != null) {
                    sql += " WHERE " + tabla.getWhere();
                }
                stmt.executeUpdate(sql);
                conn.commit();
                return true;
            } catch (Exception e) {
                System.out.println(e);
                conn.rollback();
            }
        }
        return false;
    }

    public List<String> getListaColumnas(String nombreTabla) throws SQLException {
        List<String> columnas = new ArrayList<>();
        if (conn != null && !conn.isClosed()) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + nombreTabla + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                columnas.add(rs.getString("COLUMN_NAME"));
            }
            return columnas;
        }
        return null;
    }


    public List<String> getListaTablas() throws SQLException {
        List<String> listaTablas = new ArrayList<>();

        if (conn != null && !conn.isClosed()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);
            while (resultSet.next()) {
                listaTablas.add(resultSet.getString("TABLE_NAME"));
            }
        }

        return listaTablas;
    }
}
