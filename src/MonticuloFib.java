
public class MonticuloFib {
	
	private Nodo nodoMin;										// Nodo mínimo del montículo
	private int elementos;

	public MonticuloFib() {   									// Iniciamos el montículo con un nodo que va a ser el mínimo ya que es el único
		this.elementos = 0;	
	}
	
	public MonticuloFib(MonticuloFib f1, MonticuloFib f2) {
		union(f1,f2);
	}
	
	public void insertar(Nodo n) {								// Insertamos el nuevo nodo a la izquierda del mínimo y si el valor es menor que el mínimo, lo indicamos como nuevo mínimo.
		
		if (elementos > 1) {
			nodoMin.getIzquierda().setDerecha(n);
			n.setDerecha(nodoMin);
			n.setIzquierda(nodoMin.getIzquierda());
			nodoMin.setIzquierda(n);
		}
		else if (elementos == 1) {
			n.setDerecha(nodoMin);
			n.setIzquierda(nodoMin);
			nodoMin.setIzquierda(n);
			nodoMin.setDerecha(n);
		}
		else {
			n.setDerecha(n);
			n.setIzquierda(n);
		}
		
		if (this.elementos == 0 || n.getValor() < nodoMin.getValor()) {
			nodoMin = n;
		}
		this.elementos++;
	}
	
	
	
	public void union(MonticuloFib f1, MonticuloFib f2) {		// Con esta unión, obtenemos un solo montículo formado por los elementos de ambos concatenados
		this.nodoMin = f1.nodoMin;								// Ponemos como mínimo el nodo mínimo del primer montículo	
		Nodo tmpD = nodoMin.getDerecha();						// Guardamos el nodo derecho del nodo mínimo para no perderlo
		Nodo tmpI = f2.nodoMin.getIzquierda();                  // Guardamos el nodo izquierdo del nodo mínimo del segundo montículo para no perderlo
		nodoMin.setDerecha(f2.nodoMin);							// Cambiamos el nodo derecho del mínimo por el mínimo del segundo montículo para concatenar sus elementos
		nodoMin.getDerecha().setIzquierda(nodoMin);				// Cambiamos el nodo derecho del nodo mínimo del segundo montículo por el nodo mínimo del primero asi esta concatenado uno con otro 
		tmpD.setIzquierda(tmpI);								// Cambiamos el nodo izquierdo del que estaba anteriormente en el primer montículo por el elemento a la izquierda del mínimo del segundo montículo
		tmpI.setDerecha(tmpD); 									// Cambiamos el nodo derecho del nodo izquierdo del nodo mínimo del segundo montículo por el derecho del nodo mínimo
		
		if (this.nodoMin.getValor() > this.nodoMin.getDerecha().getValor()) { // Si el mínimo del segundo montículo es menor que el del primero, lo intercambiamos
			this.nodoMin = this.nodoMin.getDerecha();
		}
		
		this.elementos = f1.elementos + f2.elementos;
	}
	
	public Nodo minimo() {
		return nodoMin;
	}
	
	public void eliminarMinimo() {
		if (nodoMin.getHijo() != null) {
			Nodo tmp = nodoMin.getHijo().getIzquierda();			// Guardamos el nodo izquierdo del hijo para no reemplazarlo
			if (nodoMin.getDerecha() != nodoMin) {
				tmp.setDerecha(nodoMin.getDerecha());				// Cambiamos el nodo derecho del hermano derecho del hijo del mínimo al nodo derecho del padre
			}
			Nodo d = tmp;
			nodoMin.getHijo().setIzquierda(nodoMin.getIzquierda()); // Cambiamos el nodo izquierdo del hijo al nodo izquierdo del padre
			
			nodoMin.getHijo().borrarPadre();
			while (tmp != nodoMin.getHijo()) {						// Recorremos todos los hijos para disminuir su grado en -1
				tmp.borrarPadre();									// Borramos el padre del nodo
				if (tmp.getIzquierda() == nodoMin.getHijo()) {
					break;
				}
				tmp = tmp.getIzquierda();
			}							
																	// Borramos el nodo mínimo sustituyendo el nodo izquierdo y derecho de los nodos que tenia a su lado.
			nodoMin.getIzquierda().setDerecha(nodoMin.getHijo());
			nodoMin.getDerecha().setIzquierda(d);
		}
		else {														// Si no tiene hijos, solo eliminamos el elemento de la lista y consolidamos si hay mas elementos en la lista
			if (nodoMin.getDerecha() == nodoMin) {						// Si no hay mas elementos que el mínimo
				nodoMin = null;
			}
			else {
				nodoMin.getDerecha().setIzquierda(nodoMin.getIzquierda());
				nodoMin.getIzquierda().setDerecha(nodoMin.getDerecha());
			}
		}
		
		if (nodoMin != null) {
			if (nodoMin != nodoMin.getDerecha()) {
				nodoMin = nodoMin.getDerecha();							// El mínimo pasa a ser el hermano derecho del nodo mínimo eliminado
			}
			else {
				nodoMin = nodoMin.getHijo();							// Si solo estaba ese elemento en la raiz principal entonces ponemos como mínimo a su hijo
			}
			consolidar();											// Función auxiliar para consolidar el montículo
		}
		this.elementos--;											// Decrementamos el número de elementos en uno
	}

	private void consolidar() {
		Nodo grados[] = new Nodo[(int) ((Math.log(elementos)+1)*2)];					// Inicializamos un array para añadir un nodo a cada grado
		Nodo nodoFinal = this.nodoMin.getIzquierda();				// Guardamos el último nodo que debe ser revisado
		Nodo nodoIt = this.nodoMin;									// Guardamos el primer nodo sobre el que vamos a ir iterando
		Nodo next = null;
		boolean dentro = true, c = false, lastVisited = false;
		int i = 0;
		while (i == 0 || c || !lastVisited) {						// Iteramos sobre toda la lista encadenada de nodos hacia la derecha del mínimo
			int gradoTmp = nodoIt.getGrado();
			
			if (nodoIt == nodoFinal) {
				lastVisited = true;
			}
			
			if (grados[gradoTmp] != null) {
				int valorArr = grados[gradoTmp].getValor();			// Tomamos el valor del nodo con el mismo grado
				int valorIt = nodoIt.getValor();					// Tomamos el valor sobre el nodo que estamos iterando
				Nodo tmp;
				
				if (valorArr >= valorIt) {							// Ponemos como hijo el que tenga el valor mayor de los dos
					tmp = grados[gradoTmp];
					tmp.getIzquierda().setDerecha(tmp.getDerecha());
					tmp.getDerecha().setIzquierda(tmp.getIzquierda());
					if (tmp == nodoFinal) {
						nodoFinal = nodoIt;
					}
				}
				else {												// Si el que estaba guardado en el array es el menor elemento los intercambiamos
					tmp = nodoIt;
					nodoIt = grados[gradoTmp];
					tmp.getIzquierda().setDerecha(tmp.getDerecha());
					tmp.getDerecha().setIzquierda(tmp.getIzquierda());
					
					if (next == null) {
						next = tmp.getDerecha();	
					}
					
					if (tmp == nodoFinal) {
						nodoFinal = nodoIt;
					}
				}
		
				if (nodoIt.getHijo() != null) {						// Si tiene hijos el nodo mayor de los dos, ponemos tmp como hermano de su hijo
					tmp.setIzquierda(nodoIt.getHijo());
					tmp.setDerecha(nodoIt.getHijo().getDerecha());
					nodoIt.getHijo().getDerecha().setIzquierda(tmp);
					nodoIt.getHijo().setDerecha(tmp);
				}
				else {												// Si no tiene hijos, solamente lo colocamos como hijo
					nodoIt.setHijo(tmp);
					tmp.setDerecha(tmp);							// Ponemos el mayor como hijo del menor del grado explorado
					tmp.setIzquierda(tmp);
				}
				
				
				
				
				nodoIt.setGrado(gradoTmp+1);
				tmp.setPadre(nodoIt);								// Ponemos el menor como el padre del mayor de los nodos del mismo grado comparados
				tmp.setMarcado(false);								// Al colgarse de otro padre, si estaba marcado deja de estarlo
				
				grados[gradoTmp] = null;							// Actualizamos el array de los grados de los elementos
				if (grados[gradoTmp+1] == null) {
					grados[gradoTmp+1] = nodoIt;
					if ((nodoIt != nodoFinal || dentro) && next == null) {
						nodoIt = nodoIt.getDerecha();
						dentro = false;
					}
					else if (next != null) {
						nodoIt = next;
						next = null;
						dentro = false;
					}
					c = false;
				}
				else {
					c = true;
				}
				i++;
				
			}
			else {
				grados[gradoTmp] = nodoIt;							// Si no hay ningun nodo con el mismo grado entonces lo incluimos en el array para comparar
				nodoIt = nodoIt.getDerecha();						//si posteriormente hay alguno del mismo grado
				
				i++;
			}														
			
			
		}
		
		this.nodoMin = null;
		for (Nodo n : grados) {										// Recorremos la lista de los diferentes nodos con diferentes grados para elegir el mínimo
			if (nodoMin == null && n != null) {
				nodoMin = n;
			}
			else if (n != null) {
				
				if (n.getValor() < nodoMin.getValor()) {			
					nodoMin = n;
				}
			}
		}
	}
	
	

	public void decrementarElemento(Nodo nodo, int nuevo) {
		if (nuevo >= nodo.getValor()) {								// Si el nuevo valor indicado no es menor que el actual, volvemos sin cambiar nada
			return;
		}
		else {
			nodo.setValor(nuevo);									// Si el nuevo valor es menor que el de su padre, lo cortamos
			Nodo padre = nodo.getPadre();							// Realizamos el corte en cascada hasta que no haya nodos marcados
			if (padre != null && padre.getValor() > nuevo) {
				cortar(padre, nodo);
				cortarCascada(padre);
			}
		}
	}


	private void cortar(Nodo padre, Nodo nodo) {
		nodo.borrarPadre();											// Borramos su padre
		if (nodo.getDerecha() != nodo) {
			padre.setHijo(nodo.getDerecha());
			nodo.getDerecha().setIzquierda(nodo.getIzquierda());	// Lo borramos de los hijos del padre
			nodo.getIzquierda().setDerecha(nodo.getDerecha());
			
			Nodo tmp = padre.getHijo().getDerecha();
			int max = padre.getHijo().getGrado();
			while (padre.getHijo() != tmp) {												// Disminuimos el grado del padre
				if (tmp.getGrado() > max) {
					max = tmp.getGrado();
				}
				tmp = tmp.getDerecha();
			}
			padre.setGrado(max + 1);
		}
		else {
			padre.setHijo(null);									// Si solo tiene un hijo, lo borramos del padre y disminuimos su grado
			padre.setGrado(0);
		}
		if (nodo.getHijo() != null) {
			nodo.setGrado(nodo.getHijo().getGrado()+1);
		}
		else {
			nodo.setGrado(0);
		}
		
		insertar(nodo);
		nodo.setMarcado(false);
	}
	
	private void cortarCascada(Nodo padre) {
		Nodo p = padre.getPadre();
		if (p == null) {											// Si esta en la raiz principal, no se hace el corte en cascada
			return;
		}
		else {														
			if (!padre.getMarcado()) {								// Si no esta marcado, lo marcamos
				padre.setMarcado(true);								
			}
			else {													// Si esta marcado lo cortamos y volvemos a hacer el corte en cascada hasta que haya un nodo no marcado o lleguemos a la raiz principal
				cortar(p,padre);
				cortarCascada(p);
			}
		}
	}
	
	public void display(Nodo c) {
	    System.out.print("(");
	    if (c == null) {
	      System.out.print(")");
	      return;
	    } else {
	    	Nodo tmp = c;
	      do {
	    	if (tmp.getMarcado()) {
	    		System.out.print("*"+tmp.getValor()+"*");
	    	}
	    	else {
	    		System.out.print(tmp.getValor());
	    	}
	        Nodo k = tmp.getHijo();
	        display(k);
	        System.out.print("->");
	        tmp = tmp.getDerecha();
	      } while (tmp != c);
	      System.out.print(")");
	    }
	  }
	
	
}
