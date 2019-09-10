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
package grafos.matrizadyacencia.tripleta;

import grafos.listaligadaadyacencia.enteros.GrafoListaLigadaAdyacencia;
import grafos.listaligadaadyacencia.enteros.Nodo;
import matriz.tripleta.MatrizEnTripleta;

/**
 *
 * @author alejandroescobar
 */
public class GrafoMatrizAdyacenciaEnMatrizTripleta extends MatrizEnTripleta {

    public GrafoMatrizAdyacenciaEnMatrizTripleta(int f, int c) {
        super(f, c);
    }

    public int grado(int v) {
        int cv = (int) tripletas[0].getV();
        int grado = 0;
        for (int i = 1; i <= cv; i++) {
            int f = tripletas[i].getF();
            if (f < v) {
                continue;
            }
            if (f == v) {
                grado++;
            } else {
                break;
            }
        }
        return grado;
    }

    public int mayorGrado() {
        int cantidadValores = (int) tripletas[0].getV();
        int gradoMayor = 0;
        int gradoTemporal = 0;
        int verticeGradoMayor = 0;
        int verticeRecorrido = 1;
        for (int i = 1; i <= cantidadValores; i++) {
            int f = tripletas[i].getF();
            if (f == verticeRecorrido) {
                gradoTemporal++;
            } else if (f > verticeRecorrido) {
                if (gradoMayor < gradoTemporal) {
                    gradoMayor = gradoTemporal;
                    verticeGradoMayor = verticeRecorrido;
                }
                verticeRecorrido = f;
                i--;
                gradoTemporal = 0;
            }
        }

        if (gradoMayor < gradoTemporal) {
            gradoMayor = gradoTemporal;
            verticeGradoMayor = verticeRecorrido;
        }

        return verticeGradoMayor;
    }

    /**
     * Construye un Grafo Representado en Lista ligada de adyacencia basandose
     * en el grafo de la Matriz de Adyacencia almacenada en Matriz dispersa de
     * Tripletas
     *
     * @return
     */
    public GrafoListaLigadaAdyacencia parseGrafoListaLigadaAdyacencia() {

        /**
         * Creo el arreglo inicial
         */
        int cantidadVertices = (int) tripletas[0].getF();
        Nodo[] arregloGrafoListaLigadaAdyacencia = new Nodo[cantidadVertices];

        /**
         * Recorrido de la matriz de tripletas
         */
        int cantidadValores = (int) tripletas[0].getV();
        for (int i = 1; i <= cantidadValores; i++) {
            int f = tripletas[i].getF();
            int c = tripletas[i].getC();
            /**
             * Ingresar el verticeAdyancete a la lista ligada segun la posición
             * en el arreglo
             */
            Nodo verticeAdyacente = new Nodo(c);
            Nodo nodoViejoPrimero = arregloGrafoListaLigadaAdyacencia[f - 1];
            verticeAdyacente.setLiga(nodoViejoPrimero);
            arregloGrafoListaLigadaAdyacencia[f - 1] = verticeAdyacente;
        }

        return new GrafoListaLigadaAdyacencia(arregloGrafoListaLigadaAdyacencia);

    }

}
