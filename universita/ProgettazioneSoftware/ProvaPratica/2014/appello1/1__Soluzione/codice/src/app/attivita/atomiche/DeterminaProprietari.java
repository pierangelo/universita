package app.attivita.atomiche;

import java.util.HashSet;
import java.util.Set;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Condominio;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Proprietario;
import app.dominio.TipoLinkInclude;
import app.dominio.TipoLinkPossiede;

public class DeterminaProprietari implements Task {

	private boolean eseguita = false;
	
	private Condominio condominio;
	private Set<Proprietario> proprietari;
	
	public DeterminaProprietari(Condominio condominio) {
		this.condominio = condominio;
		proprietari = new HashSet<Proprietario>();
	}

	@Override
	public void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true; 
		
		Set<TipoLinkInclude> linkInclude = null;
		try {
			linkInclude = condominio.getLinkInclude();
		} catch (EccezioneMoltMinMax e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Set<Proprietario> insiemeProprietari = new HashSet<Proprietario>();
		Set<TipoLinkPossiede> linkPossiede = null;
		for (TipoLinkInclude link : linkInclude) {
			try {
				linkPossiede = link.getImmobile().getLinkPossiede();
			} catch (EccezioneMoltMinMax e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (TipoLinkPossiede linkP : linkPossiede) {
				insiemeProprietari.add(linkP.getProprietario());
			}
		}
		Set<TipoLinkPossiede> insiemeLinkPossiede = null;
		TipoLinkInclude linkTipoInclude = null;
		Condominio ilCondominio;
		for (Proprietario proprietario : insiemeProprietari) {
			try {
				insiemeLinkPossiede = proprietario.getLinkPossiede();
			} catch (EccezioneMoltMinMax e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (TipoLinkPossiede linkTipoPossiede : insiemeLinkPossiede) {
				try {
					linkTipoInclude = linkTipoPossiede.getImmobile().getLinkInclude();
				} catch (EccezioneMoltMinMax e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ilCondominio = linkTipoInclude.getCondominio();
				if(!ilCondominio.equals(condominio)) {
					proprietari.add(proprietario);
				}
			}
		}
		
	}
	
	public synchronized Set<Proprietario> getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return proprietari;
	}
	
}
