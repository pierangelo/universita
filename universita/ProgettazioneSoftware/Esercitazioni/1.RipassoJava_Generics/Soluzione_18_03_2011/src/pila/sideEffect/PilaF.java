package pila.sideEffect;

class Nodo<T> {
	public T info;
	public Nodo<T> next;
}

public class PilaF<T>
{

	// rappresentazione degli oggetti

	private Nodo<T> nodoinit;

	// realizzazione delle funzioni del tipo astratto

	public PilaF () { // realizza pilaVuota
		nodoinit = null; 
	}

	public boolean estVuota() { 
		return nodoinit==null; 
	}

	public void push (T o) {
		Nodo<T> aux = new Nodo<T>();
		aux.info = o;
		aux.next = nodoinit;
		nodoinit = aux;
	}

	public void pop() {
		if (estVuota()) 
			throw new RuntimeException("Pila: pop applicato ad una pila vuota");     
		else nodoinit = nodoinit.next;
	}     

	public Object top() {
		if (estVuota()) 
			throw new RuntimeException("Pila: top applicato ad una pila vuota");     
		else return nodoinit.info;
	}

	//uguaglianza

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

	public Object clone()
	{	
		if (estVuota())
			return(new PilaF<T>());
		else
		{

			Nodo<T> iterator=nodoinit;
			Nodo<T> nodoClone= new Nodo<T>();
			PilaF<T> pilaClonata=new PilaF<T>();
			pilaClonata.nodoinit=nodoClone;
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
			return(pilaClonata);
		}
	}  
}
