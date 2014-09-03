package app.gui;

import java.awt.*;
import java.util.*;

import app.dominio.*;

class Percorso extends DrawPanel {// non pubblica perchè ad uso interno del
                                  // package
  private final int LARGHEZZA = 800; // larghezza in pixel del percorso
  private final int ALTEZZA = 40; // altezza di ciascuna corsia in pixel
  private final int SPESSORE_LINEA = 3;
  private final int MARGINE = 10;
  private final int MARGINE_NOMI = 100;
  private final String SEGNALINO = "\\__/";
  private HashMap<Equipaggio, Text> posizione; // per memorizzare la posizione degli equipaggi lungo il percorso
  private int altezzaTotale = 0;
  private Regata regata;

  public Percorso(Regata regata) {
    
    super();
    
    this.regata = regata;
    posizione = new HashMap<Equipaggio, Text>();
    
    Set<TipoLinkPartecipa> linkPartecipanti = null;
    
    try {
      linkPartecipanti = regata.getLinkPartecipa();
    } catch (EccezioneMoltMinMax e) {
      e.printStackTrace();
      System.exit(1);
    }
    
    // Inizializza la mappa ed i segnalini degli equipaggi
    Iterator<TipoLinkPartecipa> it = linkPartecipanti.iterator();
    int i = 1;
    while (it.hasNext()) {
      Equipaggio equipaggioCorrente = it.next().getEquipaggio();
      addFigureBatch(new Text(equipaggioCorrente.getNome(), new Point(MARGINE,
          MARGINE + SPESSORE_LINEA + ALTEZZA * i - ALTEZZA / 2)));
      Text segnalinoEquipaggioCorrente = new Text(SEGNALINO, new Point(MARGINE
          + SPESSORE_LINEA + MARGINE_NOMI, MARGINE + SPESSORE_LINEA + ALTEZZA
          * i - ALTEZZA / 2));
      addFigureBatch(segnalinoEquipaggioCorrente);
      posizione.put(equipaggioCorrente, segnalinoEquipaggioCorrente);
      i++;
    }

    // disegna il campo di regata

    altezzaTotale = linkPartecipanti.size() * ALTEZZA;

    // linee laterali
    Point start = new Point(MARGINE + MARGINE_NOMI, MARGINE);
    Point end = new Point(MARGINE + MARGINE_NOMI, MARGINE + altezzaTotale);
    Line l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    addFigureBatch(l);

    start = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE);
    end = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    l.setLineColor(Color.GREEN);
    addFigureBatch(l);

    start = new Point(5 * MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE);
    end = new Point(5 * MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE
        + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    addFigureBatch(l);
    
    //new
    /*
    start = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA/4, MARGINE);
    end = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA/4, MARGINE
        + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    addFigureBatch(l);

    start = new Point(MARGINE + MARGINE_NOMI + 2*LARGHEZZA/4, MARGINE);
    end = new Point(MARGINE + MARGINE_NOMI + 2*LARGHEZZA/4, MARGINE
        + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    addFigureBatch(l);
    
    start = new Point(MARGINE + MARGINE_NOMI + 3*LARGHEZZA/4, MARGINE);
    end = new Point(MARGINE + MARGINE_NOMI + 3*LARGHEZZA/4, MARGINE
        + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    addFigureBatch(l);
    // fine new
    */
    
    // corsie
    for (i = 0; i <= linkPartecipanti.size(); i++) {
      start = new Point(MARGINE, MARGINE + ALTEZZA * i);
      end = new Point(5 * MARGINE + LARGHEZZA + MARGINE_NOMI, MARGINE + ALTEZZA
          * i);
      l = new Line(start, end);
      l.setLineSize(SPESSORE_LINEA);
      addFigureBatch(l);
    }

    //commit();
  }

  public void setPosizioneEquipaggio(Equipaggio equipaggio, float kmPercorsi) {
    // Normalizza la posizione in base alla dimensione del percorso
    int nuovaPosizioneEquipaggio = (int) ((kmPercorsi / regata.getDistanza()) * LARGHEZZA);
    if (nuovaPosizioneEquipaggio >= LARGHEZZA) {// arrivato al traguardo
      nuovaPosizioneEquipaggio = LARGHEZZA;
    }
    // Posiziona il Segnalino in base alla nuova posizione (l'altezza e' la
    // stessa del segnalino precedente)
    Text segnalinoEquipaggio = new Text(SEGNALINO, new Point(MARGINE
        + SPESSORE_LINEA + MARGINE_NOMI + nuovaPosizioneEquipaggio, posizione
        .get(equipaggio).getPoint().y));
    removeFigure(posizione.get(equipaggio)); // toglie l'equipaggio dalla posizione corrente...
    addFigureBatch(segnalinoEquipaggio);// ... lo rimpiazza con quella nuova
    posizione.put(equipaggio, segnalinoEquipaggio); // aggiorna la mappa
    commit();
  }

  @Override
  public Dimension getPreferredSize() {
    /*return (new Dimension(LARGHEZZA + MARGINE_NOMI + 5 * MARGINE, altezzaTotale
        + MARGINE_NOMI + MARGINE));*/
    return (new Dimension(LARGHEZZA + MARGINE_NOMI + 7 * MARGINE, altezzaTotale + 3*MARGINE));
  }
}
