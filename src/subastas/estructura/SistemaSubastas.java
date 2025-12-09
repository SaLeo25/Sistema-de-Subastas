package subastas.estructura;

import subastas.modelo.Oferta;
import subastas.modelo.Producto;
import subastas.modelo.Usuario;

import java.util.*;

/**
 * Creamos la clase principal que gestiona la lógica del negocio.
 * Aquí aplicamos las estructuras de datos solicitadas:
 * 1. HashMap que se usa para buscar rápido usuarios y productos por ID.
 * 2. PriorityQueue (Heap) que se usa para mantener las ofertas ordenadas (la mayor siempre arriba).
 * 3. Sets (Conjuntos) que se usa para las categorías e intereses usado en la clase Producto/Usuario.
 */

public class SistemaSubastas {

    private Map<String, Producto> mapaProductos; //Encontramos aquí las estructuras de datos principales.
    private Map<String, Usuario> mapaUsuarios;
    private Map<String, PriorityQueue<Oferta>> subastasActivas; //Mapeamos el ID Producto que es el montículo de las ofertas.

    public SistemaSubastas() {

        this.mapaProductos = new HashMap<>();
        this.mapaUsuarios = new HashMap<>();
        this.subastasActivas = new HashMap<>();

    }

    //Aquí realizamos la gestión de usuarios.
    public void registrarUsuario(String id, String nombre) {

        if (mapaUsuarios.containsKey(id)) {

            System.out.println("Error: El usuario con ID " + id + " ya existe.");
            return;

        }

        Usuario nuevo = new Usuario(id, nombre);
        mapaUsuarios.put(id, nuevo);
        System.out.println("Usuario " + nombre + " registrado correctamente.");

    }

    public Usuario buscarUsuario(String id) {

        return mapaUsuarios.get(id);

    }

    //Aquí realizamos la gestión de productos.
    public void registrarProducto(String id, String nombre, String desc, double precio) {

        if (mapaProductos.containsKey(id)) {

            System.out.println("Error: El producto ya existe.");
            return;

        }

        Producto p = new Producto(id, nombre, desc, precio);
        mapaProductos.put(id, p);

        //Aquí inicializamos el Heap (montículo) vacío para este producto.
        //Como la clase Oferta tiene 'compareTo', la cola se ordena sola.
        subastasActivas.put(id, new PriorityQueue<>());

        System.out.println("Producto publicado: " + nombre);

    }

    public Producto buscarProducto(String id) {

        return mapaProductos.get(id);

    }

    //Aquí realizamos la lógica de ofertas (Heap).
    public void realizarOferta(String idUsuario, String idProducto, double monto) {

        if (!mapaUsuarios.containsKey(idUsuario)) { //Validaciones básicas.

            System.out.println("Usuario no encontrado.");
            return;

        }

        Producto p = mapaProductos.get(idProducto);
        if (p == null) {

            System.out.println("Producto no encontrado.");
            return;

        }

        if (!p.isSubastaActiva()) {

            System.out.println("Lo sentimos, esta subasta ya finalizó. :(");
            return;

        }

        PriorityQueue<Oferta> cola = subastasActivas.get(idProducto); //Aquí obtenemos la cola de prioridad (Heap) de este producto.

        double ofertaMasAlta = cola.isEmpty() ? p.getPrecioBase() : cola.peek().getMonto(); //Verificamos si la oferta es mayor a la actual o al precio base.

        if (monto > ofertaMasAlta) {

            Oferta nueva = new Oferta(idUsuario, monto);
            cola.offer(nueva); //.offer inserta en el Heap manteniendo el orden.
            System.out.println("¡Oferta aceptada de $" + monto + " por " + p.getNombre() + "!");

        } else {

            System.out.println("Oferta rechazada: Debes superar $" + ofertaMasAlta);

        }

    }

    public void mostrarMejorOferta(String idProducto) {

        PriorityQueue<Oferta> cola = subastasActivas.get(idProducto);
        if (cola != null && !cola.isEmpty()) {

            //.peek() mira el tope del montículo sin sacarlo (la oferta más alta).
            System.out.println("La oferta ganadora actual es: " + cola.peek());

        } else {

            System.out.println("No hay ofertas para este producto aún.");

        }

    }

    //Aquí nos encargamos de cerrar la subasta.
    public void cerrarSubasta(String idProducto) {

        Producto p = mapaProductos.get(idProducto);

        if (p == null || !p.isSubastaActiva()) {

            System.out.println("No se puede cerrar, pues el producto no existe o ya ha cerrado.");
            return;

        }

        PriorityQueue<Oferta> cola = subastasActivas.get(idProducto);

        if (cola.isEmpty()) {

            System.out.println("La subasta cerró sin ofertas.");

        } else {

            //.poll() extrae el elemento raíz del Heap (el ganador).
            Oferta ganadora = cola.poll();
            Usuario usuarioGanador = mapaUsuarios.get(ganadora.getIdUsuario());

            System.out.println("=== SUBASTA CERRADA ===");
            System.out.println("Ganador: " + usuarioGanador.getNombre());
            System.out.println("Pagará: $" + ganadora.getMonto());

            usuarioGanador.agregarCompra(p.getNombre() + " - $" + ganadora.getMonto()); //Agregamos al historial del usuario.
        }

        p.cerrarSubasta();

    }

    //Aquí realizamos las recomendaciones (intersección de conjuntos).
    public void recomendarSubastas(String idUsuario) {

        Usuario u = mapaUsuarios.get(idUsuario);
        if (u == null) return;

        System.out.println("Buscando productos para: " + u.getNombre() + "...");
        boolean encontro = false;

        //Recorremos todos los productos mediante un for.
        for (Producto p : mapaProductos.values()) {

            if (p.isSubastaActiva()) {

                Set<String> interseccion = new HashSet<>(u.getIntereses()); //Creamos un set temporal copia de los intereses del usuario.

                interseccion.retainAll(p.getCategorias());  //retainAll deja solo los elementos que se repiten en ambos conjuntos (intersección).

                if (!interseccion.isEmpty()) {

                    System.out.println("Recomendación: " + p.getNombre() + " (Coincide en: " + interseccion + ")");
                    encontro = true;

                }

            }

        }

        if (!encontro) System.out.println("No encontramos productos activos según tus intereses.");

    }

    //Esto es auxiliar para ver todo.
    public void listarProductos() {

        System.out.println("--- Lista de Subastas ---");
        for (Producto p : mapaProductos.values()) {

            String estado = p.isSubastaActiva() ? "[ACTIVA]" : "[CERRADA]";
            System.out.println(estado + " " + p);

        }

    }

}