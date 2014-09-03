package app.applicazione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import app.attivita.complesse.AttivitaPrincipale;
import app.dominio.Abitazione;
import app.dominio.Condominio;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Negozio;
import app.dominio.Proprietario;
import app.dominio.SpesaOrdinaria;
import app.dominio.SpesaStraordinaria;
import app.dominio.TipoLinkInclude;
import app.dominio.TipoLinkPossiede;
import app.dominio.TipoLinkSostiene;
import app.dominio.Ufficio;

public class Main {

	private Main() {}

	public static void main(String[] args) {

		Set<Condominio> condomini = new HashSet<Condominio>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.ITALIAN);

		Condominio c1 = new Condominio("Le Betulle", "Via Roma 25");

		Abitazione a1 = new Abitazione(100, 1, 0);
		a1.setMillesimi(300);
		a1.setNumeroVani(4);

		Abitazione a2 = new Abitazione(100, 2, 0);
		a2.setMillesimi(300);
		a2.setNumeroVani(4);

		Abitazione a3 = new Abitazione(90, 3, 1);
		a3.setMillesimi(250);
		a3.setNumeroVani(3);

		Abitazione a4 = new Abitazione(80, 4, 1);
		a4.setMillesimi(150);
		a4.setNumeroVani(3);

		Proprietario p1 = new Proprietario("Mario", "Rossi", "cfMR");
		try {
			a1.inserisciLinkPossiede(new TipoLinkPossiede(p1, a1, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p2 = new Proprietario("Giuseppe", "Verdi", "cfGV");
		try {
			a2.inserisciLinkPossiede(new TipoLinkPossiede(p2, a2, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p3 = new Proprietario("Giovanni", "Gialli", "cfGG");
		try {
			a3.inserisciLinkPossiede(new TipoLinkPossiede(p3, a3, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p4 = new Proprietario("Stefano", "Neri", "cfSN");
		try {
			a4.inserisciLinkPossiede(new TipoLinkPossiede(p4, a4, 50));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p5 = new Proprietario("Anna", "Neri", "cfAN");
		try {
			a4.inserisciLinkPossiede(new TipoLinkPossiede(p5, a4, 50));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			c1.inserisciLinkInclude(new TipoLinkInclude(c1, a1));
			c1.inserisciLinkInclude(new TipoLinkInclude(c1, a2));
			c1.inserisciLinkInclude(new TipoLinkInclude(c1, a3));
			c1.inserisciLinkInclude(new TipoLinkInclude(c1, a4));
		} catch (EccezionePrecondizioni e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//spese ordinarie
		try {
			//2010
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2010, "Pulizie")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 1500, 2010, "Giardiniere")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2010, "Amministratore")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2500, 2010, "Ascensore")));
			//2011
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 1500, 2011, "Pulizie")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2011, "Giardiniere")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2011, "Amministratore")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2011, "Ascensore")));
			//2012
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 1500, 2012, "Pulizie")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2500, 2012, "Giardiniere")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2012, "Amministratore")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2012, "Ascensore")));
			//2013
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 1500, 2013, "Pulizie")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2500, 2013, "Giardiniere")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2013, "Amministratore")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2013, "Ascensore")));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaOrdinaria(UUID.randomUUID().toString(), 800, 2013, "Pannelli Solari")));

		} catch (EccezionePrecondizioni e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//spese straordinarie
		try {
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaStraordinaria(UUID.randomUUID().toString(), 5000, 2010, "Disinfestazione", dateFormat.parse("11/05/2010"))));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaStraordinaria(UUID.randomUUID().toString(), 1000, 2011, "Verniciatura cancello", dateFormat.parse("11/05/2011"))));
			c1.inserisciLinkSostiene(new TipoLinkSostiene(c1, new SpesaStraordinaria(UUID.randomUUID().toString(), 10000, 2012, "Installazione pannelli solari", dateFormat.parse("11/05/2012"))));
		} catch (EccezionePrecondizioni e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		condomini.add(c1);



		Condominio c2 = new Condominio("I Pini", "Via Aosta 2");

		Negozio n21 = new Negozio(250, 1, 0);
		n21.setMillesimi(200);

		Negozio n22 = new Negozio(250, 2, 0);
		n22.setMillesimi(200);

		Negozio n23 = new Negozio(100, 3, 0);
		n23.setMillesimi(150);

		Negozio n24 = new Negozio(50, 4, 0);
		n24.setMillesimi(50);

		Abitazione a21 = new Abitazione(100, 5, 1);
		a21.setMillesimi(100);
		a21.setNumeroVani(4);

		Abitazione a22 = new Abitazione(50, 6, 1);
		a22.setMillesimi(50);
		a22.setNumeroVani(2);

		Abitazione a23 = new Abitazione(50, 7, 1);
		a23.setMillesimi(50);
		a23.setNumeroVani(2);

		Ufficio u21 = new Ufficio(200, 8, 2);
		u21.setMillesimi(200);


		Proprietario p6 = new Proprietario("Andrea", "Bianchi", "cfAB");
		try {
			n21.inserisciLinkPossiede(new TipoLinkPossiede(p6, n21, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			n22.inserisciLinkPossiede(new TipoLinkPossiede(p1, n22, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p7 = new Proprietario("Francesco", "Alti", "cfFG");
		try {
			n23.inserisciLinkPossiede(new TipoLinkPossiede(p7, n23, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p8 = new Proprietario("Luca", "Bassi", "cfLB");
		try {
			n24.inserisciLinkPossiede(new TipoLinkPossiede(p8, n24, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			a21.inserisciLinkPossiede(new TipoLinkPossiede(p6, a21, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			a22.inserisciLinkPossiede(new TipoLinkPossiede(p6, a22, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p9 = new Proprietario("Paolo", "Medi", "cfPM");
		try {
			a23.inserisciLinkPossiede(new TipoLinkPossiede(p9, a23, 100));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Proprietario p10 = new Proprietario("Sandro", "Forti", "cfSF");
		try {
			u21.inserisciLinkPossiede(new TipoLinkPossiede(p10, u21, 70));
			u21.inserisciLinkPossiede(new TipoLinkPossiede(p2, u21, 30));
		} catch (EccezionePrecondizioni e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, n21));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, n22));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, n23));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, n24));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, a21));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, a22));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, a23));
			c2.inserisciLinkInclude(new TipoLinkInclude(c2, u21));
		} catch (EccezionePrecondizioni e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//spese ordinarie
		try {
			//2011
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2500, 2011, "Pulizie")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2750, 2011, "Giardiniere")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 4000, 2011, "Amministratore")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2011, "Ascensore")));
			//2012
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2600, 2012, "Pulizie")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2012, "Giardiniere")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 3900, 2012, "Amministratore")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2050, 2012, "Ascensore")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 800, 2012, "Pannelli Solari")));
			//2013
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2400, 2013, "Pulizie")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 3000, 2013, "Giardiniere")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 4000, 2013, "Amministratore")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 2000, 2013, "Ascensore")));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaOrdinaria(UUID.randomUUID().toString(), 800, 2013, "Pannelli Solari")));

		} catch (EccezionePrecondizioni e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//spese straordinarie
		try {
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaStraordinaria(UUID.randomUUID().toString(), 15000, 2011, "Rifacimento facciata", dateFormat.parse("03/04/2011"))));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaStraordinaria(UUID.randomUUID().toString(), 5000, 2012, "Sostituzione portone", dateFormat.parse("04/03/2012"))));
			c2.inserisciLinkSostiene(new TipoLinkSostiene(c2, new SpesaStraordinaria(UUID.randomUUID().toString(), 4000, 2013, "Riparazione citofoni", dateFormat.parse("11/11/2013"))));
		} catch (EccezionePrecondizioni e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		condomini.add(c2);

		Thread threadAttivitaPrincipale = new Thread(new AttivitaPrincipale(condomini));
		threadAttivitaPrincipale.start();
	}
}
