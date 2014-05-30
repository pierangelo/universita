package app;

public class Application {

	public static void main(String args[]) {
		//APRE DUE FINESTRE CON LO STESSO LOOK-AND-FEEL MA IMPLEMENTATE IN MODO DIFFERENTE
		app.gui.display.Display finestra = new app.gui.display.Display();
		app.gui.displayAdv.Display finestra2 = new app.gui.displayAdv.Display();
	}
}