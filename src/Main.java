
public class Main {

	public static void main(String[] args) {
		
		MonticuloFib fibonacci = new MonticuloFib();
		Nodo nueve = new Nodo(9);
		Nodo diez = new Nodo(10);
		
		fibonacci.insertar(new Nodo(5));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		fibonacci.insertar(new Nodo(2));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		fibonacci.insertar(new Nodo(3));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		fibonacci.insertar(new Nodo(4));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		fibonacci.insertar(new Nodo(6));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.eliminarMinimo();
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.insertar(new Nodo(1));
		fibonacci.insertar(new Nodo(8));
		fibonacci.insertar(nueve);
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.eliminarMinimo();
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.insertar(diez);
		fibonacci.insertar(new Nodo(12));
		fibonacci.insertar(new Nodo(30));
		fibonacci.insertar(new Nodo(7));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.eliminarMinimo();
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.insertar(new Nodo(14));
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.eliminarMinimo();
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.eliminarMinimo();
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.decrementarElemento(nueve, 2);
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci.decrementarElemento(diez, 4);
		fibonacci.display(fibonacci.minimo());
		System.out.println("");
		System.out.println("");
		
		MonticuloFib fibonacci1 = new MonticuloFib();
		MonticuloFib fibonacci2 = new MonticuloFib();
		
		fibonacci1.insertar(new Nodo(1));
		fibonacci1.insertar(new Nodo(2));
		fibonacci1.insertar(new Nodo(13));
		fibonacci1.insertar(new Nodo(15));
		fibonacci1.insertar(new Nodo(7));
		fibonacci1.insertar(new Nodo(5));
		fibonacci1.eliminarMinimo();
		
		fibonacci1.display(fibonacci1.minimo());
		System.out.println("");
		System.out.println("");
		
		fibonacci2.insertar(new Nodo(10));
		fibonacci2.insertar(new Nodo(11));
		fibonacci2.insertar(new Nodo(3));
		fibonacci2.insertar(new Nodo(8));
		fibonacci2.insertar(new Nodo(16));
		fibonacci2.eliminarMinimo();
		
		fibonacci2.display(fibonacci2.minimo());
		System.out.println("");
		System.out.println("");
		
		MonticuloFib union = new MonticuloFib(fibonacci1, fibonacci2);
		union.display(union.minimo());
		System.out.println("");
		System.out.println("");
	
	}

}
