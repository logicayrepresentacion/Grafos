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
package grafos.matrizadyacencia.matriz;

import com.sun.javafx.geom.AreaOp;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Alejandro
 */
public class GrafoMatrizAdyacenciaEnMatriz {

    int n;
    int[][] adya;

    /**
     *
     * @return
     */
    /**
     * public Conjunto kruskal() { Conjunto<Lado> cE =
     * obtenerConjuntoLadosOrdenadosCosto(); Conjunto<Conjunto<Integer>> cV =
     * obtenerConjuntoConjuntoVertices(); // OJO Adicionar conjuntos con los
     * vertices de forma individula
     *
     * Conjunto<Lado> cSt = new Conjunto(); int i = 0; while (i < n - 1) { Lado
     * lado = cE.get(); int u = (int) lado.getVi(); int w = (int) lado.getVj();
     * cE.remove(lado); if (!formaCiclo(u, w, cV)) { cSt.add(lado);
     * unirConjuntos(u, w, cV); i = i + 1; } } return cSt; }
     */
    void dfs(int v) {

    }

    /**
     * Esta método se basa en el algoritmo general de BSF Se crea una tabla
     * donde la fila corresponde a la base que enviaría el mensaje y la columna
     * si tiene un 1 es donde debe enviar el mensaje En el caso que la fila
     * tenga todo en 0, siginifica que esa base no debe enviar mensaje a ninguna
     * base. Se va imprimiendo a quien se debe entregar el mensaje.
     *
     * @param v
     */
    void crearTabla(int v) {
        // Arreglo 
        int[] visitados = new int[n];
        int[][] tablaEnvios = new int[n][n];
        Queue cola = new LinkedList();
        visitados[v] = 1;
        cola.add(v);
        while (!cola.isEmpty()) {
            v = cola.poll();
            System.out.print("El vertice " + v + " debe enviar a los vertices \t");
            for (int w = 0; w < n; w++) {
                if (adya[v][w] == 1) {
                    visitados[w] = 1;
                    System.out.print(" - " + w + "\t");
                    cola.add(w);
                    tablaEnvios[v][w] = 1;
                }
            }
        }
        return tablaEnvios;
    }

}
