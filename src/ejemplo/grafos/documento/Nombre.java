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
public class Nombre implements Comparable<Nombre>{
    private final String nombre;

    public Nombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nombre.equalsIgnoreCase(obj.toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public int compareTo(Nombre o) {
        return this.nombre.compareTo(o.getNombre());
    }
    
    
    
}
