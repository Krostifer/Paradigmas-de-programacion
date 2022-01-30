package models;

import java.util.ArrayList;

/**
 * Clase que representa una lista de preguntas registradas en el foro
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */
public class ListaPreguntas {
    private ArrayList<Pregunta> preguntas;  //Array de preguntas(agregacion)

    /**
     * Crea una lista de preguntas pre registradas dentro del foro.
     */
    public ListaPreguntas() {
        ArrayList<Pregunta> preguntasIniciales;
        preguntasIniciales = new ArrayList<>();

        ListaRespuestas respuestasGenericas1 = new ListaRespuestas( "Jose", "Esteban", 1);
        Pregunta pregunta1 = new Pregunta("Pregunta 1", "Esta el la primera pregunta", "Jose", 0, respuestasGenericas1, 5);
        preguntasIniciales.add(pregunta1);

        ListaRespuestas respuestasGenericas2 = new ListaRespuestas( "Esteban", "Thomas", 2);
        Pregunta pregunta2 = new Pregunta("Pregunta 2", "Esta el la segunda pregunta", "Esteban", 20, respuestasGenericas2, -1);
        preguntasIniciales.add(pregunta2);

        ListaRespuestas respuestasGenericas3 = new ListaRespuestas( "Thomas", "Jesus", 3);
        Pregunta pregunta3 = new Pregunta("Pregunta 3", "Esta el la tercera pregunta", "Thomas", 3, respuestasGenericas3, 6);
        preguntasIniciales.add(pregunta3);

        ListaRespuestas respuestasGenericas4 = new ListaRespuestas( "Jesus", "Jose", 4);
        Pregunta pregunta4 = new Pregunta("Pregunta 4", "Esta el la cuarta pregunta", "Jesus", 7, respuestasGenericas4, -3);
        preguntasIniciales.add(pregunta4);

        ListaRespuestas respuestasGenericas5 = new ListaRespuestas( "Esteban", "Jesus", 5);
        Pregunta pregunta5 = new Pregunta("Pregunta 5", "Esta el la quinta pregunta", "Jesus", 15, respuestasGenericas5, 5);
        preguntasIniciales.add(pregunta5);

        this.preguntas = preguntasIniciales;
    }
    


    /**
     * Obtiene el array con las preguntas.
     * @return Array de preguntas.
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Agrega una pregunta al final de la lista de preguntas.
     * @param nuevaPregunta Pregunta que se desea agregar al final de la lista de preguntas.
     */
    private void agregarPregunta( Pregunta nuevaPregunta ) {
        this.preguntas.add( nuevaPregunta );
    }

    /**
     * Crea una pregunta y la registra dentro de la lista de preguntas.
     * @param titulo String con el titulo de la nueva pregunta.
     * @param contenido String con el contenido de la nueva pregunta.
     * @param etiquetas ListaEtiquetas que contiene las etiquetas de la nueva pregunta.
     * @param autor String con el nombre del autor de la nueva pregunta.
     */
    public void ask ( String titulo, String contenido, ListaEtiquetas etiquetas, String autor ) {
        Pregunta nuevaPregunta = new Pregunta( titulo, contenido, autor, etiquetas );
        agregarPregunta( nuevaPregunta );
    }

    /**
     * Crea una respuesta a una pregunta previamente registrada en el foro.
     * @param idPregunta Identificador numerico de la pregunta que se desea responder.
     * @param contenido String con el contenido de la nueva respuesta.
     * @param autor String con el nombre del autor de la nueva respuesta.
     */
    public void answer ( int idPregunta, String contenido, String autor ) {
        Respuesta nuevaRespuesta = new Respuesta(autor, contenido);
        preguntas.get(idPregunta).getListaRespuestas().agregarRespuesta(nuevaRespuesta);
    }
    
    /**
     * Obtine una lista con los id de las preguntas abiertas .
     * @return listaIdsPreguntas Lista de enteros con los identificadores de las preguntas  que se encuentran abiertas.
     */
    public ArrayList<Integer> preguntasAbiertas(){
    	ArrayList<Integer> listaIdsPreguntas = new ArrayList<Integer>();
    	
    	for(int i = 0; i < preguntas.size(); i++) {
            if(preguntas.get(i).getEstado().equals("Abierta") ) {
            	listaIdsPreguntas.add(preguntas.get(i).getId());
            }
        }
   
    	return listaIdsPreguntas;
    }
    
    /**
     * Obtiene una lista con los id de las preguntas que no pertenecen al usuario loggueado que desea votar.
     * @param usuarioLoggueado String con el nombre del usuario que tiene sesion iniciada y desea votar.
     * @return listaIdsPreguntas Lista de enteros con los identificadores de las preguntas  que no pertenesen al usuario con sesion iniciada.
     */
    public ArrayList<Integer> preguntasVote(String usuarioLoggueado){
    	ArrayList<Integer> listaIdsPreguntas = new ArrayList<Integer>();
    	
    	for(int i = 0; i < preguntas.size(); i++) {
            if((!preguntas.get(i).getAutor().equals(usuarioLoggueado)) || preguntas.get(i).getListaRespuestas().respuestasVote(usuarioLoggueado).size() > 0) {
            	listaIdsPreguntas.add(preguntas.get(i).getId());
            }
        }
   
    	return listaIdsPreguntas;
    }

    

    /**
     * Crea un largo string con la informacion de todas las preguntas de una lista de preguntas.
     * @return Largo string que representa una lista de preguntas.
     */
    @Override
    public String toString() {
        String preguntasString = "";
        for(Pregunta pregunta: preguntas) {
            preguntasString = preguntasString + "\n" + pregunta.toString();
        }
        return preguntasString;
    }

}
