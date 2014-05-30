package pila.funzionale;

class Nodo<T> {
	public T info;
	public Nodo<T> next;
}

public class PilaF<T>
{
	//rappresentazione degli oggetti

	private Nodo<T> nodoinit;

	//realizzazione delle funzioni del tipo astratto

	public PilaF() { // realizza pilaVuota
		nodoinit = null; 
	}

	public boolean estVuota() { 
		return nodoinit==null; 
	}

	public PilaF<T> push (T o) {
		Nodo<T> aux = new Nodo<T>();
		aux.info = o;
		aux.next = nodoinit;
		return new PilaF<T>(aux); // NB: uso costruttore privato
	}

	public PilaF<T> pop() {
		if (estVuota()) 
			throw new RuntimeException("PilaF: pop applicato ad una pila vuota");
		return new PilaF<T>(nodoinit.next); // NB: uso costruttore privato
	}

	public T top() {
		if (estVuota()) 
			throw new RuntimeException("PilaF: top applicato ad una pila vuota");
		else return nodoinit.info; 
	}

	// uguaglianza

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o == null || !getClass().equals(o.getClass())) 
			return false;
		PilaF<T> p = (PilaF<T>)o;
		Nodo<T> n1 = nodoinit;
		Nodo<T> n2 = p.nodoinit;
		while (n1 != null || n2 != null) {
			if (!n1.info.equals(n2.info)) return false;
			n1 = n1.next;
			n2 = n2.next;
		}
		if (n1 != null || n2 != null) return false;
		else return true;
	}

	public int hashCode()
	{
		if (nodoinit!=null || nodoinit.info!=null)
			return(nodoinit.info.hashCode());
		else
			return(0);
	}  

	// metodi ausiliari

	private PilaF(Nodo<T> n) { //costruttore privato
		nodoinit = n;
	}
}
