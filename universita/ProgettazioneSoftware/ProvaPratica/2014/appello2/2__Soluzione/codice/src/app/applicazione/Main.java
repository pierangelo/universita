package app.applicazione;

import javax.swing.SwingUtilities;

import app.gui.*;


public class Main {

	private Main() {}

	public static void main(String[] args) {

		Runnable target = new Runnable() {

			@Override
			public void run() {
				new FinestraPrincipale();
			}
		};
		SwingUtilities.invokeLater(target);

	}
}
