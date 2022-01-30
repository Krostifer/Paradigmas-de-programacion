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

        ListaRespuestas respuestasGenericas1 = new ListaRespuestas( "Jose", "Esteban");
        Pregunta pregunta1 = new Pregunta("Pregunta 1", "Esta el la primera pregunta", "Jose", 0, respuestasGenericas1, 5);
        preguntasIniciales.add(pregunta1);

        ListaRespuestas respuestasGenericas2 = new ListaRespuestas( "Esteban", "Thomas");
        Pregunta pregunta2 = new Pregunta("Pregunta 2", "Esta el la segunda pregunta", "Esteban", 20, respuestasGenericas2, -1);
        preguntasIniciales.add(pregunta2);

        ListaRespuestas respuestasGenericas3 = new ListaRespuestas( "Thomas", "Jesus");
        Pregunta pregunta3 = new Pregunta("Pregunta 3", "Esta el la tercera pregunta", "Thomas", 3, respuestasGenericas3, 6);
        preguntasIniciales.add(pregunta3);

        ListaRespuestas respuestasGenericas4 = new ListaRespuestas( "Jesus", "Jose");
        Pregunta pregunta4 = new Pregunta("Pregunta 4", "Esta el la cuarta pregunta", "Jesus", 7, respuestasGenericas4, -3);
        preguntasIniciales.add(pregunta4);

        ListaRespuestas respuestasGenericas5 = new ListaRespuestas( "Esteban", "Jesus");
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
     * Busca el indice que tiene una pregunta que contiene cierta respuesta por su identificador
     * @param idRespuesta Entero que representa el identificador de la respuesta que se desea buscar dentro de una pregunta.
     * @return Indice del objeto pregunta que posea la respuesta buscada dentro de la lista de preguntas.
     */
    public int buscarIndicePreguntaPorRespuesta( int idRespuesta ) {
        for(Pregunta pregunta: preguntas) {
            for(Respuesta respuesta: pregunta.getListaRespuestas().getRespuestas()) {
                if(idRespuesta == respuesta.getId()) {
                    return pregunta.getId();
                }
            }
        }
        return -1;
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
     * Crea un largo String con las preguntas abiertas que pertenecen al usuario.
     * @param autor String con el nombre del usuario autor.
     */
    public String preguntasDelUsuario( String autor ) {
        String preguntasString = "";
        for(Pregunta pregunta: preguntas) {
            if(pregunta.getAutor().equals(autor) && pregunta.getEstado().equals("Abierta")) {
                preguntasString = preguntasString + pregunta.toString() + "\n";
            }
        }
        return preguntasString;
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

    /**
     * Crea un largo string con la informacion de todas las preguntas y/o respuestas que no fueron creadas por el usuario que desea votar.
     * @param autorVoto String con el nombre del usuario que desea votar.
     * @return Largo string con las preguntas y/o respuestas.
     */
    public String mostrarPreguntasVote( String autorVoto ) {
        String preguntasString = "";
        for(Pregunta pregunta: preguntas) {
            if(!pregunta.getAutor().equals(autorVoto)) {
               preguntasString = preguntasString + "\n" + pregunta.toStringVote(autorVoto);
            }

            else {
                if(!pregunta.getListaRespuestas().mostrarRespuestasVote(autorVoto).equals("")){
                    preguntasString = preguntasString + "\n" + "La siguiente pregunta no se puede votar, pero si sus respuestas.\n" +pregunta.toStringVote(autorVoto);
                }
            }
        }
        return preguntasString;
    }
}
