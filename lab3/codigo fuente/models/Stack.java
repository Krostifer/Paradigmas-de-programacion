package models;

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
        if(preguntas.getPreguntas().get(idPregunta - 1).getEstado().equals("Cerrada")) {
            System.out.println("Esta pregunta ya esta cerrada, no se pueden ofrecer recompensas");
        }

        else {
            preguntas.getPreguntas().get( idPregunta - 1 ).setRecompensa( recompensa );
            usuarios.getUsuarios().get(indiceUsuario).setReputacion( recompensa * -1 );
        }
    }

    /**
     * Permite aceptar una respuesta como la mejor a una pregunta del usuario con sesion iniciada.
     * @param idPregunta Identificador numerico de la pregunta que posee la respuesta a aceptar.
     * @param idRespuesta Identificador numerico de la respuesta a aceptar.
     * @param usuarioLoggueado String con el nombre del usuario que desea aceptar una respuesta.
     */
    public void accept( int idPregunta, int idRespuesta, String usuarioLoggueado ) {
        if(preguntas.getPreguntas().get(idPregunta).getAutor().equals(usuarioLoggueado)) {
            preguntas.getPreguntas().get( idPregunta).setEstado("Cerrada");
            int indiceRespuesta = preguntas.getPreguntas().get(idPregunta).getListaRespuestas().buscarIndiceRespuesta(idRespuesta);

            if(indiceRespuesta == -1) {
                System.out.println("El identificador de la respuesta ingresado no corresponde a una respuesta de la pregunta indicada.\n");
            }

            else {
                preguntas.getPreguntas().get(idPregunta).getListaRespuestas().getRespuestas().get(indiceRespuesta).setEstado("Aceptada");
                int indiceAutorRespuesta = usuarios.buscarIndiceUsuario( preguntas.getPreguntas().get(idPregunta).getListaRespuestas().getRespuestas().get(indiceRespuesta).getAutor() );

                usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion( 15 + preguntas.getPreguntas().get(idPregunta).getRecompensa() );
                usuarios.getUsuarios().get( usuarios.buscarIndiceUsuario(usuarioLoggueado) ).setReputacion( 2 );
            }
        }

        else {
            System.out.println("No puede aceptar un respuesta de esta pregunta, ya que no es el autor.\n");
        }
    }


    /**
     * Permite votar positiva o negativamente por una pregunta o respuesta.
     * @param idVoto Identificador numerico de la pregunta o respuesta que se desea votar.
     * @param tipoVoto Entero que representa el tipo de voto a realizar, 0 (negativo) o 1 (positivo).
     * @param autorVoto String con el nombre del usuario que realiza el voto.
     */
    public void vote ( String idVoto, int tipoVoto, String autorVoto ) {
        int id = Integer.parseInt(idVoto.substring(0,(idVoto.length() - 1)));

        if(idVoto.charAt(idVoto.length()-1) == 'P') {
            if(preguntas.getPreguntas().get(id-1).getAutor().equals(autorVoto)) {
                System.out.println("Identificador de pregunta invalido.\n");
            }

            int indiceAutorPregunta = usuarios.buscarIndiceUsuario(preguntas.getPreguntas().get(id-1).getAutor());

            if(tipoVoto == 1) {
                preguntas.getPreguntas().get(id-1).setVotos(1);
                usuarios.getUsuarios().get(indiceAutorPregunta).setReputacion(10);
            }

            else {
                preguntas.getPreguntas().get(id-1).setVotos(-1);
                usuarios.getUsuarios().get(indiceAutorPregunta).setReputacion(-2);

                int indiceAutorVoto = usuarios.buscarIndiceUsuario(autorVoto);
                usuarios.getUsuarios().get(indiceAutorVoto).setReputacion(-1);
            }
        }

         else if(idVoto.charAt(idVoto.length()-1) == 'R') {

            int indicePreguntaConRespuesta = preguntas.buscarIndicePreguntaPorRespuesta(id) - 1;
            int indiceRespuestaDentroDeLista = preguntas.getPreguntas().get(indicePreguntaConRespuesta).getListaRespuestas().buscarIndiceRespuesta(id);
            String autorRespuesta = preguntas.getPreguntas().get(indicePreguntaConRespuesta).getListaRespuestas().getRespuestas().get(indiceRespuestaDentroDeLista).getAutor();
            int indiceAutorRespuesta = usuarios.buscarIndiceUsuario(autorRespuesta);

            if(autorRespuesta.equals(autorVoto)) {
                System.out.println("Identificador de respuesta invalido.\n");
            }

            if(tipoVoto == 1) {
                preguntas.getPreguntas().get(indicePreguntaConRespuesta).getListaRespuestas().getRespuestas().get(indiceRespuestaDentroDeLista).setVotos(1);
                usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion(10);
            }

            else {
                preguntas.getPreguntas().get(indicePreguntaConRespuesta).getListaRespuestas().getRespuestas().get(indiceRespuestaDentroDeLista).setVotos(-1);
                usuarios.getUsuarios().get(indiceAutorRespuesta).setReputacion(-2);

                int indiceAutorVoto = usuarios.buscarIndiceUsuario(autorVoto);
                usuarios.getUsuarios().get(indiceAutorVoto).setReputacion(-1);
            }
        }
    }
}
