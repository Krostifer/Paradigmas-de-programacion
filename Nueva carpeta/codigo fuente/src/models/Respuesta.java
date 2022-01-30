package models;

import java.util.Calendar;

/**
 * Clase que representa una respuesta dentro del foro.
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Respuesta {
    private static int idAutoincremental;   //Identificador autoincremental de la clase Respuesta
    private int id; //Identificador de la respuesta
    private String autor;   //Nombre del usuario que creo la respuesta
    private Calendar fecha; //Fecha de creacion de la respuesta
    private String contenido;   //Contenido de la respuesta
    private String estado;  //Estado de la respuesta, "" o aceptada
    private int votos;  //Total de votos realizados a la respuesta

    /**
     * Crea una pregunta con la información ingresada por un usuario.
     * @param autor Nombre del autor de la nueva respuesta.
     * @param contenido Contenido de la nueva respuesta.
     */
    public Respuesta( String autor, String contenido ) {
        idAutoincremental = idAutoincremental + 1;
        this.id = idAutoincremental;
        this.autor = autor;
        this.fecha = Calendar.getInstance();
        this.contenido = contenido;
        this.estado = "";
        this.votos = 0;
    }

    /**
     * Obtine el identificador numerico de la respuesta.
     * @return Entero que representa el identificdor numerico de una respuesta.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtine el nombre del usuario que creo la respuesta.
     * @return String con el nombre del autor de la respuesta.
     */
    public String getAutor() {
        return autor;
    }
    
    /**
     * Obtine el contenido de la respuesta.
     * @return String con el contenido de la respuesta.
     */
    public String getContenido() {
		return contenido;
	}

    /**
     * Cambia el estado de la respuesta.
     * @param estado String con el nuevo estado de la respuesta.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Suma la cantidad ingresada a los votos de la respuesta.
     * @param cambioVotos Entero que representa lo que se desea sumar(positivo) o restar(negativo) a los votos de la respuesta.
     */
    public void setVotos(int cambioVotos) {
        this.votos = votos + cambioVotos;
    }

    /**
     * Crea un largo string con la informacion de una respuesta.
     * @return Largo string que representa una respuesta.
     */
    @Override
    public String toString() {
        return "      *Respuesta: " + id +
                "  Fecha: " + fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR) +
                "  Estado: " + estado +
                "  Votos: " + votos +
                "\n" +
                "        " + getContenido() +
                "\n" +
                "        Autor: " + autor +
                "\n";
    }
	
}
