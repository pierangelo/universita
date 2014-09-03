package app.dominio;

public abstract class Prodotto {
  
  protected final String nome;
  protected double prezzo;
  
  private final static Prodotto[] list = {
    new Snack("Patatine Fritte", 7, "patate, olio, sale"),
    new Snack("Noccioline", 4, "arachidi, sale"),
    new Snack("Pistacchi", 3, "pistacchi, sale"),
    new Snack("Olive verdi", 4, "olive, olio"),
    new Bevanda("Guinness", 5, 4.2),
    new Bevanda("Corona Extra", 4.5, 4.6),
    new Bevanda("Paulaner", 5, 5.5),
    new Bevanda("Mojito", 7, 14),
    new Bevanda("Cuba Libre", 7, 18),
    new Bevanda("Rum e Cola", 7, 13.3),
    new Bevanda("Whisky", 7.5, 40),
    new Bevanda("Vodka", 7.5, 35),
    new Bevanda("Baileys", 6, 17),
    new Bevanda("Jagermeister", 6.5, 35)
    
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
