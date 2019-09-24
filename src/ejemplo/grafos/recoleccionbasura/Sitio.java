package ejemplo.grafos.recoleccionbasura;

/**
 * Sitio
 */
public class Sitio implements Comparable {

    private String nombre;
    private String coordenada;

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
        return (this.nombre.equals(otroSitio.getNombre() ) && this.coordenada.endsWith(otroSitio.getCoordenada()) );
       
    }


}