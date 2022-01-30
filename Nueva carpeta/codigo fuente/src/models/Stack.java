package models;

import java.util.ArrayList;

/**
 * Clase que representa el foro con toda su informacion
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Stack {
    private ListaUsuarios usuarios; //Lista de usuarios registrados en el foro(agregacion)
    private ListaPreguntas preguntas;   //Lista de preguntas registradas en el foro(agregacion)
    private String sesionIniciada;  //Nombre del usuario con sesion iniciada
    private ListaEtiquetas tags; //Lista de etiquetas registradas en el foro(agregacion)

    /**
     * Crea un stack con la informacion pre registrada.
     */
    public Stack() {
        this.usuarios = new ListaUsuarios();
        this.preguntas = new ListaPreguntas();
        this.tags = new ListaEtiquetas(0);
    }

    /**
     * Obtine la lista de usuarios registrados en el foro.
     * @return ListaUsuarios que contiene los usuarios registrados en el foro.
     */
    public ListaUsuarios getListaUsuarios() {
        return usuarios;
    }

    /**
     * Obtine el nombre del usuario con sesion iniciada en el foro.
     * @return String con el nombre del usuario que tiene sesion iniciada.
     */
    public String getSesionIniciada() {
        return sesionIniciada;
    }

    /**
     * Obtine la lista de preguntas registradas en el foro.
     * @return ListaPreguntas con todas las preguntas registradas en el foro.
     */
    public ListaPreguntas getListaPreguntas() {
        return preguntas;
    }

    /**
     * Obtine la lista de etiquetas registradas en el foro.
     * @return ListaEtiquetas que contiene todas las etiquetas registradas en el foro.
     */
    public ListaEtiquetas getTags() {
        return tags;
    }

    /**
     * Modifica el nombre del usuario con sesion iniciada.
     * @param sesionIniciada String con el nombre del usuario que inicia sesion.
     */
    public void setSesionIniciada(String sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    /**
     * Permite registrar un nuevo usuario dentro del foro.
     * @param name Nombre del nuevo usuario a registrar.
     * @param pass Clave de acceso del nuevo usuario a registrar.
     */
    public void register( String name, String pass ) {
        Usuario nuevoUsuario = new Usuario(name, pass, 0);
        usuarios.agregarUsuario(nuevoUsuario);
    }

    /**
     * Crea y registra una nueva etiqueta dentro del foro.
     * @param nombre Nombre de la nueva etiqueta a registrar.
     * @param descripcion Breve descripcion de la etiqueta a registrar.
     */
    public void agregarEtiquetaAlRegistro( String nombre, String descripcion ) {
        Etiqueta nuevaEtiqueta = new Etiqueta(nombre, descripcion);
        tags.agregarEtiqueta(nuevaEtiqueta);
    }
    
    

    /**
     * Permite ofrecer puntos de reputacion del usuario con sesion iniciada por una pregunta.
     * @param idPregunta Identificador numerico de la pregunta por la que se desea ofrecer recompensa.
     * @param recompensa Cantidad de puntos de reputacion que se desean ofrecer por la pregunta.
     * @param indiceUsuario Indice del usuario que desea ofrecer recompensa dentro de la lista de usuarios registrados.
     */
    public void reward( int idPregunta, int recompensa, int indiceUsuario ) {
    
            preguntas.getPreguntas().get( idPregunta - 1).setRecompensa( recompensa );
            usuarios.getUsuarios().get(indiceUsuario).setReputacion( recompensa * -1 );
    }
    
    /**
     * Obtine una lista con los id de las preguntas abiertas y con respuestas del usuario con sesion iniciada.
     * return listaIdsPreguntas Lista de enteros con los identificadores de las preguntas del usuario con sesion iniciada que se encuentran abiertas y con respuestas.
     */
    public ArrayList<Integer> preguntasDelUsuario() {
    	ArrayList<Integer> listaIdsPreguntas = new ArrayList<Integer>();
    	
    	for(int i = 0; i < preguntas.getPreguntas().size(); i++) {
    		
            if(preguntas.getPreguntas().get(i).getAutor().equals(sesionIniciada) && preguntas.getPreguntas().get(i).getEstado().equals("Abierta") && preguntas.getPreguntas().get(i).getListaRespuestas().getRespuestas().size() > 0) {
            	listaIdsPreguntas.add(preguntas.getPreguntas().get(i).getId());
            }
        }
    	
    	return listaIdsPreguntas;
    }

    /**
     * Permite aceptar una respuesta como la mejor a una pregunta del usuario con sesion iniciada.
     * @param idPregunta Identificador numerico de la pregunta que posee la respuesta a aceptar.
     * @param indiceRespuesta Indice de la respuesta dentro de la lista de respuestas de la pregunta.
     * @param usuarioLoggueado String con el nombre del usuario que desea aceptar una respuesta.
     */
    public void accept( int idPregunta, int indiceRespuesta, String usuarioLoggueado ) {
       
    	preguntas.getPreguntas().get( idPregunta - 1).setEstado("Cerrada");
    	preguntas.getPreguntas().get(idPregunta - 1).getListaRespuestas().getRespuestas().get(indiceRespuesta).setEstado("Aceptada");
    	int indiceAutorRespuesta = usuarios.buscarIndiceUsuario( preguntas.getPreguntas().get(idPregunta - 1).getListaRespuestas().getRespuestas().get(indiceRespuesta).getAutor() );
    	
    	int recompensa = preguntas.getPreguntas().get(idPregunta - 1).getRecompensa();
    	preguntas.getPreguntas().get(idPregunta - 1).setRecompensa(recompensa *-1);  
    	
    	//Solamente se actualizaran los puntos de reputancion si un usuario acepta una respuesta que no sea de el 
    	if(!usuarioLoggueado.equals(usuarios.getUsuarios().get(indiceAutorRespuesta).getNombre())) {
    		usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion( 15 + recompensa );
        	usuarios.getUsuarios().get( usuarios.buscarIndiceUsuario(usuarioLoggueado) ).setReputacion( 2 );
    	}
    }


    /**
     * Permite votar positiva o negativamente por una pregunta o respuesta.
     * @param indicadorClase Identificador numerico que indica que es una pregunta(1) o una respuesta(2) la que se desea votar.
     * @param tipoVoto Entero que representa el tipo de voto a realizar, 0 (negativo) o 1 (positivo).
     * @param autorVoto String con el nombre del usuario que realiza el voto.
     * @param indicePregunta Entero que representa el indice de la pregunta que se desea votar o de la pregunta que contiene la respuesta a votar
     * @param indiceRespuesta Entero que representa el indice de la pregunta que contien la respuesta en caso de que se desee votar una respuesta.
     */
    public void vote (int indicadorClase, int tipoVoto, String autorVoto, int indicePregunta, int indiceRespuesta) {

    	//Votar pregunta
        if(indicadorClase == 1) {
            int indiceAutorPregunta = usuarios.buscarIndiceUsuario(preguntas.getPreguntas().get(indicePregunta).getAutor());

            if(tipoVoto == 1) {
                preguntas.getPreguntas().get(indicePregunta).setVotos(1);
                usuarios.getUsuarios().get(indiceAutorPregunta).setReputacion(10);
            }

            else {
                preguntas.getPreguntas().get(indicePregunta).setVotos(-1);
                usuarios.getUsuarios().get(indiceAutorPregunta).setReputacion(-2);

                int indiceAutorVoto = usuarios.buscarIndiceUsuario(autorVoto);
                usuarios.getUsuarios().get(indiceAutorVoto).setReputacion(-1);
            }
        }

         else if(indicadorClase == 2) {

            String autorRespuesta = preguntas.getPreguntas().get(indicePregunta).getListaRespuestas().getRespuestas().get(indiceRespuesta).getAutor();
            int indiceAutorRespuesta = usuarios.buscarIndiceUsuario(autorRespuesta);

            if(tipoVoto == 1) {
                preguntas.getPreguntas().get(indicePregunta).getListaRespuestas().getRespuestas().get(indiceRespuesta).setVotos(1);
                usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion(10);
            }

            else {
                preguntas.getPreguntas().get(indicePregunta).getListaRespuestas().getRespuestas().get(indiceRespuesta).setVotos(-1);
                usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion(-2);

                int indiceAutorVoto = usuarios.buscarIndiceUsuario(autorVoto);
                usuarios.getUsuarios().get(indiceAutorVoto).setReputacion(-1);
            }
        }
    }
    
    /**
     * Crea un largo string con la representacion del foro.
     * return stringForo String que contiene la representacion de las preguntas y respuestas del foro.
     */
    public String toString() {
    	String stringForo = "\n\n";
    	
    	for(int i = 0; i < preguntas.getPreguntas().size(); i++) {
    		stringForo = stringForo + preguntas.getPreguntas().get(i).toString() + "\n\n";
    	}
    	
    	return stringForo;
    }
}
