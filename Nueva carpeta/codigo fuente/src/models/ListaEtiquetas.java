package models;

import java.util.ArrayList;

/**
 * Clase que representa una lista de etiquetas registradas en el foro o dentro de una pregunta
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class ListaEtiquetas {
    private ArrayList<Etiqueta> etiquetas;  //Array de Etiqueta (agregacion)

    /**
     * Crea una lista de etiquetas vacia.
     */
    public ListaEtiquetas() {
        ArrayList<Etiqueta> etiquetasVacia;
        etiquetasVacia = new ArrayList<>();
        this.etiquetas = etiquetasVacia;
    }

    /**
     * Crea una lista de etiquetas pre registradas dentro del foro.
     * @param indicadorInicioStack Indica que se esta utilizando el constructor para crear las etiquetas pre registradas.
     */
    public ListaEtiquetas( int indicadorInicioStack ) {
        ArrayList<Etiqueta> etiquetasIniciales;
        etiquetasIniciales = new ArrayList<>();

        Etiqueta tag1 = new Etiqueta("Java", "Pregunta relacionada con el leguaje de programacion Java");
        Etiqueta tag2 = new Etiqueta("Urgente", "Pregunta que necesita ser respuesta lo antes posible");
        Etiqueta tag3 = new Etiqueta("Python", "Pregunta relacionada con el leguaje de programacion Python");
        Etiqueta tag4 = new Etiqueta("C", "Pregunta relacionada con el lenguaje de programacion C");
        Etiqueta tag5 = new Etiqueta("POO", "Pregunta relacionada con la programacion orientada a objetos");

        etiquetasIniciales.add(tag1);
        etiquetasIniciales.add(tag2);
        etiquetasIniciales.add(tag3);
        etiquetasIniciales.add(tag4);
        etiquetasIniciales.add(tag5);

        this.etiquetas = etiquetasIniciales;
    }

    /**
     * Obtiene el array con las etiquetas.
     * @return Array de etiquetas.
     */
    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    /**
     * Agrega una etiqueta al final de la lista de etiquetas.
     * @param nuevaEtiqueta Etiqueta que se desea agregar al final de la lista de etiquetas.
     */
    public void agregarEtiqueta(Etiqueta nuevaEtiqueta ) {
        this.etiquetas.add(nuevaEtiqueta);
    }

    /**
     * Crea un largo String con el nombre de las etiquetas de una pregunta.
     * @return Largo String con las eqtiquetas de una pregunta.
     */
    @Override
    public String toString() {
        String etiquetasString = "";
        for(Etiqueta etiqueta: etiquetas) {
            if(etiquetasString.equals("")) {
                etiquetasString = etiquetasString + etiqueta.getNombre();
            }
            else {
                etiquetasString = etiquetasString + ", " + etiqueta.getNombre();
            }
        }

        return etiquetasString;
    }

}
