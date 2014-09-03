package app.attivita;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.Atleta;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.EccezioneSubset;
import app.dominio.Gara;
import app.dominio.Percorso;
import app.dominio.Tavola;
import app.dominio.TipoLinkPartecipa;
import app.dominio.TipoLinkVince;
import app.gui.ErrorNotifier;
import app.gui.FinestraDatiAtleta;
import app.gui.FinestraDatiPercorso;
import app.gui.FinestraDatiTavola;
import app.gui.FinestraRiassuntoGara;
import app.gui.GaraView;

public class AttivitaIO {

	private AttivitaIO() {}


	// Dati Percorso
	public static Percorso inserisciDatiPercorso() {

		TargetDatiPercorso target = new TargetDatiPercorso();
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		FinestraDatiPercorso finestra = target.finestra;

		return new Percorso(finestra.leggiLunghezza(), finestra.leggiAltezza());

	}

	// Dati Atleta
	public static Atleta inserisciDatiAtleta() {

		TargetDatiAtleta target = new TargetDatiAtleta();
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		FinestraDatiAtleta finestra = target.finestra;

		Atleta atleta = new Atleta(finestra.leggiNome(), finestra.leggiCognome());
		atleta.setNazione(finestra.leggiNazione());
		return atleta;

	}

	// Dati Tavola
	public static Tavola inserisciDatiTavola() {

		TargetDatiTavola target = new TargetDatiTavola();
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		FinestraDatiTavola finestra = target.finestra;

		return new Tavola(finestra.leggiMarca());

	}


	// Altro Atleta
	public static boolean chiediSeAltroAtleta() {

		TargetAltroAtleta target = new TargetAltroAtleta();
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return target.getResult();

	}
	
	public static void mostraErrore() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					ErrorNotifier.notifyError("Una gara richiede la partecipazione di almeno due\ne massimo sei atleti!");
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Riassunto Gara
	public static void visualizzaRiassuntoGara(Gara gara) {

		TargetRiassunto target = new TargetRiassunto(gara);
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	// Visualizza Gara
	public static void visualizzaGara(Gara gara) {

		TargetSimul target = new TargetSimul(gara);
		try {
			SwingUtilities.invokeAndWait(target);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	


	private static class TargetDatiPercorso implements Runnable {

		private FinestraDatiPercorso finestra;

		@Override
		public void run() {
			finestra = new FinestraDatiPercorso();
			finestra.visualizza();
		}
	}

	private static class TargetDatiAtleta implements Runnable {

		private FinestraDatiAtleta finestra;

		@Override
		public void run() {
			finestra = new FinestraDatiAtleta();
			finestra.visualizza();
		}
	}
	
	private static class TargetDatiTavola implements Runnable {

		private FinestraDatiTavola finestra;

		@Override
		public void run() {
			finestra = new FinestraDatiTavola();
			finestra.visualizza();
		}
	}

	private static class TargetAltroAtleta implements Runnable {

		private boolean res;

		@Override
		public void run() {
			int yn = JOptionPane.showConfirmDialog(null,
					"Vuoi aggiungere un altro atleta alla gara?",
					"Iscrizione Atleta", JOptionPane.YES_NO_OPTION);
			res = (yn == JOptionPane.YES_OPTION);
		}

		private boolean getResult() {
			return res;
		}
	}


	private static class TargetRiassunto implements Runnable {

		private Gara gara;

		private TargetRiassunto(Gara gara) {
			this.gara = gara;
		}

		@Override
		public void run() {

			Set<TipoLinkVince> vincitori = null;
			Set<TipoLinkPartecipa> partecipanti = null;
			try {
				vincitori = gara.getLinkVince();
				partecipanti = gara.getLinkPartecipa();
				 FinestraRiassuntoGara fin = new FinestraRiassuntoGara(vincitori, partecipanti);
				 fin.visualizza();
			} catch (EccezioneMoltMinMax e) {
				e.printStackTrace();
				System.exit(1);
			} catch (EccezioneSubset e) {
				e.printStackTrace();
				System.exit(1);
			}


		}
	}

	private static class TargetSimul implements Runnable {

		private Gara gara;

		private TargetSimul(Gara gara) {
			this.gara = gara;
		}

		@Override
		public void run() {
			GaraView gv = new GaraView(gara);
			gv.visualizza();
		}
	}



}
