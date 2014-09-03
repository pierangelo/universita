package app.gui;

import javax.swing.JOptionPane;

public class ErrorNotifier {
  
  private ErrorNotifier() {}
  
  public static void notifyError(String message) {
    JOptionPane.showMessageDialog(null, message, "Errore", JOptionPane.ERROR_MESSAGE);
  }
}
