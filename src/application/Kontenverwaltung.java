package application;

import java.util.HashMap;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;

	public Kontenverwaltung() {

	}

	// Konto wird der HashMap hinzugef�gt
	private void addKonto(Konto myKonto) {
		konten.put(myKonto.getTitel(), myKonto);
	}
	//Gesch�ftsf�lle werden der ArrayList hinzugef�gt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}
	//dem Gesch�ftsfall wird ein Buchungssatz hinzugef�gt	
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.addBuchung(bsatz);
	}

	public void removeGeschaeftsfall() {

	}

	public void removeBuchungssatz() {

	}

}
