package views;

import models.ListaEtiquetas;
import models.Stack;

import java.util.Scanner;

/**
 * Clase que representa el menu principal del programa.
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class MainMenu {

    private Scanner lector; //Variable para almacenar los datos numericos ingresador por el usuario
    private Stack foro; //Stack que contiene toda la informacion del foro(agregacion)

    /**
     * Crea un menu principal con un Stack que contiene informacion pre registrada.
     */
    public MainMenu() {
        lector = new Scanner(System.in);
        foro = new Stack();
    }

    /**
     * Despliega el menu principal al usuario y le pide que ingrese una opcion del menu.
     */
    public void iniciar() {

        int opcion;

        do {
            if( foro.getSesionIniciada() != null ) {
                System.out.println("Sesion iniciada como: " + foro.getSesionIniciada());
            }
            System.out.println("Escoja una opccion: ");
            System.out.println("1. Registrar un nuevo usuario");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Agregar una pregunta");
            System.out.println("4. Responder una pregunta");
            System.out.println("5. Ofrecer recompensa a una pregunta");
            System.out.println("6. Aceptar una respuesta");
            System.out.println("7. Votar una pregunta o respuesta");
            System.out.println("8. Cerrar sesion");
            System.out.println("9. Salir del programa");
            System.out.println("INTRODUZCA SU OPCION: ");

            String lineaLeida = lector.nextLine();
            try {
                opcion = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                opcion = - 1;
            }

            switch(opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    preguntar();
                    break;
                case 4:
                    responder();
                    break;
                case 5:
                    ofrecerRecompensa();
                    break;
                case 6:
                    aceptarRespuesta();
                    break;
                case 7:
                    votarPreguntaRespuesta();
                    break;
                case 8:
                    cerrarSesion();
                    break;
                case 9:
                    terminarPrograma();
                    break;
                default:
                    System.out.println("Opcion invalida, favor ingresar una de las opciones.");
            }
        } while (opcion != 9);
    }

    /**
     * Pide al usuario que ingrese los datos para registrar un nuevo usuario en el foro.
     */
    private void registrarUsuario(){
        System.out.println("Ingrese el nombre del nuevo usuario: ");
        String name = lector.nextLine();
        System.out.println("Ingrese la clave del nuevo usuario: ");
        String pass = lector.nextLine();

        if( foro.getListaUsuarios().usuarioUnico(name) ) {
            foro.register( name, pass );

            System.out.println("Usuario agregado correctamente.\n");
        }

        else {
            System.out.println("El nombre de usuario ingresado ya esta registrado.\n");
        }
    }

    /**
     * Pide al usuario que ingrese los datos para iniciar sesion con un usuario previamente registrado.
     */
    private void iniciarSesion(){
        if( !(foro.getSesionIniciada() == null) ) {
            System.out.println("Ya existe una sesion actualmente iniciada, si desea ingresar con otro usuario, favor cerrar la sesion actual.\n");
        }

        else {
            System.out.println("Ingrese su nombre de usuario: ");
            String name = lector.nextLine();
            System.out.println("Ingrese su clave: ");
            String pass = lector.nextLine();

            if( foro.getListaUsuarios().verificarLogin( name, pass) ) {
                foro.setSesionIniciada(name);
                System.out.println("Sesion iniciada correctamente.\n");
            }

            else {
                System.out.println("Los datos ingresados son incorrectos.\n");
            }
        }
    }

    /**
     * Pide al usuario con sesion previamente iniciada que ingrese los datos para registrar una nueva pregunta en el foro.
     * Además permite crear nuevas etiquetas en caso de que el usuario lo desee.
     */
    private void preguntar(){
        if( foro.getSesionIniciada() == null ) {
            System.out.println("Es necesario que inicie sesion antes de agregar una pregunta.\n");
        }

        else {
            Scanner datos = new Scanner(System.in);
            System.out.println("Ingrese el titulo de su pregunta: ");
            String titulo = lector.nextLine();
            System.out.println("Ingrese el contenido de su pregunta: ");
            String contenido = lector.nextLine();
            System.out.println(foro.getTags().toStringAsk());
            System.out.println("Ingrese un 1 si desea utilizar las etiquetas registradas o un 2 si desea agregar nuevas etiquetas para su pregunta: ");
            int opcionEtiquetas = datos.nextInt();

            if(opcionEtiquetas == 2) {
                System.out.println("Ingrese la cantidad de etiquetas que desea crear: ");
                int cantEtiquetasCrear = datos.nextInt();

                for(int i = 1; i <= cantEtiquetasCrear; i++) {
                    Scanner datosEtiqueta = new Scanner(System.in);

                    System.out.println("Ingrese el nombre de la etiqueta numero " + i + " : ");
                    String nobreEtiqueta = datosEtiqueta.nextLine();

                    System.out.println("Ingrese una breve descripcion de esta etiqueta: ");
                    String descripcionEtiqueta = datosEtiqueta.next();

                    foro.agregarEtiquetaAlRegistro(nobreEtiqueta, descripcionEtiqueta);
                    System.out.println("Etiqueta creada correctamente.\n");
                }
                System.out.println(foro.getTags().toStringAsk());
            }

            ListaEtiquetas etiquetasPregunta = new ListaEtiquetas();
            System.out.println("Ingrese la cantidad de etiquetas que desea agregar a su pregunta: ");
            int cantEtiquetasUsar = datos.nextInt();

            for(int i = 1; i <= cantEtiquetasUsar; i++) {
                System.out.println("Ingrese en identificador de la etiqueta numero " + i + ": ");
                int indiceEtiqueta = datos.nextInt();
                etiquetasPregunta.agregarEtiqueta(foro.getTags().getEtiquetas().get(indiceEtiqueta - 1));
            }

            foro.getListaPreguntas().ask( titulo, contenido, etiquetasPregunta, foro.getSesionIniciada() );
            System.out.println("Se ha agregado correctamente la nueva pregunta.\n");
        }

    }

    /**
     * Pide al usuario con sesion previamente iniciada que ingrese los datos para registrar una nueva respuesta a una pregunta del foro.
     */
    private void responder(){
        if( foro.getSesionIniciada() == null ) {
            System.out.println("Es necesario que inicie sesion antes de responder una pregunta.\n");
        }

        else {
            Scanner datos = new Scanner(System.in);
            System.out.println(foro.getListaPreguntas().toString());
            System.out.println("Ingrese el identificador de la pregunta que desea responder: ");
            String lineaLeida = datos.nextLine();
            int idPregunta;

            try {
                 idPregunta = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                System.out.println("Favor ingresar un numero, no texto.\n");
                return;
            }

            System.out.println("Ingrese el contenido de su respuesta: ");
            String contenido = datos.nextLine();

            foro.getListaPreguntas().answer( idPregunta-1 , contenido, foro.getSesionIniciada() );
            System.out.println("Se ha agregado correctamente la nueva respuesta.\n");
        }
    }

    /**
     * Pide al usuario con sesion previamente iniciada que ingrese los datos para ofrecer recompensa por una pregunta del foro.
     */
    private void ofrecerRecompensa(){
        if( foro.getSesionIniciada() == null ) {
            System.out.println("Es necesario que inicie sesion antes de ofrecer recompensa por una pregunta.\n");
        }

        else {
            Scanner datos = new Scanner(System.in);
            int indiceUsuario = foro.getListaUsuarios().buscarIndiceUsuario( foro.getSesionIniciada() );
            System.out.println("La reputacion actual del usuario es de: " + foro.getListaUsuarios().getUsuarios().get( indiceUsuario ).getReputacion());
            System.out.println(foro.getListaPreguntas().toString());
            int idPregunta;
            System.out.println("Ingrese el identificador de la pregunta por la cual desea ofrecer recompensa: ");
            String lineaLeida = lector.nextLine();

            try {
                idPregunta = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                System.out.println("Favor ingresar un numero, no texto.\n");
                return;
            }

            int recompensa;
            System.out.println("Ingrese la cantidad de reputacion que desea ofrecer: ");
            lineaLeida = datos.nextLine();

            try {
                recompensa = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                System.out.println("Favor ingresar un numero, no texto.\n");
                return;
            }

            if( foro.getListaUsuarios().getUsuarios().get( indiceUsuario ).getReputacion() >= recompensa ) {
                foro.reward( idPregunta , recompensa, indiceUsuario );
                System.out.println("Se han ofrecido correctamente " + recompensa + " puntos de reputacion por la pregunta " + idPregunta + "\n");
            }

            else {
                System.out.println("Usted no posee suficiente reputacion para ofrecer, favor ingresar una cantidad valida.\n");
            }
        }
    }

    /**
     * Pide al usuario con sesion previamente iniciada que ingrese los datos para aceptar una respuesta a una de sus preguntas registrada en el foro.
     */
    private void aceptarRespuesta(){
        if( foro.getSesionIniciada() == null ) {
            System.out.println("Es necesario que inicie sesion antes de aceptar una respuesta.\n");
        }

        else {
            String preguntasAceptar = foro.getListaPreguntas().preguntasDelUsuario(foro.getSesionIniciada());

            if(preguntasAceptar.equals("")) {
                System.out.println("No posee preguntas para aceptar.\n");
                return;
            }

            System.out.println(preguntasAceptar);
            System.out.println("Ingrese el identificador de la pregunta a la cual desea aceptar una respuesta: ");
            int idPregunta;
            String lineaLeida = lector.nextLine();

            try {
                idPregunta = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                System.out.println("Favor ingresar un numero, no texto.\n");
                return;
            }

            System.out.println("Ingrese el identificador de la respuesta que desea aceptar: ");
            int idRespuesta;
            lineaLeida = lector.nextLine();

            try {
                idRespuesta = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                System.out.println("Favor ingresar un numero, no texto.\n");
                return;
            }

            foro.accept( idPregunta-1, idRespuesta, foro.getSesionIniciada() );
            System.out.println("Respuesta aceptada correctamente.\n");
        }
    }

    /**
     * Pide al usuario con sesion previamente iniciada que ingrese los datos para votar positiva o negativamente por una pregunta o respuesta que no sea de el.
     */
    private  void votarPreguntaRespuesta() {
        if(foro.getSesionIniciada() == null) {
            System.out.println("Debe previamente iniciar sesion para votar.\n");
        }

        else {
            System.out.println(foro.getListaPreguntas().mostrarPreguntasVote(foro.getSesionIniciada()));
            System.out.println("Ingrese el identificador de la pregunta o respuesta que desea votar(debe incluir la P o R): ");
            String idVotar = lector.nextLine();
            System.out.println("Ingrese un 0 para votar negativamente o un 1 para votar positivamente: ");
            int tipoVoto;
            String lineaLeida = lector.nextLine();;

            try {
                tipoVoto = Integer.parseInt((lineaLeida.trim()));
            }
            catch (NumberFormatException e) {
                tipoVoto = -1;
            }

            if(tipoVoto > 1 | tipoVoto < 0) {
                System.out.println("Tipo de voto invalido.\n");
                return;
            }

            if(idVotar.length() == 1) {
                System.out.println("Identificador ingresado invalido.\n");
                return;
            }

            foro.vote( idVotar, tipoVoto, foro.getSesionIniciada() );
            System.out.println("Se ha registrado correctamente el voto.\n");
        }
    }

    /**
     * Cierra la sesion del usuario que se encuentra actualmente iniciada.
     */
    private void cerrarSesion() {
        if( foro.getSesionIniciada() != null ) {
            foro.setSesionIniciada( null );
            System.out.println("Sesion finalizada correctamente.\n");
        }

        else {
            System.out.println("No existe una sesion iniciada para finalizar.\n");
        }
    }

    /**
     * Termina la ejecucion del programa.
     */
    private void terminarPrograma(){
        System.out.println("Cerrando el programa...\n");
    }
}
