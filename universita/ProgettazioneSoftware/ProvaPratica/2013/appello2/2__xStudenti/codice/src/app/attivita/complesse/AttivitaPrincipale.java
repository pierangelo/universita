package app.attivita.complesse;

import app._framework.Executor;
import app.attivita.AttivitaIO;
import app.attivita.RecordSelezioneProdotto;
import app.attivita.atomiche.AggiungiProdotto;
import app.attivita.atomiche.RegistraOrdine;
import app.attivita.atomiche.VerificaOrdine;
import app.dominio.Cliente;
import app.dominio.Ordine;

public class AttivitaPrincipale implements Runnable {

  private boolean eseguita = false;

  private Cliente cliente;
  private Ordine ordine;
  private RecordSelezioneProdotto recordProdotto;
  private boolean altroProdotto;
  private boolean ordineValido;
  private boolean nuovoOrdine;


  public AttivitaPrincipale() {}

  public synchronized void run() {
    if (eseguita == true)
      return;
    eseguita = true;

    do {

      cliente = AttivitaIO.inserisciDatiCliente();
      ordine = AttivitaIO.inserisciDatiOrdine();

      do {
        recordProdotto = AttivitaIO.selezionaProdotto();
        Executor.perform(new AggiungiProdotto(ordine, recordProdotto));
        altroProdotto = AttivitaIO.chiediSeAltroProdotto();
      } while (altroProdotto);

      VerificaOrdine vo = new VerificaOrdine(ordine);
      Executor.perform(vo);
      ordineValido = vo.getRisultato();

      /* DA COMPLETARE A CURA DELLO STUDENTE */
      
      
      

    } while (nuovoOrdine);

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
