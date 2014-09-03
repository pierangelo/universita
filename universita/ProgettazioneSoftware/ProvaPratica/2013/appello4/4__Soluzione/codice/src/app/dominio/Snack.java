package app.dominio;

public class Snack extends Prodotto {
  
  public String ingredienti;

  public Snack(String nome, double prezzo, String ingredienti) {
    super(nome, prezzo);
    this.ingredienti = ingredienti;
  }

  public String getIngredienti() {
    return ingredienti;
  }

  public void setIngredienti(String ingredienti) {
    this.ingredienti = ingredienti;
  }

  @Override
  public String toString() {
    return nome + " (" + ingredienti + ")";
  }
  
  

}
