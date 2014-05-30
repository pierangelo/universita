package lista.funzionale;
import java.util.StringTokenizer;

public class ListaUso {

  public static <T> int lunghezzaLista(Lista<T> l) {
    if (l.estVuota()) return 0; 
    else  return 1 + lunghezzaLista(l.cdr()); 
  }

  public static <T> Lista<T> aggiungiUltimo(Lista<T> l, T o) {
    if (l.estVuota()) return l.cons(o);
    else return aggiungiUltimo(l.cdr(),o).cons(l.car());
  }

  public static <T> Lista<T> append(Lista<T> l1, Lista<T> l2) {
    if (l1.estVuota()) return l2;
    else return append(l1.cdr(),l2).cons(l1.car());
  }

  public static <T> T elementoInPosizione(Lista<T> l, int i) {
    if ( l.estVuota() || i<0 )
      throw new RuntimeException("ListaUso: indice fuori dai limiti");
    else if (i==0) return l.car();
    else return elementoInPosizione(l.cdr(), i-1);
  }

  public static <T> Lista<T> inserisciElementoInPosizione(Lista<T> l, int i,
                                                   T o) {
    if (i<0) 
      throw new RuntimeException("ListaUso: indice fuori dai limiti");
    else if (i==0) return l.cons(o);
    else return inserisciElementoInPosizione(l.cdr(),i-1,o).cons(l.car());
  }

  public static Lista<String> parseLista(String s) {
    return parseAux(new StringTokenizer(s));
  }
  
  private static Lista<String> parseAux(StringTokenizer st) {
    if (st.hasMoreTokens()) {
      String e = st.nextToken();
      return parseAux(st).cons(e);
    }
    else return new Lista<String>();
  }    
}
