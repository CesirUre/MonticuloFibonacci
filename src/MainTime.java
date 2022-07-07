import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MainTime {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		MonticuloFib fibonacci = new MonticuloFib();		// Creamos el monticulo de Fibonacci
		
		PrintWriter writer = new PrintWriter("FibonacciOutput.txt", "UTF-8");
		writer.println("A_size, Insertar, Union, EliminarMinimo, DecrementarElemento");
		for (int i = 100; i <= 200000; i += 400) {
			writer.print(i+", ");
			
			Nodo [] nodos = new Nodo[i];                     // Creamos una lista de nodos de tamaño 1000
			
			for (int j = 0; j < i; j++) {			// Llenamos la lista de nodos con punteros a nodos para poder realizar la operacion decrementar elemento
				nodos[j] = new Nodo(j);
			}
			
			for (int j = 0; j < i; j++) {			// Insertamos i elementos al montículo
				fibonacci.insertar(new Nodo((int) Math.random()));
			}
			long startTime = System.nanoTime();	
			for (int j = 0; j < 1000; j++) {
				fibonacci.insertar(new Nodo((int) Math.random()));
			}
			
			long endTime = System.nanoTime();					// Finaliza el contador al insertar los 1000 elementos
	
			long duration = (endTime - startTime);
			
			writer.print(duration/1000 + ", ");					// Dividimos el tiempo que ha tardado en ejecutar por el número de operaciones
			
			MonticuloFib fibonacci1 = new MonticuloFib();
			MonticuloFib fibonacci2 = new MonticuloFib();
			for (int j = 0; j < nodos.length; j++) {			
				if (j < nodos.length/2) {
					fibonacci1.insertar(nodos[j]);
				}
				else {
					fibonacci2.insertar(nodos[j]);
				}
			}
			MonticuloFib fibonacciUnion;
			startTime = System.nanoTime();
			for (int j = 0; j < 1000; j++) {
				fibonacciUnion = new MonticuloFib(fibonacci1,fibonacci2);  // Creamos un monticulo con la union de otros dos
			}
			
			endTime = System.nanoTime();
			
			duration = (endTime - startTime);
			
			writer.print(duration/1000 + ", ");
			
			MonticuloFib fibonacciEliminarMinimo = new MonticuloFib();
			
			for (int j = 0 ; j < i ; j++) {
				fibonacciEliminarMinimo.insertar(new Nodo((int) Math.random()));
			}
			
			startTime = System.nanoTime();
			
			for (int j = 0; j < 100; j++) {
				fibonacciEliminarMinimo.eliminarMinimo();				  // Repetimos 100 veces la operacion eliminar mínimo y hacemos la media 
			}
			
			endTime = System.nanoTime();
			
			duration = (endTime - startTime);
			
			writer.print(duration/100 + ", ");
			
			
			startTime = System.nanoTime();
			
			for (int j = 0; j < 1000; j++) {							 // Ejecutamos 1000 veces la operación decrementar elemento
				fibonacci.decrementarElemento(fibonacci.minimo(), fibonacci.minimo().getValor()-1);
			}
			
			endTime = System.nanoTime();
			
			duration = (endTime - startTime);
			
			writer.print(duration/1000 + "\n");
			
			System.gc();
		}
		writer.close();
		
	}

}
