package app.gui;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Figure {
  private Point p1 = null;
  private Point p2 = null;
  private Line2D.Double shape = null;

  private void createShape() {
    if (p1 != null && p2 != null)
      shape = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    else
      shape = null;
  }

  public Line(Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
    createShape();
  }

  public void draw(Graphics2D g2d) {
    if (shape != null) {
      g2d.setColor(this.lineColor);
      g2d.setStroke(new BasicStroke(this.lineSize));
      g2d.draw(shape);
    }
  }

  public Point getPoint1() {
    return (Point) p1.clone();
  }

  public void setPoint1(Point p1) {
    this.p1 = p1;
    createShape();
  }

  public Point getPoint2() {
    return (Point) p2.clone();
  }

  public void setPoint2(Point p2) {
    this.p2 = p2;
    createShape();
  }

  public boolean containsPoint(Point p) {
    return (shape.contains(p.getX(), p.getY()));
  }

}
