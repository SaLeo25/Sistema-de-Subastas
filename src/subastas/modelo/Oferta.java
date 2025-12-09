package subastas.modelo;

import java.time.LocalDateTime;

/**
 * Clase que representa una oferta o puja en el sistema.
 * Implementamos Comparable para que el Montículo (Heap) sepa cómo ordenarlas.
 */

public class Oferta implements Comparable<Oferta> {

    private String idUsuario;
    private double monto;
    private LocalDateTime fechaHora; //Para desempatar por tiempo.

    public Oferta(String idUsuario, double monto) {

        this.idUsuario = idUsuario;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now(); //Se guarda la hora exacta de la oferta.

    }

    // Getters que necesitaremos.
    public String getIdUsuario() {

        return idUsuario;

    }

    public double getMonto() {

        return monto;

    }

    @Override
    public String toString() {

        return "Usuario: " + idUsuario + " | Monto: $" + monto;

    }

    /**
     * Esto es clave para el Heap por lo siguiente:
     * 1. Ordenamos por monto de mayor a menor (Max-Heap).
     * 2. Si los montos son iguales, gana el que llegó primero (fecha menor).
     */

    @Override
    public int compareTo(Oferta otra) {

        if (this.monto != otra.monto) {

            return Double.compare(otra.monto, this.monto); //Invertimos el orden para que sea de mayor a menor.

        }

        return this.fechaHora.compareTo(otra.fechaHora); //Si hay empate en dinero, gana el más antiguo.

    }

}