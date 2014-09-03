package app.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import app.dominio.Atleta;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Gara;
import app.dominio.Posizione;
import app.dominio.TipoLinkPartecipa;

public class PercorsoSalto extends DrawPanel {// non pubblica perche' ad uso interno del package

	public final static int LARGHEZZA = 400; // larghezza in pixel del percorso
	public final static int LARGHEZZA_AGGIUNTIVA = 100; // larghezza in pixel del percorso
	private final int ALTEZZA = 100; // altezza di ciascuna corsia in pixel
	private final int SPESSORE_LINEA = 10;
	private final int MARGINE_TOP = 200;
	private final int MARGINE = 100;
	
	private final String SEGNALINO = "O";
	private HashMap<Atleta, Text> posizioneAtleti; // per memorizzare la posizione degli atleti lungo il percorso
	private HashMap<Atleta, Text> salti; // per memorizzare i salti
	
	private Color[] colors = {Color.GREEN, Color.CYAN, Color.RED, Color.WHITE, Color.PINK, Color.ORANGE};
	
	private Gara gara;

	public PercorsoSalto(Gara gara) {

		super();

		this.gara = gara;
		posizioneAtleti = new HashMap<Atleta, Text>();
		salti = new HashMap<Atleta, Text>();

		Set<TipoLinkPartecipa> linkPartecipanti = null;

		try {
			linkPartecipanti = this.gara.getLinkPartecipa();
		} catch (EccezioneMoltMinMax e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Inizializza la mappa ed i segnalini degli atleti
		Iterator<TipoLinkPartecipa> it = linkPartecipanti.iterator();
		int i = 0;
		while (it.hasNext()) {
			Atleta atletaCorrente = it.next().getAtleta();
			
			Text segnalinoAtletaCorrente = new Text(SEGNALINO, new Point(MARGINE, MARGINE_TOP));
			segnalinoAtletaCorrente.setLineColor(colors[i]);
			addFigureBatch(segnalinoAtletaCorrente);
			posizioneAtleti.put(atletaCorrente, segnalinoAtletaCorrente);
			
			
			Text segnalinoSaltoAtletaCorrente = new Text(atletaCorrente.getNome() + " " + atletaCorrente.getCognome() + " (" + atletaCorrente.getNazione() + "): " + ": 0 mt", new Point(MARGINE, MARGINE_TOP/4 + 15*i));
			segnalinoSaltoAtletaCorrente.setLineColor(colors[i]);
			addFigureBatch(segnalinoSaltoAtletaCorrente);
			salti.put(atletaCorrente, segnalinoSaltoAtletaCorrente);
			
			i++;
		}

		// disegna il percorso di salto

		//assi salto: verticale
		Point start = new Point(MARGINE, MARGINE_TOP);
		Point end = new Point(MARGINE, MARGINE_TOP + ALTEZZA);
		Line verticalAx = new Line(start, end);
		verticalAx.setLineSize(SPESSORE_LINEA);
		addFigureBatch(verticalAx);
		
		//assi salto: orizzontale
		start = new Point(MARGINE, MARGINE_TOP + ALTEZZA);
		end = new Point(MARGINE + LARGHEZZA, MARGINE_TOP + ALTEZZA);
		Line horizontalAx = new Line(start, end);
		horizontalAx.setLineSize(SPESSORE_LINEA);
		addFigureBatch(horizontalAx);
		
		//prolungamento orizzontale
		start = new Point(MARGINE + LARGHEZZA, MARGINE_TOP + ALTEZZA);
		end = new Point(MARGINE + LARGHEZZA + LARGHEZZA_AGGIUNTIVA + 30, MARGINE_TOP + ALTEZZA);
		Line horizontalAxProl = new Line(start, end);
		horizontalAxProl.setLineSize(SPESSORE_LINEA);
		//horizontalAxProl.setLineColor(Color.GREEN);
		addFigureBatch(horizontalAxProl);

		//commit();
	}

	public void setPosizioneAtleta(Atleta atleta, Posizione posizione) {
		

		double lunghezzaPercorso = 0;
		double altezza = 0;
		try {
			lunghezzaPercorso = gara.getPercorso().getLunghezza();
			altezza = gara.getPercorso().getAltezza();
		} catch (EccezioneMoltMinMax e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// Normalizza la posizione in base alla dimensione del percorso
		int nuovaPosizioneAtletaX = (int) ((posizione.getX() / lunghezzaPercorso) * LARGHEZZA);
		if (posizione.getY() < 0)
			posizione.setY(0);
		int nuovaPosizioneAtletaY = (int) (((altezza-posizione.getY()) / altezza) * ALTEZZA);
		
		
		// Posiziona il Segnalino in base alla nuova posizione
		Text segnalinoAtleta = new Text(SEGNALINO, new Point(MARGINE + nuovaPosizioneAtletaX, MARGINE_TOP + nuovaPosizioneAtletaY));
		segnalinoAtleta.setLineColor(this.posizioneAtleti.get(atleta).getLineColor());
		removeFigure(this.posizioneAtleti.get(atleta)); // toglie l'atleta dalla posizione corrente...
		addFigureBatch(segnalinoAtleta);// ... lo rimpiazza con quella nuova
		this.posizioneAtleti.put(atleta, segnalinoAtleta); // aggiorna la mappa
		
		// Aggiorna etichetta salto in base alla distanza
		Text vecchiaLabel = salti.get(atleta);
		vecchiaLabel.setText(atleta.getNome() + " " + atleta.getCognome() + " (" + atleta.getNazione() + "): " + new DecimalFormat("#.##").format(posizione.getX()));
		commit();
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(LARGHEZZA + 3*MARGINE, ALTEZZA + MARGINE_TOP + MARGINE));
	}
}
