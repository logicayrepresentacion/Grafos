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
package ejemplo.grafos.recoleccionbasura;

import arbol.binario.listaligada.busqueda.avl.ArbolAVL;
import arbol.binario.listaligada.busqueda.avl.NodoAVL;
import grafos.multilistaligadaadyacencia.NodoMultilista;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import matriz.listaligadaforma2.MatrizEnListaLigadaForma2;

/**
 *
 * @author Alejandro
 *
 */
public class GrafoMultilistaLigadaAdyacenciaDinamicoCosto {

    private int proximoID = 0;
    ArbolAVL<NodoEspeciales> grafo;
    ArbolAVL<Sitio> indiceSitio;
    private MatrizEnListaLigadaForma2 matricostos;

    public GrafoMultilistaLigadaAdyacenciaDinamicoCosto() {
        grafo = new ArbolAVL<>();
        indiceSitio = new ArbolAVL<>();
        matricostos = new MatrizEnListaLigadaForma2(0, 0);
    }

    public void crearAdyacencia(Sitio origen, Sitio destino, double costo) {

        // ArbolAVL de los sitios
        NodoAVL origenObj = indiceSitio.buscar(origen);
        if (origenObj == null) {
            int idOrigen = ++proximoID;
            origen.setId(idOrigen);
            indiceSitio.insertarDato(origen);
        } else {
            origen = (Sitio) origenObj.getDato();
        }

        NodoAVL destinoObj = indiceSitio.buscar(destino);
        if (destinoObj == null) {
            int idDestino = ++proximoID;
            destino.setId(idDestino);
            indiceSitio.insertarDato(destino);
        } else {
            destino = (Sitio) destinoObj.getDato();
        }

        // ArbolAVL de los NodoEspeciales
        NodoEspeciales origenN = new NodoEspeciales(origen);
        NodoAVL origenObjN = grafo.buscar(origenN);
        if (origenObjN == null) {
            grafo.insertarDato(origenN);
            matricostos.aumentarMatriz(1);
        } else {
            origenN = (NodoEspeciales) origenObjN.getDato();
        }

        NodoEspeciales destinoN = new NodoEspeciales(destino);
        NodoAVL destinoObjN = grafo.buscar(destinoN);
        if (destinoObjN == null) {
            grafo.insertarDato(destinoN);
            matricostos.aumentarMatriz(1);
        } else {
            destinoN = (NodoEspeciales) destinoObjN.getDato();
        }

        NodoMultilista nodoMulti = new NodoMultilista(origen.getId(), destino.getId());
        nodoMulti.setLigaVi(origenN.nm);
        origenN.nm = nodoMulti;
        nodoMulti.setLigaVj(destinoN.nm);
        destinoN.nm = nodoMulti;

        matricostos.setCelda(origen.getId(), destino.getId(), costo);
        matricostos.setCelda(destino.getId(), origen.getId(), costo);

    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        try {
            sw.write("Indice sitios\n");
            indiceSitio.inorden(sw);
            sw.write("Grafo\n");
            grafo.inorden(sw);
            sw.write("Matriz de costos\n");
            sw.write(matricostos.toString());
        } catch (IOException ex) {
            Logger.getLogger(GrafoMultilistaLigadaAdyacenciaDinamicoCosto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    public double[] dijkstra(int v) {
        return dijkstra(this.matricostos, proximoID, v);
    }

    private static double[] dijkstra(MatrizEnListaLigadaForma2 matricostos, int cantidadVertices, int v) {
        double[] costoMinimo = new double[cantidadVertices];
        boolean[] s = new boolean[cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            double posibleCosto = matricostos.getCelda(v, i + 1);
            costoMinimo[i] = (posibleCosto == 0) ? Double.MAX_VALUE : posibleCosto;
            s[i] = false;
        }

        s[v - 1] = true;
        int i = 1;
        while (i < cantidadVertices) {
            int w = escogerVerticeWMinimoSinEvaluar(v, s, costoMinimo);
            s[w] = true;
            i++;
            for (int j = 0; j < s.length; j++) {
                if (s[j] == false) {
                    costoMinimo[j] = menor(j, w, costoMinimo, matricostos);
                }
            }
        }

        return costoMinimo;
    }

    private static double menor(int j, int w, double[] costoMinimo, MatrizEnListaLigadaForma2 matricostos) {
        double cmJ = costoMinimo[j];
        double cmW = costoMinimo[w];
        double posibleCosto = matricostos.getCelda(w + 1, j + 1);
        double cmWJ = (posibleCosto == 0) ? Double.MAX_VALUE : posibleCosto;
        double costo2 = cmW + cmWJ;
        return (cmJ < costo2) ? cmJ : costo2;
    }

    private static int escogerVerticeWMinimoSinEvaluar(int v, boolean[] s, double[] costoMinimo) {
        int w = v;
        double menor = Double.MAX_VALUE;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == false && (i + 1) == v) {
                if (costoMinimo[i] < menor) {
                    menor = costoMinimo[i];
                    w = i;
                }
            }
        }
        return w;
    }

    private static class NodoEspeciales implements Comparable {

        private NodoMultilista nm;
        Sitio sitio;

        public NodoEspeciales(Sitio sitio) {
            this.sitio = sitio;
        }

        @Override
        public int compareTo(Object obj) {
            Sitio sitio2 = (((NodoEspeciales) obj)).sitio;
            int id = this.sitio.getId();
            int id2 = sitio2.getId();
            return id - id2;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.sitio.toString() + " ady ");
            NodoMultilista r = nm;
            int id = this.sitio.getId();
            while (r != null) {
                builder.append("> " + r.toString());
                if (r.getVi() == id) {
                    r = r.getLigaVi();
                } else {
                    r = r.getLigaVj();
                }
            }
            return builder.toString();
        }

    }

}
