/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.grafos.documento;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class NodoCabeza implements Comparable<NodoCabeza> {

    private final LinkedList<Nombre> lista;
    private Nombre dato;
    private final char tipo;
    /**
     * Se puede hacer un poco más objetual de dos formas 1. Con clases Palabra y
     * Documento que heradan de nodo. 2. Con un enum para la D y la P.
     */
    static final char DOCUMENTO = 'D';
    static final char PALABRA = 'P';

    /**
     * 
     * @param tipo 
     */
    public NodoCabeza(char tipo) {
        this.tipo = tipo;
        lista = new LinkedList<>();
    }

    public NodoCabeza(Nombre dato, char tipo) {
        this.dato = dato;
        this.tipo = tipo;
        lista = new LinkedList<>();
    }

    public Nombre getDato() {
        return dato;
    }

    public char getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(NodoCabeza o) {
        if (this.tipo != o.getTipo()) {
            return -1;
        } else {
            return this.dato.compareTo(o.getDato());
        }

    }

    @Override
    public boolean equals(Object obj) {
        NodoCabeza o = (NodoCabeza) obj;
        if (this.tipo != o.getTipo()) {
            return false;
        } else {
            return this.dato.equals(o.getDato());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.dato);
        hash = 79 * hash + this.tipo;
        return hash;
    }

    @Override
    public String toString() {
        return dato.getNombre();
    }

    public LinkedList<Nombre> getLista() {
        return lista;
    }
    
    

}
