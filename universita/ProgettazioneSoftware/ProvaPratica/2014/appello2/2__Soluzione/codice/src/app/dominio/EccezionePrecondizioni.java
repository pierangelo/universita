package app.dominio;

public class EccezionePrecondizioni extends Exception {
  private String messaggio;

  public EccezionePrecondizioni(String unMessaggio) {
    messaggio = unMessaggio;
  }

  public String getMessage() {
    return (messaggio);
  }
}
