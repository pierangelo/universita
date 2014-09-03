package app.attivita.atomiche;

import java.util.Set;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Condominio;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Spesa;
import app.dominio.SpesaStraordinaria;
import app.dominio.TipoLinkSostiene;

public class CalcolaSpeseStraordinarie implements Task {

	private boolean eseguita = false;

	private final Condominio condominio;
	private final int anno;
	private double result;

	public CalcolaSpeseStraordinarie(Condominio ilCondominio, int anno) {
		condominio = ilCondominio;
		this.anno = anno;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;

		Set<TipoLinkSostiene> linkSostiene = null;
		try {
			linkSostiene = condominio.getLinkSostiene();
		} catch (EccezioneMoltMinMax e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Spesa spesa;
		for (TipoLinkSostiene link : linkSostiene) {
			spesa = link.getSpesa();
			if (spesa.getAnno() == anno && spesa.getClass().equals(SpesaStraordinaria.class) ) {
				result += spesa.getImporto();
			}
		}
	}

	public synchronized double getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}
}
