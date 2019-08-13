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
package grafos.matrizadyacencia.tripleta.ejemplos.alcantarilla;

import grafos.matrizadyacencia.tripleta.GrafoMatrizAdyacenciaEnMatrizTripleta;
import java.util.Scanner;
import matriz.util.Tripleta;

/**
 *
 * @author alejandroescobar
 */
public class Alcantarilla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * 
         */
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();
        int nv = Integer.parseInt(linea.trim());
        GrafoMatrizAdyacenciaEnMatrizTripleta matrizEnTripleta = new GrafoMatrizAdyacenciaEnMatrizTripleta(nv, nv);
        int cv = 0;
        for( int i = 0; i < nv; i++){
            linea = sc.nextLine();
            for( int j = 0; j< nv; j++){
                if( linea.charAt(j) == '1'){
                    cv++;
                    Tripleta t = new Tripleta(i+1, j+1, 1);
                    matrizEnTripleta.setTripleta(cv, t);
                }
            }
        }
        
        Tripleta conf = new Tripleta(nv, nv, cv);
        matrizEnTripleta.setTripleta(0, conf);
        
        System.out.println( matrizEnTripleta.grado(1) );
        System.out.println( matrizEnTripleta.grado(2) );
        System.out.println( matrizEnTripleta.grado(3) );
        System.out.println( matrizEnTripleta.grado(4) );
        System.out.println( matrizEnTripleta.grado(5) );
        
    }
    
}
