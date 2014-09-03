package app.gui;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class Text extends Figure {
  private String text;
  private Point p1 = null;
  private Font font;

  public Text(String text, Point p) {
    this.text = text;
    p1 = p;
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.lineColor);
    g2d.setStroke(new BasicStroke(this.lineSize));
    if (font != null)
      g2d.setFont(font);
    g2d.drawString(text, (float) p1.getX(), (float) p1.getY());

  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Point getPoint() {
    return (Point) p1.clone();
  }

  public void setPoint(Point p1) {
    this.p1 = p1;
  }

  public boolean containsPoint(Point p) {
    return false;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(Font font) {
    this.font = font;
  }

}
