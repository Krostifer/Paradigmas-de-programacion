public class Test {

    public static void main(String[] args) {

        BookPlus bp = new BookPlus();

        // Crear libros
        LibroFisico lf1 = new LibroFisico(....);
        LibroDigital ld1 = new LibroDigital(...);

	    //Agregar libros a colección
        bp.agregarLibro(lf1);
        bp.agregarLibro(ld1);

        // Registrar clientes en el sistema
        Cliente nuevoCliente1 = new Cliente(...);
        Cliente nuevoCliente2 = new Cliente(...);

        bp.agregarUsuario(nuevoCliente1);
        bp.agregarUsuario(nuevoCliente2);

        // Iniciar sesión con uno de los clientes
        bp.iniciarSesion(nuevoCliente1);

        // Arrendar un libro
        bp.arrendarLibro(nuevoCliente1, lf1);
    }
}
