package officina;

public class TestOfficina {

	public static void main(String[] args) {
		Bicicletta b1=new Bicicletta("Atala", "Modello1", "Blu");
		Bicicletta b2=new Bicicletta("Atala", "Modello1", "Blu");
		Bicicletta b3=new Bicicletta("Atala", "Modello1", "Blu");
		Auto a1=new Auto("Alfa Romeo","147","Grigia","CC194MS");
		Officina o=new Officina("01234567");
		Camion c1=new Camion("Marca","Modello","Nero",true);
		o.arrivaMezzo(c1);
		System.out.println("Il camion C1 ha "+c1.getNumeroRuote()+" ruote");
		o.arrivaMezzo(b1);
		o.arrivaMezzo(b2);
		o.arrivaMezzo(b3);
		o.arrivaMezzo(a1);
		o.approntaMezzo(b1);
		o.approntaMezzo(b3);
		if (o.estInRiparazione(a1))
			System.out.println("La auto con targa "+a1.getTarga()+" e' in riparazione");
		else
			System.out.println("La auto con targa "+a1.getTarga()+" non e' in riparazione");
		if (o.estInRiparazione(b1))
			System.out.println("La bicicletta B1 e' in riparazione");
		else
			System.out.println("La bicicle B1 non e' in riparazione");	
		o.parteMezzo(b2); //Normale che lanci una eccezione giacche' le precondizioni sono violate
	}

}
