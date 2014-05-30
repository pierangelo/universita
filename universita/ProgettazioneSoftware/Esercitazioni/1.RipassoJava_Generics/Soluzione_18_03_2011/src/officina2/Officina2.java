package officina2;

import java.util.*;

enum status
{
	RIPARAZIONE,PRONTA;
}

class Nodo 
{
	  public Mezzo mezzo;
	  public status status;

	  public boolean equals(Object o)
	  {
		  if (o==null || !getClass().equals(o.getClass()))
			  return(false);
		  Nodo secondo=(Nodo)o;
		  return(mezzo.equals(secondo.mezzo));
	  }
	  
	  public int hashCode()
	  {
		  return(mezzo.hashCode());
	  }
}

public class Officina2 
{ 
  private String partitaIva;
  private Set<Nodo> insieme;
  
  // realizzazione delle funzioni del tipo astratto
  
  public Officina2(String pi) 
  {
    partitaIva = pi;
    insieme=new HashSet<Nodo>();
  }
  
  public String partitaIva() {
    return partitaIva;
  }
  
  public void arrivaMezzo(Mezzo a) 
  {
	  Nodo x=new Nodo();
	  x.mezzo=a;
	  x.status=status.RIPARAZIONE;
	  boolean giaEsistente=!insieme.add(x);
	  if (giaEsistente)
		  throw new RuntimeException("Officina: violate precond. arrivaAuto");
  }
  
  public void approntaMezzo(Mezzo a) 
  {
	  Nodo x=trovaMezzo(a);
	  if (x==null || x.status==status.PRONTA)
	  {
		  throw new RuntimeException("Officina: violate precond. approntaAuto"); 
	  }
	  else
		  x.status=status.PRONTA;
  }
  
  public void parteMezzo(Mezzo a) 
  {
	  Nodo x=trovaMezzo(a);
	  if (x==null || x.status==status.RIPARAZIONE)
	  {
		  throw new RuntimeException("Officina: violate precond. parteAuto"); 
	  }
	  else
		  x.status=status.PRONTA;
  }
  
  public boolean estInRiparazione(Mezzo a) 
  {
    Nodo x = trovaMezzo(a);
    if (x!=null)
    {
    	return x.status==status.RIPARAZIONE;
    }
    else
    	return false;
  }
  
  public boolean estPronta(Mezzo a) 
  {
    Nodo x = trovaMezzo(a);
    if (x!=null)
    {
    	return x.status==status.PRONTA;
    }
    else
    	return false;
  }

  
  private Nodo trovaMezzo(Mezzo a)
  {
	  for (Nodo n : insieme)
	  {
		  if (n.mezzo.equals(a))
			  return(n);
	  }
	  return(null);
  }
}

