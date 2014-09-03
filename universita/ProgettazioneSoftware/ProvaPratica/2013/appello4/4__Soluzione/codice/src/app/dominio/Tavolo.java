package app.dominio;

public class Tavolo {
  
  private final String numero;
  private int posti;
  
  private final static Tavolo[] tavoli = {new Tavolo("1", 4),
                                          new Tavolo("2", 2),
                                          new Tavolo("3", 4),
                                          new Tavolo("4", 6),
                                          new Tavolo("5", 2)};
  
  public Tavolo(String numero, int posti) {
    super();
    this.numero = numero;
    this.posti = posti;
  }

  public String getNumero() {
    return numero;
  }

  public int getPosti() {
    return posti;
  }

  public void setPosti(int posti) {
    this.posti = posti;
  }

  @Override
  public String toString() {
    return "Tavolo " + numero + " (" + posti + " posti)";
  }
  
  public static Tavolo[] getTavoliDefault() {
    return tavoli;
  }

}
