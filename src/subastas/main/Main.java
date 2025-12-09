package subastas.main;

import subastas.estructura.SistemaSubastas;
import subastas.modelo.Producto;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaSubastas sistema = new SistemaSubastas();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        preloadDatos(sistema); //Carga de datos de prueba para no empezar de cero.

        while (opcion != 7) {

            System.out.println("\n=== SISTEMA DE SUBASTAS Y PUJAS ===");
            System.out.println("1. Registrar usuario.");
            System.out.println("2. Crear subasta (Publicar Producto).");
            System.out.println("3. Ver Subastas activas");
            System.out.println("4. Realizar oferta (pujar)");
            System.out.println("5. Consultar mejor oferta de un producto");
            System.out.println("6. Cerrar subasta (determinar ganador)");
            System.out.println("7. Salir");
            System.out.println("8. [Extra] Ver recomendaciones por usuario");
            System.out.print("Elige una opción: ");

            try {

                String entrada = scanner.nextLine();
                opcion = Integer.parseInt(entrada);

            } catch (NumberFormatException e) {

                opcion = -1;

            }

            switch (opcion) {

                case 1:
                    System.out.print("ID usuario (ej. u1): ");
                    String uid = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String unom = scanner.nextLine();
                    sistema.registrarUsuario(uid, unom);

                    System.out.print("¿Agregar interés? (si/no): ");
                    if(scanner.nextLine().equalsIgnoreCase("si")){
                        System.out.print("Categoría (ej. gaming, arte): ");
                        sistema.buscarUsuario(uid).agregarInteres(scanner.nextLine());
                    }
                    break;

                case 2:
                    System.out.print("ID producto (ej. p1): ");
                    String pid = scanner.nextLine();
                    System.out.print("Nombre del producto: ");
                    String pnom = scanner.nextLine();
                    System.out.print("Precio base: ");

                    try {
                        double precio = Double.parseDouble(scanner.nextLine());
                        sistema.registrarProducto(pid, pnom, "Descripción pendiente", precio);

                        //Esto es opcional (Agregar categoría)
                        Producto p = sistema.buscarProducto(pid);
                        System.out.print("Categoría del producto (ej. tecnologia): ");
                        p.agregarCategoria(scanner.nextLine());

                    } catch (NumberFormatException e) {

                        System.out.println("Error: El precio debe ser numérico.");

                    }
                    break;

                case 3:
                    sistema.listarTodo();
                    break;

                case 4:
                    System.out.print("Tu ID de usuario: ");
                    String uOferta = scanner.nextLine();
                    System.out.print("ID del producto a ofertar: ");
                    String pOferta = scanner.nextLine();
                    System.out.print("Monto a ofertar: ");

                    try {

                        double monto = Double.parseDouble(scanner.nextLine());
                        sistema.realizarOferta(uOferta, pOferta, monto);

                    } catch (NumberFormatException e) {

                        System.out.println("Error: Monto inválido.");

                    }
                    break;

                case 5:
                    System.out.print("ID del producto: ");
                    sistema.mostrarMejorOferta(scanner.nextLine());
                    break;

                case 6:
                    System.out.print("ID del producto a cerrar: ");
                    sistema.cerrarSubasta(scanner.nextLine());
                    break;

                case 7:
                    System.out.println("Cerrando sistema... ¡Hasta luego!");
                    break;

                case 8:
                    System.out.print("ID Usuario para ver recomendaciones: ");
                    sistema.recomendarSubastas(scanner.nextLine());
                    break;

                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    private static void preloadDatos(SistemaSubastas sis) {
        sis.registrarUsuario("user1", "Juan Perez");
        sis.buscarUsuario("user1").agregarInteres("gaming");

        sis.registrarUsuario("user2", "Maria Lopez");
        sis.buscarUsuario("user2").agregarInteres("arte");

        sis.registrarProducto("prod1", "PlayStation 1", "Consola retro", 500);
        sis.buscarProducto("prod1").agregarCategoria("gaming");
        sis.buscarProducto("prod1").agregarCategoria("retro");

        sis.registrarProducto("prod2", "Pintura al Oleo", "Original", 1200);
        sis.buscarProducto("prod2").agregarCategoria("arte");
    }
}