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

      if (!ordineValido) {
        AttivitaIO.MostraErrore();
      }
      else {
        Executor.perform(new RegistraOrdine(ordine, cliente));

        AttivitaSottoramo1 a1 = new AttivitaSottoramo1(ordine);
        Thread ramo1 = new Thread(a1);
        ramo1.start();

        AttivitaSottoramo2 a2 = new AttivitaSottoramo2(ordine);
        Thread ramo2 = new Thread(a2);
        ramo2.start();

        try {
          ramo1.join();
          ramo2.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.exit(1);
        }
      }
      
      nuovoOrdine = AttivitaIO.chiediSeNuovoOrdine();

    } while (nuovoOrdine);

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
