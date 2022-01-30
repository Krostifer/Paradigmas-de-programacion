package models;

/**
 * Clase que representa un usuario registrado dentro del foro
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Usuario {

    private String nombre;  //Nombre del usuario
    private String contrasenia; //Clave del usuario
    private int reputacion; //Puntos de reputacion actuales del usuario

    /**
     * Crea un usuario a partir de los datos entregados.
     * @param nombre Nombre del nuevo usuario.
     * @param contrasenia Clave de acceso del nuevo usuario.
     * @param reputacion Puntos de reputación del usuario.
     */
    public Usuario(String nombre, String contrasenia, int reputacion) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.reputacion = reputacion;
    }

    /**
     * Obtine el nombre del usuario.
     * @return String que representa el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtine la clave del usuario.
     * @return String que representa la clave del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Obtine los puntos de reputacion del usuario.
     * @return Entero que representa la cantidad de puntos de reputacion del usuario.
     */
    public int getReputacion() {
        return reputacion;
    }

    /**
     * Suma la cantidad ingresada a la reputación del usuario.
     * @param cambioReputacion Entero que representa lo que se desea sumar(positivo) o restar(negativo) a la reputacion del usuario
     */
    public void setReputacion(int cambioReputacion) {
        this.reputacion = reputacion + cambioReputacion;
    }
}
