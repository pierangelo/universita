package app.dominio;

public class Cliente {
  
  private final String nome;
  private final String cognome;
  private String indirizzo;
  
  public Cliente(String nome, String cognome, String indirizzo) {
    super();
    this.nome = nome;
    this.cognome = cognome;
    this.indirizzo = indirizzo;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getNome() {
    return nome;
  }

  public String getCognome() {
    return cognome;
  }

  @Override
  public String toString() {
    return "Cliente: " + nome + " " + cognome + "\nIndirizzo: " + indirizzo;
  }
  
  public static Cliente getClienteDefault() {
    return new Cliente("Massimo", "Mecella", "Via Ariosto 25, Roma");
  }

}
