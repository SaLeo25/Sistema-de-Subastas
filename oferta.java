// Oferta.java
public class Oferta {
    private String idUsuario;
    private double monto;
    private long timestamp;
    
    public Oferta(String idUsuario, double monto) {
        this.idUsuario = idUsuario;
        this.monto = monto;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String getIdUsuario() { return idUsuario; }
    public double getMonto() { return monto; }
    public long getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return "Usuario: " + idUsuario + " | Monto: $" + monto + " | Tiempo: " + timestamp;
    }
}
