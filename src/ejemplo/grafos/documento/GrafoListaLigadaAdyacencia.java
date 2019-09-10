/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.grafos.documento;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class GrafoListaLigadaAdyacencia {

    private final ArrayList<Nodo> nodos;

    public GrafoListaLigadaAdyacencia() {
        nodos = new ArrayList<>();
    }

    /**
     * Esto ingresa nodos tipo palabra o documento al grafo El nodo a siempre va
     * a ser el documento, el nodo b siempre es una palabra
     *
     * @param documento
     * @param palabra
     */
    public void insertar(String documento, String palabra) {
        Nodo documentoCabezaEnArreglo = new Nodo(documento, Nodo.DOCUMENTO);
        Nodo palabraCabezaEnArreglo = new Nodo(palabra, Nodo.PALABRA);

        int di = nodos.indexOf(documentoCabezaEnArreglo);
        int pi = nodos.indexOf(palabraCabezaEnArreglo);

        if (di == -1) {
            nodos.add(documentoCabezaEnArreglo);
        } else {
            documentoCabezaEnArreglo = nodos.get(di);
        }

        if (pi == -1) {
            nodos.add(palabraCabezaEnArreglo);
        } else {
            palabraCabezaEnArreglo = nodos.get(pi);
        }

        /**
         * En esta parte doc y pal representan las cabezas de las listas ligadas
         * sea que existan o no
         */
        Nodo palabraAdyante = new Nodo(palabra, Nodo.PALABRA);
        Nodo documentoAdyante = new Nodo(documento, Nodo.DOCUMENTO);
        
        Nodo aux = palabraCabezaEnArreglo.getLiga();
        documentoAdyante.setLiga(aux);
        palabraCabezaEnArreglo.setLiga(documentoAdyante);
        
        aux = documentoCabezaEnArreglo.getLiga();
        palabraAdyante.setLiga(aux);
        documentoCabezaEnArreglo.setLiga(palabraAdyante);
    }
    
    

}
