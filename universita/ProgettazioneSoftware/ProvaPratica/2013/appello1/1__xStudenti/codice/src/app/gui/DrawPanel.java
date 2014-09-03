package app.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
  
  private LinkedList<Figure> listFigures = new LinkedList<Figure>();

  public void addFigureBatch(Figure f) {
    synchronized (listFigures) {
      listFigures.add(f);
    }
  }

  public void commit() {
    this.repaint();
  }

  public void addFigure(Figure f) {
    addFigureBatch(f);
    commit();
  }

  public void removeFigure(Figure f) {
    synchronized (listFigures) {
      listFigures.remove(f);
    }
    this.repaint();
  }

  @SuppressWarnings("unchecked")
  public List<Figure> getListFigures() {
    return ((List<Figure>) listFigures.clone());
  }

  @Override
  public void paintComponent(Graphics g) {
    synchronized (listFigures) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      Iterator<Figure> iter = listFigures.iterator();
      while (iter.hasNext()) {
        Figure fig = iter.next();
        fig.draw(g2D);
      }
    }
  }

  public void reset() {
    synchronized (listFigures) {
      listFigures.clear();
    }
  }
}
