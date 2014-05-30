package lista.funzionale;
public class TestLista {
  public static void main (String[] args) {
    Lista<String> l1 = new Lista<String>().cons("C").cons("B").cons("A");
    Lista<String> l2 = ListaUso.parseLista("D E F G");
    Lista<String> l3 = ListaUso.parseLista("");
    
    System.out.println("l1 = ( "+l1.toString()+")");
    System.out.println("l2 = ( "+l2.toString()+")");
    
    l2 = ListaUso.aggiungiUltimo(l2,"H");
    l3 = ListaUso.append(ListaUso.append(l3,l1),l2);
    
    System.out.println("l1 = ( "+l1.toString()+")");
    System.out.println("l2 = ( "+l2.toString()+")");
    System.out.println("l3 = ( "+l3.toString()+")");
    
    System.out.println("l3[3] = "+ListaUso.elementoInPosizione(l3,3));
    
    l3 = ListaUso.inserisciElementoInPosizione(l3,0,"X");
    l3 = ListaUso.inserisciElementoInPosizione(l3,ListaUso.lunghezzaLista(l3),"Z");
    
    System.out.println("l3 = ( "+l3.toString()+")");
  }
}

