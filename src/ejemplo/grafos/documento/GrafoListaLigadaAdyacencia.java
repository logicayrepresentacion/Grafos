/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.grafos.documento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class GrafoListaLigadaAdyacencia {

    private final ArrayList<NodoCabeza> nodosCabeza;

    public GrafoListaLigadaAdyacencia() {
        nodosCabeza = new ArrayList<>();
    }

    /**
     * Esto ingresa nodos tipo palabra o documento al grafo El nodo a siempre va
     * a ser el documento, el nodo b siempre es una palabra
     *
     * @param documento
     * @param palabra
     */
    public void insertar(String documento, String palabra) {

        /**
         * Validar si el nodo sea palabra o documento ya existe en el arraylist
         * Si ya existe ese nodo es la cabeza de la lista ligada de adyacencia.
         */
        NodoCabeza documentoCabezaEnArreglo = new NodoCabeza(new Nombre(documento), NodoCabeza.DOCUMENTO);
        NodoCabeza palabraCabezaEnArreglo = new NodoCabeza(new Nombre(palabra), NodoCabeza.PALABRA);

        int di = nodosCabeza.indexOf(documentoCabezaEnArreglo);
        int pi = nodosCabeza.indexOf(palabraCabezaEnArreglo);

        if (di == -1) {
            nodosCabeza.add(documentoCabezaEnArreglo);
        } else {
            documentoCabezaEnArreglo = nodosCabeza.get(di);
        }

        if (pi == -1) {
            nodosCabeza.add(palabraCabezaEnArreglo);
        } else {
            palabraCabezaEnArreglo = nodosCabeza.get(pi);
        }

        /**
         * En esta parte documentoCabezaEnArreglo y palabraCabezaEnArreglo
         * representan las cabezas de las listas ligadas sea que existan o no
         * Este fragmento de codigo adiciona en las dos listas ligadas Una por
         * la lista para la palabra (en que documentos esta la palabra) Una por
         * la lista de documentos (que palabras estan en el documento)
         */

        List lista = palabraCabezaEnArreglo.getLista();
        if (! lista.contains(documento)){
            lista.add(documento);
        }

        lista = documentoCabezaEnArreglo.getLista();
        if (! lista.contains(palabra)){
            lista.add(palabra);
        }
       
    }

    /**
     * Conocer cual era la palabra que más se utilizaba en el grafo Esto me
     * daría idea de que hablaban los documentos
     *
     * @return
     */
    public NodoCabeza buscarGradoPalabras() {

        /**
         * Recorro los nodos
         */
        int contador;
        int maximoContador = -1;
        List lista;
        NodoCabeza nodoMaximo = null;
        for (NodoCabeza cabezaAuxiliar : nodosCabeza) {
            if (cabezaAuxiliar != null && cabezaAuxiliar.getTipo() == NodoCabeza.PALABRA) {
                lista = cabezaAuxiliar.getLista();
                contador = lista.size();
                if (contador > maximoContador) {
                    maximoContador = contador;
                    nodoMaximo = cabezaAuxiliar;
                }
            }
        }

        System.out.println(" Nodo palabra con más relaciones \"" + nodoMaximo + "\" con un grado de " + maximoContador);
        return nodoMaximo;

    }

}
