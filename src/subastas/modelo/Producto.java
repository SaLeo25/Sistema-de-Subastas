package subastas.modelo;

import java.util.HashSet;
import java.util.Set;

public class Producto {

    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private boolean subastaActiva;

    private Set<String> categorias; //Usamos HashSet para las categorías para que evite duplicados automáticamente.

    public Producto(String id, String nombre, String desc, double precio) {

        this.idProducto = id;
        this.nombre = nombre;
        this.descripcion = desc;
        this.precioBase = precio;
        this.subastaActiva = true; //Por defecto la subasta inicia abierta.
        this.categorias = new HashSet<>();

    }

    public void agregarCategoria(String categoria) {

        categorias.add(categoria.toLowerCase()); //Guardamos en minúsculas para facilitar búsquedas después.

    }

    //Getters y Setters que necesitaremos.
    public String getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public boolean isSubastaActiva() { return subastaActiva; }
    public Set<String> getCategorias() { return categorias; }

    public void cerrarSubasta() {

        this.subastaActiva = false;

    }

    @Override
    public String toString() {

        return "ID: " + idProducto + " | " + nombre + " | Base: $" + precioBase + " | Activa: " + subastaActiva;

    }
}