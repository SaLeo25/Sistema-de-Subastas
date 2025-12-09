// Subasta.java
public class Subasta {
    private String idSubasta;
    private Producto producto;
    private boolean activa;
    private long tiempoInicio;
    private MaxHeap ofertas;
    
    public Subasta(String idSubasta, Producto producto) {
        this.idSubasta = idSubasta;
        this.producto = producto;
        this.activa = true;
        this.tiempoInicio = System.currentTimeMillis();
        this.ofertas = new MaxHeap();
    }
    
    public String getIdSubasta() { return idSubasta; }
    public Producto getProducto() { return producto; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
    
    public void agregarOferta(Oferta oferta) {
        if (activa && oferta.getMonto() > producto.getPrecioBase()) {
            ofertas.insertar(oferta);
        }
    }
    
    public Oferta getMejorOferta() {
        return ofertas.consultarMaximo();
    }
    
    public Oferta cerrarSubasta() {
        activa = false;
        return ofertas.extraerMaximo();
    }
    
    public Oferta[] obtenerTodasOfertas() {
        return ofertas.obtenerTodas();
    }
    
    @Override
    public String toString() {
        String estado = activa ? "ACTIVA" : "CERRADA";
        return "Subasta " + idSubasta + " [" + estado + "] - " + producto.getNombre();
    }
}
