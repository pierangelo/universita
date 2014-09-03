package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBattelloView  implements ActionListener {

  private BattelloView finestra;

  public ListenerBattelloView(BattelloView frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {

    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }

  }
}
