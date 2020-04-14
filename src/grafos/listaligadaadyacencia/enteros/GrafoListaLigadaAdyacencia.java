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
package grafos.listaligadaadyacencia.enteros;

import grafos.Conjunto;
import grafos.Lado;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import matriz.arreglobidimensional.MatrizEnArregloBidimensional;

/**
 *
 * @author Alejandro
 */
public class GrafoListaLigadaAdyacencia {

    Nodo[] arregloNodosVertice;
    matriz.arreglobidimensional.MatrizEnArregloBidimensional matrizCostos;

    public GrafoListaLigadaAdyacencia(Nodo[] g) {
        this.arregloNodosVertice = g;
    }

    public GrafoListaLigadaAdyacencia(int numeroVertices) {
        arregloNodosVertice = new Nodo[numeroVertices];
    }

    public void crearAdyacencia(int i, int j) {
        Nodo nodoJ = new Nodo(j);
        Nodo u = arregloNodosVertice[i];
        nodoJ.setLiga(u);
        arregloNodosVertice[i] = nodoJ;
    }

    public int grado(int v) {
        int grado = 0;
        Nodo r = arregloNodosVertice[v];
        while (r != null) {
            grado++;
            r = r.getLiga();
        }
        return grado;
    }

    public int getN() {
        return arregloNodosVertice.length;
    }

    /**
     * Ejercicio 4 del texto guia
     */
    public void bfs() {
        int[] visitados = new int[arregloNodosVertice.length];
        Queue<Nodo> cola = new LinkedList<>();
        visitados[0] = 1;
        cola.add(arregloNodosVertice[0]);
        Nodo vaux;
        while (!cola.isEmpty()) {
            vaux = cola.poll();
            System.out.println("Visitando " + vaux.getVertice());
            Nodo t = vaux.getLiga();
            while (t != null) {
                int i = 0;
                //  int i = t.getVertice();
                if (visitados[i] == 0) {
                    cola.add(t);
                    visitados[i] = 1;
                }
                t = t.getLiga();
            }
        }
    }

    /**
     * Ejercicio 4 del texto guia
     */
    public void dfs(int verticeInicio) throws Exception {
        if (verticeInicio >= arregloNodosVertice.length) {
            throw new Exception("el vertice no existe");
        }
        Nodo v = arregloNodosVertice[verticeInicio];
        int[] visitados = new int[arregloNodosVertice.length];
        DFSRecursivo(visitados, v);
    }

    /**
     * Ejercicio 4 del texto guia
     *
     * @throws java.lang.Exception
     */
    public void dfs() throws Exception {
        this.dfs(0);
    }

    private void DFSRecursivo(int[] visitados, Nodo v) {
        visitados[v.getVertice()] = 1;
        System.out.println("Visitando " + v.getVertice());
        Nodo recorrido = v.getLiga();
        while (recorrido != null) {
            int i = recorrido.getVertice();
            if (visitados[i] == 0) {
                DFSRecursivo(visitados, recorrido);
            }
            recorrido = recorrido.getLiga();
        }
    }

    public void setMatrizCostos(MatrizEnArregloBidimensional matrizCostos) {
        this.matrizCostos = matrizCostos;
    }

    public Conjunto prim() throws Exception {
        if (matrizCostos == null) {
            throw new Exception("No tienes matriz de costos, cargar antes de ejecución");
        }

        Conjunto st = new Conjunto();
        Conjunto lados = crearLados();
        Conjunto conectados = new Conjunto();
        Conjunto noConectados = crearNoConectados();

        Nodo v = (Nodo) noConectados.get();
        conectados.add(v.getVertice());
        noConectados.remove(v.getVertice());
        Lado ladoMenorCosto;
        while (!noConectados.empty()) {
            ladoMenorCosto = seleccioneLado(conectados, lados);
            st.add(ladoMenorCosto);
            conectados.add(ladoMenorCosto.getVi());
            conectados.add(ladoMenorCosto.getVj());
            noConectados.remove(ladoMenorCosto.getVi());
            noConectados.remove(ladoMenorCosto.getVj());
        }
        return st;
    }

    private Conjunto crearLados() {
        Conjunto lados = new Conjunto();
        for (Nodo n : arregloNodosVertice) {
            Nodo primero = n.getLiga();
            while (primero != null) {
                Lado l = new Lado(n.getVertice(), primero.getVertice(), (int) matrizCostos.getCelda(n.getVertice(), primero.getVertice()));
                primero = primero.getLiga();
                lados.add(l);
            }
        }
        return lados;
    }

    private Conjunto crearNoConectados() {
        Conjunto noConectados = new Conjunto();
        for (Nodo n : arregloNodosVertice) {
            noConectados.add(n.getVertice());
        }
        return noConectados;
    }

    private Lado seleccioneLado(Conjunto conectados, Conjunto lados) {
        Lado ladoMenorCosto = null;
        for (Iterator iterator = conectados.iterator(); iterator.hasNext();) {
            Comparable v1 = (Comparable) iterator.next();
            for (Iterator iterator1 = lados.iterator(); iterator1.hasNext();) {
                Lado lado = (Lado) iterator1.next();
                if (lado.contieneVx(v1)) {
                    if (ladoMenorCosto != null) {
                        if (lado.getCosto() < ladoMenorCosto.getCosto()) {
                            ladoMenorCosto = lado;
                        }
                    } else {
                        ladoMenorCosto = lado;
                    }
                }

            }
        }
        return ladoMenorCosto;
    }

    /**
     * static public GrafoListaLigadaAdyacencia
     * parseGrafoMatrizIncidenciaEnListaLigadaForma1(GrafoMatrizIncidenciaEnListaLigadaForma1
     * grafoOriginal) { NodoCabeza nc = grafoOriginal.getGrafo().getNc();
     *
     * int n = nc.getT().getC(); Nodo[] gLocal = new Nodo[n];
     *
     * NodoCabeza r = nc.getLiga(); while (r != nc) { NodoDoble primerVertice =
     * r.getLigaC(); NodoDoble segundoVertice = primerVertice.getLigaC(); int v1
     * = primerVertice.getT().getF(); int v2 = segundoVertice.getT().getF();
     *
     * Nodo nodoV1 = new Nodo(v1); Nodo nodoV2 = new Nodo(v2);
     *
     * nodoV2.setLiga( gLocal[v1 - 1] ); gLocal[v1 - 1] = nodoV2;
     *
     * Nodo nodoAux = gLocal[v2 - 1]; if (nodoAux == null) { gLocal[v2 - 1] =
     * nodoV1; }else { nodoV1.setLiga( nodoAux ); gLocal[v2 - 1] = nodoV1; } }
     *
     * GrafoListaLigadaAdyacencia grafoListaLigadaAdyacencia = new
     * GrafoListaLigadaAdyacencia(gLocal); return grafoListaLigadaAdyacencia; }
     *
     */
}
