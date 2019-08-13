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
package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class Grafo {

    /**
     *
     * @param costos
     * @return
     */
    public static Costo[][] todosLosCaminosMasCortos(Costo[][] costos) {
        int n = costos.length;
        Costo[][] menoresCostos = new Costo[n][n];
        // Realiza una copia de la matriz de costos;
        for (int i = 0; i < n; i++) {
            System.arraycopy(costos[i], 0, menoresCostos[i], 0, n);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("k= " + (k + 1) + " i= " + (i + 1) + " j= " + (j + 1));
                    Costo aux = Costo.sumar(menoresCostos[i][k], menoresCostos[k][j]);
                    System.out.println("aux= " + aux);
                    switch (aux.compareTo(menoresCostos[i][j])) {
                        case 1:
                            break;
                        case -1:

                        case 0:
                            System.out.println("acá");
                            menoresCostos[i][j] = aux;
                            break;
                    }
                    Costo.mostrar(menoresCostos);
                }
            }
        }
        return menoresCostos;
    }

    /**
     *
     * @param vs
     * @param vl
     * @param costos
     */
    public static void rutaMasCorta(int vs, int vl, Costo[][] costos) {
        int n = costos.length;
        Costo[][] menoresCostos = new Costo[n][n];
        int[][] ruta = new int[n][n];
        // Realiza una copia de la matriz de costos;
        for (int i = 0; i < n; i++) {
            System.arraycopy(costos[i], 0, menoresCostos[i], 0, n);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("k= " + (k + 1) + " i= " + (i + 1) + " j= " + (j + 1));
                    Costo aux = Costo.sumar(menoresCostos[i][k], menoresCostos[k][j]);
                    System.out.println("aux= " + aux);
                    switch (aux.compareTo(menoresCostos[i][j])) {
                        case 1:
                            break;
                        case -1:

                        case 0:
                            System.out.println("acá");
                            ruta[i][j] = k + 1;
                            menoresCostos[i][j] = aux;
                            break;
                    }
                    Costo.mostrar(menoresCostos);
                    mostrar(ruta);
                }
            }
        }
        Costo.mostrar(menoresCostos);
        mostrar(ruta);

    }

    public static Costo[][] crearMatrizDeCostosDesdeArchivo(String fname) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fname));

        // Leo la primera linea del archivo, esta contiene cuantos vertices tiene el grafo.
        String linea = scanner.nextLine();
        int n = Integer.parseInt(linea);

        Costo[][] costos;
        costos = new Costo[n][n];

        for (int i = 0; i < n; i++) {
            linea = scanner.nextLine();
            String[] valores = linea.split("\\;");
            for (int j = 0; j < n; j++) {
                if (valores[j].equals("i")) {
                    costos[i][j] = new Costo();
                } else {
                    int costo = Integer.parseInt(valores[j]);
                    if (costo != 0) {
                        costos[i][j] = new Costo(costo);
                    } else {
                        System.out.println("Posiblemente es un error");
                    }
                }
            }
        }
        return costos;
    }

    public static void mostrar(int[][] matriz) {
        for (int[] matriz1 : matriz) {
            for (int m : matriz1) {
                System.out.print("\t" + m);
            }

            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) throws FileNotFoundException {
        Costo[][] costos = crearMatrizDeCostosDesdeArchivo("costos.csv");
        Costo.mostrar(costos);
        Costo[][] menoresCostos = todosLosCaminosMasCortos(costos);
        System.out.println("La otra matriz");
        Costo.mostrar(menoresCostos);

        //System.out.println("Modificado");

        //rutaMasCorta(1, 6, costos);
    }

}
