package models;

/**
 * Clase que representa una etiqueta para una pregunta.
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Etiqueta {
    private static int idAutoincremental;   //Identificador autoincremental compartido de la clase
    private int id; //Identificador de la etiqueta
    private String nombre;  //Nombre de la etiqueta
    private String descripcion; //Breve descripcion de la etiqueta


    /**
     * Crea una etiqueta a partir de su nombre y descripcion.
     * @param nombre Nombre de la etiqueta.
     * @param descripcion Breve descripcion sobre la etiqueta.
     */
    public Etiqueta(String nombre, String descripcion) {
        idAutoincremental = idAutoincremental + 1;
        this.id = idAutoincremental;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre de una etiqueta.
     * @return  String con el nombre de la etiqueta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Crea un largo string con la informacion de la etiqueta.
     * @return Largo string que representa una etiqueta.
     */
    @Override
    public String toString() {
        return "  *Etiqueta " + id +
                "    Nombre: " + nombre +
                "\n" +
                "    Descripcion: " + descripcion +
                "\n";
    }
}
