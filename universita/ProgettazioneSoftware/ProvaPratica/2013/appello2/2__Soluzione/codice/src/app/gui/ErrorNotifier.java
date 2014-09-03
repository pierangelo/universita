package app.gui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ErrorNotifier {

  private ErrorNotifier() {}

  public static void notifyError(final String message) {
    if (SwingUtilities.isEventDispatchThread()) {
      errore(message);
    }
    else {
      Runnable target = new Runnable() {
        @Override
        public void run() {
          errore(message);
        }
      };
      try {
        SwingUtilities.invokeAndWait(target);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.exit(1);
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.exit(1);
      }
    }
  }

  private static void errore(String msg) {
    JOptionPane.showMessageDialog(null, msg, "Errore", JOptionPane.ERROR_MESSAGE);
  }
}
