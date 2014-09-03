package app.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import app.dominio.Semaforo;
import app.dominio.Semaforo.Stato;

public class SemaforoView extends JPanel implements Observer {

  private Semaforo semaforo;
  Ellipse2D redLight = new Ellipse2D.Double(10, 10, 100, 100);
  Ellipse2D yellowLight = new Ellipse2D.Double(10, 115, 100, 100);
  Ellipse2D greenLight = new Ellipse2D.Double(10, 220, 100, 100);

  Color redColor = Color.red;
  Color yellowColor = Color.yellow;
  Color greenColor = Color.green;
  Color grayColor = Color.gray;
  
  Color color = grayColor;

  public SemaforoView(Semaforo semaforo) {
    super();
    this.semaforo = semaforo;
    this.semaforo.addObserver(this);
    setPreferredSize(new Dimension(120, 330));
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    g2.setColor(grayColor);
    
    if (color == redColor) {
      g2.setColor(redColor);
      g2.fill(redLight);
      g2.setColor(Color.black);
      g2.draw(redLight);
      g2.setColor(Color.gray);
    }
    else {
      g2.fill(redLight);
    }

    if (color == yellowColor) {
      g2.setColor(yellowColor);
      g2.fill(yellowLight);
      g2.setColor(Color.black);
      g2.draw(yellowLight);
      g2.setColor(grayColor);
      color = greenColor;
    }
    else {
      g2.fill(yellowLight);
    }

    if (color == greenColor) {
      g2.setColor(greenColor);
      g2.fill(greenLight);
      g2.setColor(Color.black);
      g2.draw(greenLight);
      g2.setColor(Color.gray);
    }
    else {
      g2.fill(greenLight);
    }
    
    
  }
  
  //turn red light on
  public void turnOnRed() {
    color = redColor;
    repaint();
  }

  // turn yellow light on
  public void turnOnYellow() {
    color = yellowColor;
    repaint();
  }

  // turn green light on
  public void turnOnGreen() {
    color = greenColor;
    repaint();
  }
  
  public void turnOff() {
    color = grayColor;
    repaint();
  }

  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
    Semaforo sem = (Semaforo)o;
    Stato stato = sem.getStato();
    
    if (stato.equals(Stato.ROSSO)) {
      turnOnRed();
    }
    else if (stato.equals(Stato.GIALLO)) {
      turnOnYellow();
    }
    else if (stato.equals(Stato.VERDE)) {
      turnOnGreen();
    }
    else if (stato.equals(Stato.SPENTO)) {
      turnOff();
    }
  }

}
