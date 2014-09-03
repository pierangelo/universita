package app.dominio;

public class EccezioneSubset extends Exception {

  private String messaggio;

  public EccezioneSubset(String m) {
    messaggio = m;
  }

  public String toString() {
    return messaggio;
  }
}
