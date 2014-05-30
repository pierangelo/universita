package officina;

public class Auto extends Mezzo 
{
	private final String targa;
	
	public Auto(String unaMarca, String unModello, String unColore,String unaTarga) 
	{
		super(unaMarca, unModello, unColore);
		this.targa=unaTarga;
	}
	
	public String getTarga()
	{
		return targa;
	}

	public int getNumeroRuote() 
	{
		return 4;
	}

}
