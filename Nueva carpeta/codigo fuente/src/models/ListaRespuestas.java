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
     * @param idPregunta Entero con el identificador de la pregunta que tendra estas respuestas genericas.
     */
    public ListaRespuestas(String autor1, String autor2, int idPregunta) {
        ArrayList<Respuesta> respuestasGenericas;
        respuestasGenericas = new ArrayList<>();

        String contenidoR1 = "Respuesta 1 de la pregunta " + idPregunta;
        String contenidoR2 = "Respuesta 2 de la pregunta " + idPregunta;
        Respuesta respuesta1 = new Respuesta(autor1, contenidoR1);
        Respuesta respuesta2 = new Respuesta(autor2, contenidoR2);

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
     * Obtiene una lista con los id de las respuestas que no pertenecen al usuario loggueado que desea votar.
     * @param usuarioLoggueado String con el nombre del usuario que tiene sesion iniciada y desea votar.
     * @return listaIdsRespuestas Lista de enteros con los identificadores de las respuestas  que no pertenesen al usuario con sesion iniciada.
     */
    public ArrayList<Integer> respuestasVote(String usuarioLoggueado){
    	ArrayList<Integer> listaIdsRespuestas = new ArrayList<Integer>();
    	
    	for(int i = 0; i < respuestas.size(); i++) {
            if(!respuestas.get(i).getAutor().equals(usuarioLoggueado) ) {
            	listaIdsRespuestas.add(respuestas.get(i).getId());
            }
        }
   
    	return listaIdsRespuestas;
    }
    
    /**
     * Busca el indice que tiene una respuesta dentro de una lista de respuestas.
     * @param id Entero que representa el identificador de la respuesta buscada.
     * @return Entero que representa el indice de la respuesta dentro de la lista de respuestas.
     */
    public int buscarIndiceRespuesta( Integer id ) {
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
            stringRespuestas = stringRespuestas + "\n" + respuesta.toString();
        }
        return stringRespuestas;
    }

}
