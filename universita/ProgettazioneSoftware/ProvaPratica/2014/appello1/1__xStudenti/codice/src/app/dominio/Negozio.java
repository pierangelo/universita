package app.dominio;

public class Negozio extends Immobile {

	private String nome;
	
	public Negozio(int metriQuadri, int interno, int piano) {
		super(metriQuadri, interno, piano);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
