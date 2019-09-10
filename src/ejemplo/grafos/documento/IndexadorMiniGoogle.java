/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.grafos.documento;

/**
 *
 * @author Usuario
 */
public class IndexadorMiniGoogle {

    public static void main(String[] args) {
        GrafoListaLigadaAdyacencia glla = new GrafoListaLigadaAdyacencia();

        String[] palabras = Estaticos.documento1.split("\\ ");

        for (String palabra : palabras) {
            glla.insertar("D1", palabra);
        }

        palabras = Estaticos.documento2.split("\\ ");
        for (String palabra : palabras) {
            glla.insertar("D2", palabra);
        }

        System.out.println(" glla + " + glla);
    }
}
