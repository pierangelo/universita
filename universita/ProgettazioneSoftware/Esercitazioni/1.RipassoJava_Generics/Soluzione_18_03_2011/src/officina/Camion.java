package officina;

public class Camion extends Mezzo 
{
	public boolean haRimorchio() {
		return haRimorchio;
	}

	private final boolean haRimorchio;

	public Camion(String unaMarca, String unModello, String unColore, 
			boolean rimorchio) 
	{

		super(unaMarca, unModello, unColore);
		haRimorchio=rimorchio;
	}


	@Override
	public int getNumeroRuote() {
		if (haRimorchio)
		{
			return(8);
		}
		else
			return(4);
	}

}
