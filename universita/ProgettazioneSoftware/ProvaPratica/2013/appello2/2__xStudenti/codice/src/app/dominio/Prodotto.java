package app.dominio;

public abstract class Prodotto {
  
  protected final String nome;
  protected double prezzo;
  private final static Prodotto[] list =  {new Pizza("Acciughe", 7, "acciughe, mozzarella, pomodoro"),
    new Pizza("Boscaiola", 8, "funghi, mozzarella, panna, pomodoro"),
    new Pizza("Capricciosa", 9, "carciofi, funghi, mozzarella, pomodoro, prosciutto"),
    new Pizza("Diavola", 7, "mozzarella, pomodoro, salamino"),
    new Pizza("Funghi", 7, "funghi, mozzarella, pomodoro"),
    new Pizza("Margherita", 6.5, "mozzarella, pomodoro"),
    new Pizza("Marinara", 6, "aglio, olio d'oliva, pomodoro"),
    new Pizza("Prosciutto Cotto", 8, "mozzarella, pomodoro, prosciutto"),
    new Pizza("Quattro Stagioni", 9, "carciofi, funghi, mozzarella, peperoni, pomodoro, prosciutto"),
    new Bibita("Acqua Frizzante", 1, 50),
    new Bibita("Acqua Naturale", 1, 50),
    new Bibita("Coca Cola", 1.5, 33),
    new Bibita("Fanta", 1.5, 33),
    new Bibita("The al Limone", 1.5, 33),
    new Bibita("The alla Pesca", 1.5, 33),
    new Bibita("Birra Peroni", 3, 33),
    new Bibita("Birra Nastro Azzurro", 3, 33),
    new Bibita("Birra Heineken", 3.5, 33),
    new Bibita("Birra Corona", 3.5, 33)
    
};
  
  public Prodotto(String nome, double prezzo) {
    super();
    this.nome = nome;
    this.prezzo = prezzo;
  }

  public double getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(double prezzo) {
    this.prezzo = prezzo;
  }

  public String getNome() {
    return nome;
  }
  
  
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Prodotto other = (Prodotto) obj;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    return true;
  }

  public static Prodotto[] getDefaultProdotti() {
    return list;
  }

}
