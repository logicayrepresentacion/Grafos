/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package grafos.multilistaligadaadyacencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class GrafoMultilistaLigadaAdyacencia {

    NodoMultilista[] g;

    public GrafoMultilistaLigadaAdyacencia(NodoMultilista[] g) {
        this.g = g;
    }

    /**
     * Esta función crea un grafo en la representación desde un archivo La
     * configuración del archivo es una matriz de adyacencia, pues es más fácil
     * leer los nodos adyacentes e ir creando el grafo en la representación.
     *
     * @param fname
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static GrafoMultilistaLigadaAdyacencia crearGrafoMultilistaLigadaAdyacencia(String fname) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fname));

        // Leo la primera linea del archivo, esta contiene cuantos vertices tiene el grafo.
        String linea = scanner.nextLine();
        int n = Integer.parseInt(linea);

        // Creo el arreglo que representa el grafo.
        NodoMultilista[] gLocal = new NodoMultilista[n];
        GrafoMultilistaLigadaAdyacencia grafo = new GrafoMultilistaLigadaAdyacencia(gLocal);

        for (int i = 0; i < n; i++) {
            linea = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                int adya = linea.charAt(j);
                if (adya == 1) {
                    NodoMultilista nm = new NodoMultilista(i + 1, j + 1);
                    nm.setLigaVi(gLocal[i]);
                    gLocal[i] = nm;
                    nm.setLigaVj(gLocal[j]);
                    gLocal[j] = nm;
                }
            }
        }
        return grafo;
    }

    public void dfs() {
        int visitado[] = new int[g.length];
        dfs(1);
    }

    public void dfs(int v) {
        int visitado[] = new int[g.length];
        dfsRecursivo(v, visitado);
    }

    private void dfsRecursivo(int v, int[] visitado) {
        visitado[v] = 1;
        NodoMultilista nr = g[v - 1];
        while (nr != null) {
            int w = nr.getVj();
            if (visitado[w] == 0) {
                dfsRecursivo(w, visitado);
            }
            nr = nr.getLigaVi();
        }
    }
    
    

}
