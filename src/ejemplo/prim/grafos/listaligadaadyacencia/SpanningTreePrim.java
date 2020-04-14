/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.prim.grafos.listaligadaadyacencia;

import grafos.listaligadaadyacencia.enteros.GrafoListaLigadaAdyacencia;
import java.util.Scanner;
import matriz.arreglobidimensional.MatrizEnArregloBidimensional;

/**
 *
 * @author 57300
 */
public class SpanningTreePrim {

    public static void main(String[] args) throws Exception {
        /**
         * Para leer el teclado
         */
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();
        int numeroVertices = Integer.parseInt(linea.trim());

        /**
         * Grafo representado como una matriz o arreglo de arreglos
         */
        GrafoListaLigadaAdyacencia grafoListaLigadaAdyacencia = new GrafoListaLigadaAdyacencia(numeroVertices);

        /**
         * Cargar la matriz de los datos ingresados por pantalla a la matriz en
         * memoria
         */
        for (int i = 0; i < numeroVertices; i++) {
            linea = sc.nextLine();
            for (int j = 0; j < numeroVertices; j++) {
                if (linea.charAt(j) == '1') {
                    grafoListaLigadaAdyacencia.crearAdyacencia(i, j);
                }
            }
        }

        /**
         * Cargar la matriz de los costos ingresados por pantalla a la matriz en
         * memoria
         */
        matriz.arreglobidimensional.MatrizEnArregloBidimensional matrizEnArregloBidimensional = new MatrizEnArregloBidimensional(numeroVertices, numeroVertices);
        for (int i = 0; i < numeroVertices; i++) {
            linea = sc.nextLine();
            String costos[] = linea.split(",");
            for (int j = 0; j < numeroVertices; j++) {
                matrizEnArregloBidimensional.setCelda(i, j, Double.parseDouble(costos[j]));
            }
        }

        grafoListaLigadaAdyacencia.setMatrizCostos(matrizEnArregloBidimensional);

        System.out.println(" ST " + grafoListaLigadaAdyacencia.prim());

    }

}
