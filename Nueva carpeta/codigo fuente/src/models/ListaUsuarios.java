package models;

import java.util.ArrayList;

/**
 * Clase que representa la lista de usuarios registrados en el foro.
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class ListaUsuarios{
    private ArrayList<Usuario> usuarios;    //Lista de objetos Usuario(agregacion)

    /**
     * Crea la lista de usuarios registrados inicial con usuarios pre registrados.
     */
    public ListaUsuarios() {
        ArrayList<Usuario> usuariosIniciales;
        usuariosIniciales = new ArrayList<>();

        //Se crean los usuarios pre registrados
        Usuario user1 = new Usuario("Jose", "123", 1);
        Usuario user2 = new Usuario("Esteban", "esteban12", 2);
        Usuario user3 = new Usuario("Thomas", "Thomas1999", 3);
        Usuario user4 = new Usuario("Jesus", "Jesus2000", 4);

        usuariosIniciales.add(user1);
        usuariosIniciales.add(user2);
        usuariosIniciales.add(user3);
        usuariosIniciales.add(user4);

        this.usuarios = usuariosIniciales;
    }

    /**
     * Obtine el array de usuarios.
     * @return Array de usuarios registrados.
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Agrega un usuario al final de la lista de usuarios.
     * @param nuevoUsuario Usuario que se desea agregar al final de la lista.
     */
    public void agregarUsuario(Usuario nuevoUsuario ) {
        this.usuarios.add( nuevoUsuario );
    }

    /**
     * Indica si no existe un usuario previamente registrado con el nombre que se desea registrar.
     * @param name Nombre que se desea comprobar si es unico.
     * @return true si no existe un usuario con el mismo nombre o false en otro caso.
     */
    public boolean usuarioUnico( String name ) {
        for ( Usuario user: usuarios ) {
            if( user.getNombre().equals(name) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Indica si se puede iniciar sesion del usuario con los datos ingresados
     * @param name Nombre del usuario que desea iniciar sesion.
     * @param pass Clave del usuario que desea iniciar sesion.
     * @return true si existe un usuario registrado y tanto su nombre como clave coinciden o false en otro caso.
     */
    public boolean verificarLogin( String name, String pass ) {
        for ( Usuario user: usuarios ) {
            if( user.getNombre().equals(name) && user.getContrasenia().equals(pass) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca el indice que tiene un usuario dentro de la lista de usuarios.
     * @param nombre Nombre del usuario que se desea conocer sus posicion.
     * @return Indice del objeto usuario que tenga que coincida con el nombre dentro de la lista de usuarios.
     */
    public int buscarIndiceUsuario( String nombre ) {
        for( int i = 0;  i < getUsuarios().size(); i++) {
            if( usuarios.get(i).getNombre().equals(nombre) ) {
                return i;
            }
        }
        return 0;
    }
}
