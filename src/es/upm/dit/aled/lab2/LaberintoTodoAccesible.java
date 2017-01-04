package es.upm.dit.aled.lab2;


/**
 * Clase para mejorar la generacion de laberintos que se resuelven en Laberinto
 * 
 * @author Miguel A. de Miguel
 *
 * es.upm.dit.aled.lab2.Laberinto
 */
public class LaberintoTodoAccesible extends Laberinto {
    /**
     * Atributo utilizado para comprobar la accesibilidad del laberinto.
     */
	int contador = 0;
	
    /**
     * Constructor para crear un nuevo laberinto. Este constructor inicializa el laberinto y lo genera
     * 
     * @param n numero de filas y columnas del laberinto cuadrado
     */
	public LaberintoTodoAccesible(int n) {
		super(n);
	}
	/**
	 * Método que comprueba la accesibilidad de todas las casillas del laberinto.
	 * 
	 * @return accesible true si el laberinto es totalmente accesible y false si no lo es.
	 */
    public boolean compruebaAccesibilidad() {
    	for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                visitado[i][j] = false;
            }
    	}
    	accesibilidad(1,1);
    	boolean accesible = true;
    	double numeroCasillas = Math.pow(visitado.length-2, 2);
    	if (contador == numeroCasillas) {
    		contador = 0;
    	}
    	else {
    		accesible = false;
    		contador = 0;
    	}
	
        return accesible;
    }
    /**
     * Método privado llamado desde compruebaAccesibilidad que utiliza el atributo contador para comprobar si el laberinto es completamente
     * accesible.
     * 
     * @param x Abcisa
     * @param y Ordenada
     */
    private void accesibilidad(int x, int y) {
    	
        if (visitado[x][y]) {
    		return;
    	}
    	
    	visitado[x][y] = true;
		contador++;    
        
    	if (!norte[x][y]) {
    		accesibilidad(x, y+1);
    	}
    	if (!este[x][y]) {
    		accesibilidad(x+1, y);
    	}
    	if (!sur[x][y]) {
    		accesibilidad(x, y-1);
    	}
    	if (!oeste[x][y]) {
    		accesibilidad(x-1, y);
    	}
    }
    
    /**
     * Método que aisla una casilla del laberinto, colocando paredes en sus cuatro costados.
     * 
     * @param x Abcisa
     * @param y Ordenada
     */
    protected void aisla(int x, int y) {
    	norte[x][y] = sur[x][y] = este[x][y] = oeste[x][y] = sur[x][y+1] = norte[x][y-1] = oeste[x+1][y] = este[x-1][y] = true;
    }
    /**
     * Método que ejecuta la clase LaberintoTodoAccesible comprobando que el laberinto es completamente accesible. En este caso sí es accesible.
     * 
     * @param numFilasColumnas Dimensiones del laberinto creado.
     * @return true si el laberinto es accesible (además de resolverlo) y false si no lo es.
     */
    public static boolean ejecutaConControlAccesibilidad(int numFilasColumnas) {
        LaberintoTodoAccesible laberinto = new LaberintoTodoAccesible(numFilasColumnas);
        StdDraw.show(0);
        laberinto.dibuja();
        if (!laberinto.compruebaAccesibilidad())
        	return false;
        laberinto.resolver();
        return true;
    }
    /**
     * Método que ejecuta la clase LaberintoTodoAccesible comprobando si el laberinto creado es accesible. En este caso, a diferencia del método
     * anterior, el laberinto tiene al menos una casilla aislada. 
     * 
     * @param numFilasColumnas Dimensiones del laberinto.
     * @return true si el laberinto es accesible (además de resolverlo) y false si el laberinto no es completamente accesible.
     */
    public static boolean ejecutaConControlAccesibilidadYAislado(int numFilasColumnas) {
        LaberintoTodoAccesible laberinto = new LaberintoTodoAccesible(numFilasColumnas);
        laberinto.aisla(numFilasColumnas/2,numFilasColumnas/2);
        StdDraw.show(0);
        laberinto.dibuja();
        if (!laberinto.compruebaAccesibilidad())
        	return false;
        laberinto.resolver();
        return true;
    }
    /**
     * Método main para probar la clase LaberintoTodoAccesible.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        
        if (LaberintoTodoAccesible.ejecutaConControlAccesibilidad(N))
        	System.out.println("Todas las posiciones son accesibles");
        else System.out.println("Hay posiciones no accesibles");
        
        /*
        if (LaberintoTodoAccesible.ejecutaConControlAccesibilidadYAislado(N))
        	System.out.println("Todas las posiciones son accesibles");
        else System.out.println("Hay posiciones no accesibles");
        */
    }
}
