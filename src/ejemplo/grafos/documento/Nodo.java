/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.grafos.documento;

import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Nodo implements Comparable<Nodo> {

    private Nodo liga;
    private String dato;
    private final char tipo;
    static final char DOCUMENTO = 'D';
    static final char PALABRA = 'P';

    public Nodo(char tipo) {
        this.tipo = tipo;
    }

    public Nodo(String dato, char tipo) {
        this.dato = dato;
        this.tipo = tipo;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public char getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Nodo o) {
        if (this.tipo != o.getTipo()) {
            return -1;
        } else {
            return this.dato.compareToIgnoreCase(o.getDato());
        }

    }

    @Override
    public boolean equals(Object obj) {
        Nodo o = (Nodo) obj;
        if (this.tipo != o.getTipo()) {
            return false;
        } else {
            return this.dato.equalsIgnoreCase(o.getDato());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.dato);
        hash = 79 * hash + this.tipo;
        return hash;
    }

}
