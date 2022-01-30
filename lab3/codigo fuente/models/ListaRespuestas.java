package models;

import java.util.ArrayList;

/**
 * Clase que representa una lista de respuestas registradas en el foro
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class ListaRespuestas {
    private ArrayList<Respuesta> respuestas;    //Array de respuestas(agregacion)

    /**
     * Crea una lista de respuestas vacia.
     */
    public ListaRespuestas() {
        ArrayList<Respuesta> respuestasVacia;
        respuestasVacia = new ArrayList<>();
        this.respuestas = respuestasVacia;
    }

    /**
     * Crea una lista de respuetas con dos respuestas genericas escritas por dos usuarios.
     * @param autor1 String con el nombre del autor de la primera respuesta generica.
     * @param autor2 String con el nombre del autor de la segunda respuesta generica.
     */
    public ListaRespuestas(String autor1, String autor2) {
        ArrayList<Respuesta> respuestasGenericas;
        respuestasGenericas = new ArrayList<>();

        Respuesta respuesta1 = new Respuesta(autor1, "Respuesta 1");
        Respuesta respuesta2 = new Respuesta(autor2, "Respuesta 2");

        respuestasGenericas.add(respuesta1);
        respuestasGenericas.add(respuesta2);

        this.respuestas = respuestasGenericas;
    }

    /**
     * Obtiene el array con las respuestas.
     * @return Array de respuestas.
     */
    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * Agrega una respuesta al final de la lista de respuestas.
     * @param nuevaRespuesta Respuesta que se desea agregar al final de la lista de respuestas.
     */
    public void agregarRespuesta( Respuesta nuevaRespuesta ) {
        this.respuestas.add( nuevaRespuesta );
    }

    /**
     * Busca el indice que tiene una respuesta dentro de una lista de respuestas.
     * @param id Entero que representa el identificador de la respuesta buscada.
     * @return Entero que representa el indice de la respuesta dentro de la lista de respuestas.
     */
    public int buscarIndiceRespuesta( int id ) {
        for( int i = 0;  i < getRespuestas().size(); i++) {
            if( respuestas.get(i).getId() == id ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Crea un largo string con la informacion de todas las respuestas de una lista de respuestas.
     * @return Largo string que representa una lista de respuestas.
     */
    @Override
    public String toString() {
        String stringRespuestas = "";
        for(Respuesta respuesta: respuestas) {
            stringRespuestas = stringRespuestas + "\n" +respuesta.toString();
        }
        return stringRespuestas;
    }

    /**
     * Crea un largo string con la informacion de todas las respuestas que no fueron creadas por el usuario que desea votar.
     * @param autorVoto String con el nombre del usuario que desea votar.
     * @return Largo string con las respuestas.
     */
    public String mostrarRespuestasVote( String autorVoto ) {
        String stringRespuestas = "";
        for(Respuesta respuesta: respuestas) {
            if(!respuesta.getAutor().equals(autorVoto)) {
                stringRespuestas = stringRespuestas + "\n" +respuesta.toStringVote();
            }
        }
        return stringRespuestas;
    }
}
