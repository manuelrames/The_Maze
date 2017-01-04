package es.upm.dit.aled.lab2;

import es.upm.dit.aled.lab2.LaberintoTodoAccesible;
import es.upm.dit.aled.lab2.StdDraw;

public class PruebasLab2 extends LaberintoTodoAccesible {
	
    public PruebasLab2(int n) {
		super(n);
	}

    protected void aislaOesteColumna(int x) {
    	for (int y=1; y <= N; y++) {
        	oeste[x][y]=este[x-1][y]=true;
    	}
    }
    
    public static boolean ejecutaConControlAccesibilidadYAislaColumna(int numFilasColumnas, int colAislada) {
    	PruebasLab2 laberinto = new PruebasLab2(numFilasColumnas);
        laberinto.aislaOesteColumna(colAislada);
        StdDraw.show(0);
        laberinto.dibuja();
        if (!laberinto.compruebaAccesibilidad())
        	return false;
        laberinto.resolver();
        return true;
    }
    
	public static void main(String[] args) {
	    Throwable tt=null;
	    int errores=0;
	    try {
		    int N = 20;
		    if (!PruebasLab2.ejecutaConControlAccesibilidad(N)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidad ha devuelto false y debia devolver true");
		    	errores++;
		    }
		    if (PruebasLab2.ejecutaConControlAccesibilidadYAislado(N)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidadYAislado ha devuelto true y debia devolver false");
		    	errores++;
		    }
		    if (PruebasLab2.ejecutaConControlAccesibilidadYAislaColumna(N,2)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidadYAislaColumna(N,2) ha devuelto true y debia devolver false");
		    	errores++;
		    }
		    if (PruebasLab2.ejecutaConControlAccesibilidadYAislaColumna(N,N)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidadYAislaColumna(N,N) ha devuelto true y debia devolver false");
		    	errores++;
		    }
		    if (PruebasLab2.ejecutaConControlAccesibilidadYAislaColumna(N,N/2)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidadYAislaColumna(N,N/2) ha devuelto true y debia devolver false");
		    	errores++;
		    }
		    if (PruebasLab2.ejecutaConControlAccesibilidadYAislaColumna(N,N/2+1)) {
		    	System.out.println("La llamada ejecutaConControlAccesibilidadYAislaColumna(N,N/2+1) ha devuelto true y debia devolver false");
		    	errores++;
		    }
		} catch (Throwable t) {
	        errores++;
	        tt=t;
	    }
        System.out.println("Errores de ejecucion: "+errores);
        if (tt != null && tt.getMessage() != null) {
            System.out.print("Excepcion levantada:");
            System.out.println(tt.getMessage());
        } 
        if (tt != null)
        	tt.printStackTrace();
    }
}