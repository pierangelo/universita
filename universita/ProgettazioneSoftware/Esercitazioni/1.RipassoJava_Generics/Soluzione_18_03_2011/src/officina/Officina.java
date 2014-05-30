package officina;

class Nodo {
  public Mezzo unMezzo;
  public boolean pronta;
  public Nodo next;
}

public class Officina 
{

  // rappresentazione dei valore del TA
  
  private String partitaIva;
  private Nodo nodoinit;
  
  // realizzazione delle funzioni del tipo astratto
  
  public Officina(String pi) {
    partitaIva = pi;
    nodoinit = null;
  }
  
  public String partitaIva() {
    return partitaIva;
  }
  
  public void arrivaMezzo(Mezzo a) {
    if (presente(a))
      throw new RuntimeException("Officina: violate precond. arrivaAuto");
    Nodo aux = new Nodo();
    aux.unMezzo = a;
    aux.pronta = false;
    aux.next = nodoinit;
    nodoinit = aux;
  }
  
  public void approntaMezzo(Mezzo a) {
    Nodo aux = nodoinit;
    while (aux != null) {
      if (aux.unMezzo.equals(a)) 
        if (!aux.pronta) {
          aux.pronta = true; 
          return;
        }
        else
          throw new RuntimeException(
            "Officina: violate precond. approntaAuto");
      aux = aux.next;
    }
    throw new RuntimeException("Officina: violate precond. approntaAuto");
  }
  
  public void parteMezzo(Mezzo a) {
    if (nodoinit == null) 
      throw new RuntimeException("Officina: violate precond. parteAuto");
    if (nodoinit.unMezzo.equals(a))
      if (nodoinit.pronta) 
        nodoinit = nodoinit.next;
      else
        throw new RuntimeException("Officina: violate precond. parteAuto");
    else {
      Nodo aux = nodoinit;
      while(aux.next != null) {
        if (aux.next.unMezzo.equals(a))
          if (aux.next.pronta) {
            aux.next = aux.next.next;
            return;
          }
          else
            throw new RuntimeException(
              "Officina: violate precond. parteAuto");
        aux = aux.next;
      }
      throw new RuntimeException("Officina: violate precond. parteAuto");
    }
  }
  
  public boolean estInRiparazione(Mezzo a) {
    Nodo aux = nodoinit;
    while (aux != null) {
      if (aux.unMezzo.equals(a)) return !aux.pronta;
      aux = aux.next;
    }
    return false;
  }
  
  public boolean estPronta(Mezzo a) {
    Nodo aux = nodoinit;
    while (aux != null) {
      if (aux.unMezzo.equals(a)) return aux.pronta;
      aux = aux.next;
    }
    return false;
  }

  //metodi ausiliari

  private boolean presente(Mezzo a) {
    Nodo aux = nodoinit;
    while (aux != null) {
      if (aux.unMezzo.equals(a)) return true;
      aux = aux.next;
    }
    return false;
  }
}
