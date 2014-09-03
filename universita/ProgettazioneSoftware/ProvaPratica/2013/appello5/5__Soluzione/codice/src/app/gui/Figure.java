package app.gui;

import java.awt.*;

public abstract class Figure implements Cloneable {
  
  protected Color lineColor = Color.BLACK;
  protected float lineSize = 1;

  public abstract void draw(Graphics2D g2D);

  public abstract boolean containsPoint(Point p);

  public Color getLineColor() {
    return lineColor;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

  public void setLineSize(float width) {
    this.lineSize = width;
  }

  public float getLineSize() {
    return lineSize;
  }

  public Figure clone() {
    try {
      return (Figure) (super.clone());
    } catch (CloneNotSupportedException e) {
      return (null);
    }
  }
}