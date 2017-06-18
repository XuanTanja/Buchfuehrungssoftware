package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;

	public Kontenverwaltung() {
		faelle = new ArrayList<>();
		konten = new HashMap<>();

	}

	// Konto wird der HashMap hinzugef�gt
	public void addKonto(Konto myKonto) {
		konten.put(myKonto.getTitel(), myKonto);
	}
	//Gesch�ftsf�lle werden der ArrayList hinzugef�gt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}
	//dem Gesch�ftsfall wird ein Buchungssatz hinzugef�gt	
	public void addBuchungssatz(Geschaeftsfall gfall, Buchungssatz bsatz) {
		gfall.addBuchung(bsatz);
		konten.get(bsatz.getSollKonto()).buchung(bsatz, true);
		konten.get(bsatz.getHabenKonto()).buchung(bsatz, false);
	}

	public void removeGeschaeftsfall() {

	}

	public void removeBuchungssatz() {

	}
	
	public void kontensaldierung(){
		Iterator<Konto> it = konten.values().iterator();
		while (it.hasNext()) {
			Konto konto = it.next();
			konto.saldieren();
		}
	}

}
