package app.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.List;

import app.dominio.EccezioneMoltMinMax;
import app.dominio.Tratta;
import app.dominio.TipoLinkComprende;
import app.dominio.Battello;

public class PercorsoBattello extends DrawPanel {// non pubblica perchè ad uso interno del package

  private final int LARGHEZZA = 900; // larghezza in pixel del percorso
  private final int ALTEZZA = 40; // altezza 
  private final int SPESSORE_LINEA = 3;
  private final int MARGINE = 10;
  private final int MARGINE_NOMI = 0;
  private final String SEGNALINO = "I_I";
  private Text posizioneBattello;
  private int altezzaTotale = 0;
  private Tratta tratta;

  public PercorsoBattello(Tratta tratta) {
    
    this.setBackground(Color.blue);

    this.tratta = tratta;

    Text segnalinoBattello = new Text(SEGNALINO, new Point(MARGINE + SPESSORE_LINEA + MARGINE_NOMI, MARGINE + SPESSORE_LINEA + ALTEZZA - ALTEZZA / 2));
    segnalinoBattello.setLineColor(Color.white);
    addFigureBatch(segnalinoBattello);
    posizioneBattello = segnalinoBattello;

    // disegna la tratta
    altezzaTotale = ALTEZZA;

    // linee laterali
    Point start = new Point(MARGINE + MARGINE_NOMI, MARGINE + altezzaTotale/2);
    Point end = new Point(MARGINE + MARGINE_NOMI, MARGINE + altezzaTotale);
    Line l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    l.setLineColor(Color.red);
    addFigureBatch(l);
    start = new Point(3 * MARGINE + MARGINE_NOMI, MARGINE + altezzaTotale/2);
    end = new Point(3 * MARGINE + MARGINE_NOMI, MARGINE + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    l.setLineColor(Color.red);
    addFigureBatch(l);

    start = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE + altezzaTotale/2);
    end = new Point(MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    l.setLineColor(Color.red);
    addFigureBatch(l);

    start = new Point(3 * MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE + altezzaTotale/2);
    end = new Point(3 * MARGINE + MARGINE_NOMI + LARGHEZZA, MARGINE + altezzaTotale);
    l = new Line(start, end);
    l.setLineSize(SPESSORE_LINEA);
    l.setLineColor(Color.red);
    addFigureBatch(l);

    List<TipoLinkComprende> insiemeLinkComprende = null; 
    try {
      insiemeLinkComprende = tratta.getLinkComprende();
    } catch (EccezioneMoltMinMax e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    TipoLinkComprende link;
    int dist;
    int spiazzamento;
    Color color;
    
    for (int i = 0; i < insiemeLinkComprende.size(); i++) {
      
      link = insiemeLinkComprende.get(i);
      dist = link.getDistanza();
      color = Color.white;

      spiazzamento = (int) (((double)dist * LARGHEZZA) / tratta.lunghezza());

      start = new Point(MARGINE + MARGINE_NOMI + spiazzamento, MARGINE + altezzaTotale/2);
      end = new Point(MARGINE + MARGINE_NOMI + spiazzamento, MARGINE + altezzaTotale);
      l = new Line(start, end);
      l.setLineSize(SPESSORE_LINEA);
      l.setLineColor(color);
      addFigureBatch(l);
      start = new Point(3 * MARGINE + MARGINE_NOMI + spiazzamento, MARGINE + altezzaTotale/2);
      end = new Point(3 * MARGINE + MARGINE_NOMI + spiazzamento, MARGINE + altezzaTotale);
      l = new Line(start, end);
      l.setLineSize(SPESSORE_LINEA);
      l.setLineColor(color);
      addFigureBatch(l);

      Text segnalinoAttracco = new Text(link.getAttracco().getNome() + " (" + link.getAttracco().getLunghezzaBanchina() + " mt)", new Point(MARGINE + SPESSORE_LINEA + MARGINE_NOMI + spiazzamento, MARGINE + altezzaTotale + 5*SPESSORE_LINEA));
      segnalinoAttracco.setLineColor(Color.white);
      addFigureBatch(segnalinoAttracco);

    }
    // corsie
    /*
    for (int i = 0; i <= 1; i++) {
      start = new Point(MARGINE, MARGINE + ALTEZZA * i);
      end = new Point(3 * MARGINE + LARGHEZZA + MARGINE_NOMI, MARGINE + ALTEZZA * i);
      l = new Line(start, end);
      l.setLineSize(SPESSORE_LINEA);
      l.setLineColor(Color.white);
      addFigureBatch(l);
    }
    */

    commit();
  }

  public void setPosizioneBattello(Battello treno, double kmPercorsi) {
    // Normalizza la posizione in base alla dimensione del percorso
    int nuovaPosizioneBattello = (int) (((double)kmPercorsi / (tratta.lunghezza())) * LARGHEZZA);
    if (nuovaPosizioneBattello >= LARGHEZZA) {// arrivato alla fine
      nuovaPosizioneBattello = LARGHEZZA;
    }
    // Posiziona il Segnalino in base alla nuova posizione (l'altezza e' la
    // stessa del segnalino precedente)
    Text segnalinoBattello = new Text(SEGNALINO, new Point(MARGINE + SPESSORE_LINEA + MARGINE_NOMI + nuovaPosizioneBattello, posizioneBattello.getPoint().y));
    segnalinoBattello.setLineColor(Color.white);
    removeFigure(posizioneBattello);
    addFigureBatch(segnalinoBattello);
    posizioneBattello = segnalinoBattello;
    commit();
  }


  @Override
  public Dimension getPreferredSize() {
    return (new Dimension(LARGHEZZA + MARGINE_NOMI + 5 * MARGINE, altezzaTotale + 3*MARGINE));
  }
}
