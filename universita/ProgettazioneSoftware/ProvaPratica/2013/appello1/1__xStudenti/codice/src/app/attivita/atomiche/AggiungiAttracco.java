package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.attivita.RecordDatiAttracco;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Tratta;
import app.dominio.ManagerComprende;
import app.dominio.TipoLinkComprende;

public class AggiungiAttracco implements Task {

  private boolean eseguita = false;
  private Tratta tratta;
  private RecordDatiAttracco recordAttracco;

  public AggiungiAttracco(Tratta l, RecordDatiAttracco r) {
    tratta = l;
    recordAttracco = r;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    TipoLinkComprende link = null;
    try {
      int distanza = 0;
      if (tratta.quantiAttracchi() == 0) {
        distanza = recordAttracco.getDistanza();
      }
      else {
        try {
          distanza = tratta.getLinkComprende().get(tratta.quantiAttracchi()-1).getDistanza() + recordAttracco.getDistanza();
        } catch (EccezioneMoltMinMax e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
      link = new TipoLinkComprende(tratta, recordAttracco.getAttracco(), distanza);
    } catch (EccezionePrecondizioni ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
      System.exit(1);
    }
    ManagerComprende.inserisci(link);
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
