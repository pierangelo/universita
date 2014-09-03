package app.attivita;

import app.dominio.Prodotto;

public class RecordSelezioneProdotto {
  
  private final Prodotto prodotto;
  private final int quantita;
  
  public RecordSelezioneProdotto(Prodotto prodotto, int quantita) {
    this.prodotto = prodotto;
    this.quantita = quantita;
  }

  public Prodotto getProdotto() {
    return prodotto;
  }

  public int getQuantita() {
    return quantita;
  }
  
}
