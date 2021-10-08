package ejemplo.grafos.recoleccionbasura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * RecoleccionBasura
 */
public class RecoleccionBasura {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Cargar el archivo en un grafo
        GrafoMultilistaLigadaAdyacenciaDinamicoCosto mapa = cargarMapaDesdeArchivo();
        System.out.println(mapa);
        System.out.println(Arrays.toString(mapa.dijkstra(1)));
    }

    private static GrafoMultilistaLigadaAdyacenciaDinamicoCosto cargarMapaDesdeArchivo() throws FileNotFoundException, IOException {

        BufferedReader bin = new BufferedReader(new FileReader("recoleccion.txt"));
        GrafoMultilistaLigadaAdyacenciaDinamicoCosto mapaLocal = new GrafoMultilistaLigadaAdyacenciaDinamicoCosto();
        String linea;
        while ((linea = bin.readLine()) != null) {
            // 19,1:”esquina 1”:25:19,2:”esquina 2”
            String partes[] = linea.split(":");
            Sitio origen = new Sitio(partes[0], partes[1]);
            int costo = Integer.parseInt(partes[2]);
            Sitio destino = new Sitio(partes[3], partes[4]);
            mapaLocal.crearAdyacencia(origen, destino, costo);
        }
        return mapaLocal;
    }

}
