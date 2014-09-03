package app.gui;

import javax.swing.*;

import app._gestioneeventi.Environment;
import app.dominio.*;
import app.dominio.Atleta.Stato;
import app.dominio.eventi.Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GaraView extends JDialog implements Observer, ActionListener {


	private final JButton bottonePartenza = new JButton("Partenza");
	private final JButton bottoneChiudi = new JButton("Chiudi");

	protected PercorsoSalto percorsoSalto; // rendere accessibile all'attivita' di aggiornamento
	private Gara gara;

	public GaraView(Gara gara) {

		super((JFrame)null,  "Simulazione gara", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.gara = gara;

		// si registra come observer degli atleti
		try {
			for(TipoLinkPartecipa link : this.gara.getLinkPartecipa()) {
				link.getAtleta().addObserver(this);
			}
		} catch (EccezioneMoltMinMax e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		prepara();

	}

	private void prepara() {// prepara la finestra per la visualizzazione

		percorsoSalto = new PercorsoSalto(gara);
		getContentPane().add(percorsoSalto, BorderLayout.CENTER);

		JLabel infoGara = new JLabel("Gara: " + gara.getCodice() + " del " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(gara.getData()));
		JPanel garaInfo = new JPanel();

		garaInfo.add(infoGara);
		getContentPane().add(garaInfo, BorderLayout.PAGE_START);

		// Bottoni
		JPanel buttonPanel = new JPanel();
		// Partenza
		bottonePartenza.setActionCommand("parti");
		bottonePartenza.addActionListener(this);
		buttonPanel.add(bottonePartenza);
		// Chiudi
		bottoneChiudi.addActionListener(this);
		bottoneChiudi.setEnabled(false);
		buttonPanel.add(bottoneChiudi);

		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
	}

	public void visualizza() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Observable atleta, final Object update) {
		//invocato in seguito ad un aggiornamento in un atleta

		final Atleta loAtleta = (Atleta)atleta;

		if (update instanceof Atleta.Stato) {
			try {
				Set<TipoLinkPartecipa> linkPartecipanti = loAtleta.getLinkPartecipa().getGara().getLinkPartecipa();
				boolean arrivati = true;
				for (TipoLinkPartecipa link : linkPartecipanti) {
					arrivati = arrivati && link.getAtleta().getStato().equals(Stato.FINITO);
				}
				if (arrivati) {
					try {
						SwingUtilities.invokeAndWait(new Runnable() {

							@Override
							public void run() {
								bottoneChiudi.setEnabled(true);
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
			} catch (EccezioneMoltMinMax e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			// alla fine chiamera' repaint che e' thread safe...no in EDT
			percorsoSalto.setPosizioneAtleta(loAtleta, (Posizione)update);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("parti")) {
			bottonePartenza.setEnabled(false);
			Environment.aggiungiEvento(new Start(null, null));
		}
		else { // chiudi
			this.setVisible(false);
			this.dispose();
		}

	}
}
