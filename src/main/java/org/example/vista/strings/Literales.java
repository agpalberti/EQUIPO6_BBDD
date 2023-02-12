package org.example.vista.strings;


/**
 * Lista de strings utilizados por nuestra interfaz gráfica
 */
public record Literales() {
    public static final String mainMenu = "\nBienvenido a la BBDD del Restaurante. Las tablas por defecto son:\n" +
            "- Camareros\n" +
            "- Carta\n" +
            "- Cocineros\n" +
            "- Mesas\n" +
            "- Ordenes\n" +
            "- Reservas\n\n" +

            "Elija una opción:\n" +
            "1. Crear Tabla\n" +
            "2. Consultar Tabla\n" +
            "3. Actualizar registro\n" +
            "4. Eliminar registro\n" +
            "5. Insertar registro en Tabla\n" +
            "6. Salir del programa\n";


    //COMUNES
    public static final String nombreTabla = "\nIntroduzca el nombre de la tabla";
    public static final String atributos = "\nIntroduzca el atributo de la tabla. Si son varios atributos introducelos en una línea separandolos con una coma y sin espacios (nombre,edad,estatura...)";
    public static final String tipoAtributos = "\nIntroduzca los tipos de atributos en el órden introducidos en el paso anterior de la misma manera en lenguaje SQL (VARCHAR(15),INT,BIT...)";
    public static final String clausulaWhere = "\nIntroduzca una clausula where. Si no quieres un where, dale al enter y dejalo vacío";
    public static final String insertarRegistro = "\nIntroduce registros para cada atributo de esta manera (nombre,edad,estatura...)";

    public static final String exit = "\nAdiós.";
}