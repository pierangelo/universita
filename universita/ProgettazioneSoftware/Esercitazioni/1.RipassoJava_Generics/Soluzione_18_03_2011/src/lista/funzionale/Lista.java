package lista.funzionale;
class Nodo<T> {
	public T info;
	public Nodo<T> next;
}

public class Lista<T> 
{

	// rappresentazione degli oggetti

	private Nodo<T> nodoinit;

	// realizzazione delle funzioni del tipo astratto

	public Lista() { //realizza ListaVuota
		nodoinit = null; 
	} 

	private Lista(Nodo<T> n) {
		nodoinit = n; 
	} 

	public boolean estVuota() {
		return nodoinit==null; 
	} 

	public Lista<T> cons(T o) { 
		Nodo<T> aux = new Nodo<T>();
		aux.info=o;
		aux.next=nodoinit;
		return new Lista<T>(aux); 
	} 

	public T car() { 
		if (estVuota()) 
			throw new RuntimeException("Lista: car applicato a una lista vuota"); 
		else return nodoinit.info; 
	} 

	public Lista<T> cdr() { 
		if (estVuota()) 
			throw new RuntimeException("Lista: car applicato a una lista vuota"); 
		else return new Lista<T>(nodoinit.next); 
	}

	// uguaglianza

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o == null || !getClass().equals(o.getClass())) 
			return false;
		Lista<T> l = (Lista<T>)o;
		Nodo n1 = nodoinit;
		Nodo n2 = l.nodoinit;
		while (n1 != null || n2 != null) {
			if (!n1.info.equals(n2.info)) return false;
			n1 = n1.next;
			n2 = n2.next;
		}
		if (n1 != null || n2 != null) return false;
		else return true;
	}

	// toString

	public String toString() { 
		if (estVuota()) return "";
		else return car()+" "+cdr().toString();
	}

	public int hashCode()
	{
		if (nodoinit!=null || nodoinit.info!=null)
			return(nodoinit.info.hashCode());
		else
			return(0);
	}
}
