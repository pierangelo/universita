package app.dominio;

public class TipoLinkPartecipa {
  
  private final Regata regata;
  private final Equipaggio equipaggio;
  private final float kmPercorsi;

  public TipoLinkPartecipa(Regata regata, Equipaggio equipaggio, float kmPercorsi) throws EccezionePrecondizioni {
    if (regata == null || equipaggio == null) {
      throw new EccezionePrecondizioni("Parametri nulli non ammessi");
    }
    this.equipaggio = equipaggio;
    this.regata = regata;
    this.kmPercorsi = kmPercorsi;
  }

  public Regata getRegata() {
    return regata;
  }

  public Equipaggio getEquipaggio() {
    return equipaggio;
  }

  public float getKmPercorsi() {
    return kmPercorsi;
  }

  public int hashCode() {
    return regata.hashCode() + equipaggio.hashCode();
  }

  public boolean equals(Object o) {
    if (o == null || (this.getClass() != o.getClass()))
      return false;
    TipoLinkPartecipa l = (TipoLinkPartecipa) o;
    return (regata == l.getRegata() && equipaggio == l.getEquipaggio());
  }

}
