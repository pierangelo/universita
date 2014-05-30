package lista.sideEffect;
class Nodo<T> {
	public T info;
	public Nodo<T> next;
}

public class Lista<T> implements Cloneable
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

	public void cons(T o) { 
		Nodo<T> aux = new Nodo<T>(); 
		aux.info=o;
		aux.next=nodoinit;		
		nodoinit=aux;
	} 

	public T car() { 
		if (estVuota()) 
			throw new RuntimeException("Lista: car applicato a una lista vuota"); 
		else return nodoinit.info; 
	} 

	public void cdr() { 
		if (estVuota()) 
			throw new RuntimeException("Lista: car applicato a una lista vuota"); 
		else 
			nodoinit=nodoinit.next;
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

		String valoreRitorno="";
		Nodo<T> iterator=nodoinit;
		while (iterator!=null)
		{
			valoreRitorno+=" "+iterator.info;
			iterator=iterator.next;
		}
		return(valoreRitorno);

	}

	public int hashCode()
	{
		if (nodoinit!=null || nodoinit.info!=null)
			return(nodoinit.info.hashCode());
		else
			return(0);
	}
	
	public Object clone()
	{	
		if (estVuota())
			return(new Lista<T>());
		else
		{

			Nodo<T> iterator=nodoinit;
			Nodo<T> nodoClone= new Nodo<T>();
			Lista<T> listaClonata=new Lista<T>(nodoClone);
			while (iterator!=null)
			{
				nodoClone.info=iterator.info;
				iterator=iterator.next;
				if (iterator!=null)
				{
					nodoClone.next=new Nodo<T>();
					nodoClone=nodoClone.next;
				}
				else
					nodoClone.next=null;
			}
			return(listaClonata);
		}
	}
}
