package app.attivita;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.*;
import app.gui.*;

public class AttivitaIO {

	private AttivitaIO() {}

	// Seleziona Condominio
	public static Condominio selezionaCondominio(Set<Condominio> condomini) {
		TargetSceltaCondominio target = new TargetSceltaCondominio(condomini);
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
	
	// Seleziona Anno
	public static int selezionaAnno(Condominio condominio) {
		Set<Integer> anni = new HashSet<Integer>();
		Set<TipoLinkSostiene> linkSostiene = null;
		try {
			linkSostiene = condominio.getLinkSostiene();
			
		} catch (EccezioneMoltMinMax e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spesa spesa;
		int anno;
		for (TipoLinkSostiene link : linkSostiene) {
			spesa = link.getSpesa();
			anno = spesa.getAnno();
			anni.add(new Integer(anno));
		}
		TargetSceltaAnno target = new TargetSceltaAnno(anni);
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
	
	public static void visualizzaSpese(final double speseOrdinarie, final double speseStraordinarie) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, "Spese Ordinarie: " + speseOrdinarie + " euro\nSpese Straordinarie: " + speseStraordinarie + " euro", "Spese", JOptionPane.INFORMATION_MESSAGE);
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
	
	public static void mostraNotifica() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, "Attenzione! Le spese straordinarie superano le spese ordinarie!", "Notifica", JOptionPane.INFORMATION_MESSAGE);
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
	
	public static void visualizzaProprietari(Set<Proprietario> proprietari) {
		final String message;
		
		if (proprietari.isEmpty()) {
			message = "Non ci sono proprietari che possiedono\nimmobili in altri condomini!";
		}
		else {
		
			StringBuilder builder = new StringBuilder("I seguenti proprietari possiedono immobili\nin altri condomini:\n\n");
			for (Proprietario p : proprietari) {
				builder.append(p.getNome());
				builder.append(" ");
				builder.append(p.getCognome());
				builder.append("\n");
			}
			
			message = builder.toString();
		}
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, message, "Info Proprietari", JOptionPane.INFORMATION_MESSAGE);
					
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
	
	public static void visualizzaRipartizione(Map<Immobile, Double> ripartizione) {
		final Map<Immobile, Double> map = ripartizione;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					new FinestraRipartizioneSpese(map);
					
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
	
	
	
	
	

	private static class TargetSceltaCondominio implements Runnable {

		private Set<Condominio> condomini;
		private Condominio res;

		private TargetSceltaCondominio(Set<Condominio> insiemeCondomini) {
			condomini = insiemeCondomini;
		}

		@Override
		public void run() {
			Object selezione = JOptionPane.showInputDialog(null, "Selezionare un condominio", "Selezione", JOptionPane.INFORMATION_MESSAGE, null, condomini.toArray(), null);
			if (selezione == null) {
				System.exit(1);
			}
			else {
				res = (Condominio) selezione;
			}
		}

		private Condominio getResult() {
			return res;
		}
	}
	
	private static class TargetSceltaAnno implements Runnable {

		private Integer[] anni;
		private int res;

		private TargetSceltaAnno(Set<Integer> insiemeAnni) {
			anni = new Integer[insiemeAnni.size()];
			insiemeAnni.toArray(anni);
			Arrays.sort(anni);
		}

		@Override
		public void run() {
			Object selezione  = JOptionPane.showInputDialog(null, "Selezionare l'anno di interesse", "Selezione", JOptionPane.INFORMATION_MESSAGE, null, anni, null);
			if (selezione == null) {
				System.exit(1);
			}
			else {
				res = (Integer) selezione;
			}
		}

		private int getResult() {
			return res;
		}
	}


	

}
