package ejemplo.grafos.recoleccionbasura;

import java.util.Objects;

/**
 * Sitio
 */
public class Sitio implements Comparable {

    private final String nombre;
    private final String coordenada;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public Sitio(String nombre, String coordenada) {
        this.nombre = nombre;
        this.coordenada = coordenada;
    }

    @Override
    public boolean equals(Object obj) {
        Sitio otroSitio = (Sitio) obj;
        return (this.coordenada.equalsIgnoreCase(otroSitio.getCoordenada()));

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.coordenada);
        return hash;
    }

    @Override
    public int compareTo(Object obj) {
        Sitio otroSitio = (Sitio) obj;
        return this.coordenada.compareTo(otroSitio.getCoordenada());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre + " " + coordenada + " " + id;
    }

}
