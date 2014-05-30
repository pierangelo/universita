package pila.funzionale;

public class TestPilaF {
  public static void main (String[] args) {
    PilaF<String> p1 = new PilaF<String>();
    // inserisce elementi nella pila
    PilaF<String> p2 = p1.push("A").push("B").push("C");
    // stampa la pila p2;
    PilaF<String> q = p2;
    while (!q.estVuota()) {
      System.out.println(q.top());
      q = q.pop();
    }
  }
}

