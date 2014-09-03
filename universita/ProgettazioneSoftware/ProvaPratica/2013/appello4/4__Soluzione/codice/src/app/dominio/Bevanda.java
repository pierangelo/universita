package app.dominio;

public class Bevanda extends Prodotto {
  
  private double gradazione;

  public Bevanda(String nome, double prezzo, double gradazione) {
    super(nome, prezzo);
    this.gradazione = gradazione;
  }

  public double getGradazione() {
    return gradazione;
  }

  public void setGradazione(double gradazione) {
    this.gradazione = gradazione;
  }
  
  @Override
  public String toString() {
    return nome + " (" + gradazione + "% vol)";
  }
  

}
