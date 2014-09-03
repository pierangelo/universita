package app.dominio;

public class Bibita extends Prodotto {
  
  private int quantita;

  public Bibita(String nome, double prezzo, int quantita) {
    super(nome, prezzo);
    this.quantita = quantita;
  }

  public int getQuantita() {
    return quantita;
  }

  public void setQuantita(int quantita) {
    this.quantita = quantita;
  }
  
  @Override
  public String toString() {
    return nome + " (" + quantita + "cl)";
  }
  

}
