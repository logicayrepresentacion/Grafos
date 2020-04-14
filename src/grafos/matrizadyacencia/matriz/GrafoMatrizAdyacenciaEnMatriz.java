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

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Alejandro
 */
public class GrafoMatrizAdyacenciaEnMatriz {

    int n;
    int[][] matrizAdyacencia;

    public GrafoMatrizAdyacenciaEnMatriz(int cantidadVertices) {
        matrizAdyacencia = new int[cantidadVertices][cantidadVertices];
    }

    /**
     * Se supone que los vertices comienzan en 1
     *
     * @param i
     * @param j
     */
    public void crearAdyacencia(int i, int j) {
        matrizAdyacencia[i][j] = 1;
    }

    /**
     * Retorna el grado de un vertice
     *
     * @param v
     * @return
     */
    public int grado(int v) {
        int contadorGrado = 0;
        for (int j = 0; j < matrizAdyacencia.length; j++) {
            if (matrizAdyacencia[v][j] == 1) {
                contadorGrado++;
            }
        }
        return contadorGrado;
    }

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
////
    /**
     * Esta método se basa en el algoritmo general de BSF Se crea una tabla
     * donde la fila corresponde a la base que enviaría el mensaje y la columna
     * si tiene un 1 es donde debe enviar el mensaje En el caso que la fila
     * tenga todo en 0, siginifica que esa base no debe enviar mensaje a ninguna
     * base. Se va imprimiendo a quien se debe entregar el mensaje.
     *
     * @param v
     */
    int[][] crearTabla(int v) {
        // Arreglo 
        int[] visitados = new int[n];
        int[][] tablaEnvios = new int[n][n];
        Queue cola = new LinkedList();
        visitados[v] = 1;
        cola.add(v);
        while (!cola.isEmpty()) {
            v = (int) cola.poll();
            System.out.print("El vertice " + v + " debe enviar a los vertices \t");
            for (int w = 0; w < n; w++) {
                if (matrizAdyacencia[v][w] == 1) {
                    visitados[w] = 1;
                    System.out.print(" - " + w + "\t");
                    cola.add(w);
                    tablaEnvios[v][w] = 1;
                }
            }
        }
        return tablaEnvios;
    }

    /**
     * Ejercicio 4 del texto guia
     *
     * @param verticeInicio
     * @throws java.lang.Exception
     */
    public void dfs(int verticeInicio) throws Exception {
        if (verticeInicio >= matrizAdyacencia.length) {
            throw new Exception("el vertice no existe");
        }
        int[] visitados = new int[matrizAdyacencia.length];
        DFSRecursivo(visitados, verticeInicio);
    }

    /**
     * Ejercicio 4 del texto guia
     *
     * @throws java.lang.Exception
     */
    public void dfs() throws Exception {
        this.dfs(0);
    }

    private void DFSRecursivo(int[] visitados, int v) {
        visitados[v] = 1;
        System.out.println("Visitando " + v);

        for (int w = 0; w < matrizAdyacencia.length; w++) {
            if (matrizAdyacencia[v][w] == 1) {
                if (visitados[w] == 0) {
                    DFSRecursivo(visitados, w);
                }
            }

        }
    }

    public void bfs() {
        this.bfs(0);
    }

    public void bfs(int v) {
        Queue cola = new ArrayDeque();
        int[] visitado = new int[matrizAdyacencia.length];
        visitado[v] = 1;
        System.out.println("Visitando " + v);
        cola.add(v);
        while (!cola.isEmpty()) {
            v = (int) cola.poll();
            for (int w = 0; w < matrizAdyacencia.length; w++) {
                if (matrizAdyacencia[v][w] == 1) {
                    if (visitado[w] == 0) {
                        visitado[w] = 1;
                        System.out.println("Visitando " + v);
                        cola.add(w);
                    }
                }
            }
        }

    }

}
