// Producto.java
public class Producto {
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private String[] categorias;
    
    public Producto(String idProducto, String nombre, String descripcion, double precioBase, String[] categorias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.categorias = categorias;
    }
    
    public String getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioBase() { return precioBase; }
    public String[] getCategorias() { return categorias; }
    
    @Override
    public String toString() {
        String cats = "";
        for (int i = 0; i < categorias.length; i++) {
            cats += categorias[i];
            if (i < categorias.length - 1) cats += ", ";
        }
        return "ID: " + idProducto + " | " + nombre + " | $" + precioBase + " | Categorías: " + cats;
    }
}
