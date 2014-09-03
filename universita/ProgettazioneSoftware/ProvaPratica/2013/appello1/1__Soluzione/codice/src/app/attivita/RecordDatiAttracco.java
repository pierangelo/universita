package app.attivita;

import app.dominio.Attracco;

public class RecordDatiAttracco {
  
  private final Attracco attracco;
  private final int distanza;
  
  public RecordDatiAttracco(Attracco f, int dist) {
    this.attracco = f;
    this.distanza = dist;
  }

  public Attracco getAttracco() {
    return attracco;
  }

  public int getDistanza() {
    return distanza;
  }
  
}
