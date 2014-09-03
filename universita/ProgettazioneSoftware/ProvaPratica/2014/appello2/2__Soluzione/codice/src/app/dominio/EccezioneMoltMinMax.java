package app.dominio;

public class EccezioneMoltMinMax extends Exception {
  private String messaggio;

  public EccezioneMoltMinMax(String m) {
    messaggio = m;
  }

  public String toString() {
    return messaggio;
  }
}
