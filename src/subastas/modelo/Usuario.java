package subastas.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {

    private String idUsuario;
    private String nombre;

    private Set<String> intereses; //Conjunto de intereses para las recomendaciones (Set).

    private List<String> historialCompras;  //Historial simple de compras en una lista.

    public Usuario(String idUsuario, String nombre) {

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.intereses = new HashSet<>();
        this.historialCompras = new ArrayList<>();

    }

    public void agregarInteres(String interes) {

        intereses.add(interes.toLowerCase());

    }

    public void agregarCompra(String detalleCompra) {

        historialCompras.add(detalleCompra);

    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public Set<String> getIntereses() { return intereses; }

    @Override
    public String toString() {

        return idUsuario + " - " + nombre;

    }
}