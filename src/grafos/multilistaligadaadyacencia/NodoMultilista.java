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
package grafos.multilistaligadaadyacencia;

/**
 *
 * @author Alejandro
 */
public class NodoMultilista {

    private int vi;
    private int vj;
    private NodoMultilista ligaVi;
    private NodoMultilista ligaVj;
    private int sw;

    public NodoMultilista(int i, int j) {
        vi = i;
        vj = j;
    }

    public int getVi() {
        return vi;
    }

    public void setVi(int vi) {
        this.vi = vi;
    }

    public int getVj() {
        return vj;
    }

    public void setVj(int vj) {
        this.vj = vj;
    }

    public NodoMultilista getLigaVi() {
        return ligaVi;
    }

    public void setLigaVi(NodoMultilista ligaVi) {
        this.ligaVi = ligaVi;
    }

    public NodoMultilista getLigaVj() {
        return ligaVj;
    }

    public void setLigaVj(NodoMultilista ligaVj) {
        this.ligaVj = ligaVj;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    @Override
    public String toString() {
        return "(" + vi + "," + vj + ")";
    }

}
