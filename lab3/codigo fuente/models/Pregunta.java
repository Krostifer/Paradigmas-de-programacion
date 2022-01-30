package models;

import java.util.Calendar;

/**
 * Clase que representa un usuario registrado dentro del foro
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Pregunta {
    private static int idAutoincremental;   //Identificador autoincremental de la clase Pregunta
    private int id; //Identificador de la pregunta
    private ListaRespuestas respuestas; //Lista de respuestas dirigidas a la pregunta(agregacion)
    private ListaEtiquetas etiquetas;   //Lista de etiquetas que tiene la pregunta(agregacion)
    private String titulo;  //Titulo de la pregunta
    private String contenido;   //Contenido de la pregunta
    private Calendar fecha; //Fecha de creacion de la pregunta
    private String autor;   //Nombre del usuario que creo la pregunta
    private String estado;  //Estado de la pregunta, abierta o cerrada
    private int recompensa; //Total de recompensas ofrecidas por la pregunta
    private int votos;  //Total de votos realizados a la pregunta

    /**
     * Crea una pregunta con la información ingresada por un usuario.
     * @param titulo Titulo de la nueva pregunta.
     * @param contenido Contenido de la nueva pregunta.
     * @param autor Nombre del autor de la nueva pregunta.
     * @param etiquetas Lista de etiquetas de la nueva pregunta.
     */
    public Pregunta( String titulo, String contenido, String autor, ListaEtiquetas etiquetas ) {
        idAutoincremental = idAutoincremental + 1;
        this.id = idAutoincremental;
        this.respuestas = new ListaRespuestas();
        this.etiquetas = etiquetas;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = Calendar.getInstance();
        this.autor = autor;
        this.estado = "Abierta";
        this.recompensa = 0;
        this.votos = 0;
    }

    /**
     * Crea una pregunta pre definida.
     * @param titulo Titulo de la nueva pregunta.
     * @param contenido Contenido de la nueva pregunta.
     * @param autor Nombre del autor de la nueva pregunta.
     * @param recompensa Recompensa ofrecida a la mejor respuesta a la pregunta.
     * @param respuestasGenericas Respuestas pre creadas dirigidas a la pregunta.
     * @param votos Cantidad de votos de la pregunta.
     */
    public Pregunta( String titulo, String contenido, String autor, int recompensa, ListaRespuestas respuestasGenericas, int votos ) {
        ListaEtiquetas etiquetasVacia;
        etiquetasVacia = new ListaEtiquetas();
        this.etiquetas = etiquetasVacia;
        idAutoincremental = idAutoincremental + 1;
        this.id = idAutoincremental;
        this.respuestas = respuestasGenericas;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = Calendar.getInstance();
        this.autor = autor;
        this.estado = "Abierta";
        this.recompensa = recompensa;
        this.votos = votos;


    }

    /**
     * Obtine la lista de respuestas de la pregunta.
     * @return Lista de respuestas dirigidas a la pregunta.
     */
    public ListaRespuestas getListaRespuestas() {
        return respuestas;
    }

    /**
     * Obtine el nombre del autor que creo la pregunta.
     * @return String con el nombre del creador de la pregunta.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtine la cantidad de puntos de recompensa ofrecidos a quien responda correctamente la pregunta.
     * @return Entero con la cantidad total de recompensas ofrecidas.
     */
    public int getRecompensa() {
        return recompensa;
    }

    /**
     * Obtine el estado de la pregunta.
     * @return String con el estado de la pregunta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtine el identificador de la pregunta.
     * @return Entero que representa el identificador de la pregunta.
     */
    public int getId() {
        return id;
    }

    /**
     * Suma la cantidad ingresada a la recompensa ofrecidas a la pregunta.
     * @param cambioRecompensa Entero que representa lo que se desea sumar(positivo) o restar(negativo) a la recompensa ofrecida por la pregunta.
     */
    public void setRecompensa(int cambioRecompensa) {
        this.recompensa = recompensa + cambioRecompensa;
    }

    /**
     * Cambia el estado de una pregunta.
     * @param estado String con el nuevo estado de la pregunta.
     */
    public void setEstado(String estado) {
        this.estado = estado;

    }

    /**
     * Suma la cantidad ingresada a los votos de la pregunta.
     * @param cambioVotos Entero que representa lo que se desea sumar(positivo) o restar(negativo) a los votos de la pregunta.
     */
    public void setVotos(int cambioVotos) {
        this.votos = votos + cambioVotos;
    }

    /**
     * Crea un largo string con la informacion de una pregunta.
     * @return Largo string que representa una pregunta.
     */
    @Override
    public String toString() {
        return "*Pregunta: " + id +
                "  Fecha: " + fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR) +
                "  Votos: " + votos +
                "  Recompensa: " + recompensa +
                "  Estado: " + estado +
                "\n" +
                "  " + titulo +
                "\n" +
                "    " + contenido +
                "\n" +
                "    Autor: " + autor +
                "  Etiquetas: " + etiquetas +
                "\n\n" +
                "      Respuestas: \n" + respuestas.toString();
    }

    /**
     * Crea un largo string con la informacion de la pregunta para que sea votada.
     * @return Largo string que representa una pregunta con un identificador modificado para diferenciar de las respuestas.
     */
    public String toStringVote( String autorVoto ) {
        return "*Pregunta: " + id + "P" +
                "  Fecha: " + fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR) +
                "  Votos: " + votos +
                "  Recompensa: " + recompensa +
                "  Estado: " + estado +
                "\n" +
                "  " + titulo +
                "\n" +
                "    " + contenido +
                "\n" +
                "    Autor: " + autor +
                "  Etiquetas: " + etiquetas +
                "\n\n" +
                "      Respuestas: \n" + respuestas.mostrarRespuestasVote(autorVoto);
    }
}
