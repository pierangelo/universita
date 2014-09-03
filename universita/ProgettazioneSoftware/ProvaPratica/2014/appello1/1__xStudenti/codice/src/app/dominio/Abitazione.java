package app.dominio;

public class Abitazione extends Immobile {
	
	private int numeroVani;

	public Abitazione(int metriQuadri, int interno, int piano) {
		super(metriQuadri, interno, piano);
	}

	public int getNumeroVani() {
		return numeroVani;
	}

	public void setNumeroVani(int numeroVani) {
		this.numeroVani = numeroVani;
	}
	
}
