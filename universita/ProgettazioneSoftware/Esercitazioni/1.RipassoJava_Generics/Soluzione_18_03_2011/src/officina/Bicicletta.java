package officina;

public class Bicicletta extends Mezzo 
{
	public Bicicletta(String unaMarca, String unModello, String unColore) 
	{
		super(unaMarca, unModello, unColore);
	}

	public int getNumeroRuote() 
	{
		return 2;
	}

}
