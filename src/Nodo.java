
public class Nodo {
	
	private Nodo d;											// Nodo derecho
	private Nodo i;											// Nodo izquierdo
	private Nodo hijo;										// Hijo que pertenece a la lista doblemente encadenada de hijos
	private int valor;										// Valor del nodo
	private boolean marcado;								// Booleano para saber si esta marcado o no
	private Nodo padre;										// Nodo para indicar el padre del nodo, si tiene.
	private int grado;										// Entero para indicar el grado del nodo
	
	public Nodo (int valor) {								
		this.valor = valor;									// Inicializamos el valor
		this.marcado = false;								// El nodo nunca puede añadirse marcado
		this.padre = null;									// Inicializamos el padre a null
		this.grado = 0;
	}
	
	public Nodo getIzquierda() {							// Devolver el nodo izquierdo
		return i;
	}
	
	public Nodo getDerecha() {								// Devolver el nodo derecho
		return d;
	}
	
	public int getValor() {									// Devolver el valor del nodo
		return valor;
	}

	public void setDerecha(Nodo nodoMin) {
		this.d = nodoMin;
	}
	
	public void setIzquierda(Nodo nodoMin) {
		this.i = nodoMin;
	}

	public Nodo getHijo() {
		return this.hijo;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public void borrarPadre() {
		this.padre = null;
	}
	
	public void setPadre(Nodo p) {
		this.padre = p;
	}
	
	public Nodo getPadre() {
		return this.padre;
	}

	public int getGrado() {
		return this.grado;
	}

	public void setHijo(Nodo h) {
		this.hijo = h;
	}

	public void setMarcado(boolean b) {
		this.marcado = b;
	}

	public void setValor(int nuevo) {
		this.valor = nuevo;
	}

	public boolean getMarcado() {
		return marcado;
	}

	

}
