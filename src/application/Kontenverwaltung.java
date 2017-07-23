package application;

import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import geschaeftsfall.*;
import konten.*;

public class Kontenverwaltung {

	private HashMap<String, Konto> konten;
	private ArrayList<Geschaeftsfall> faelle;
	private File speicherort;
	private LocalDate geschaeftsjahrBeginn;

	public Kontenverwaltung() {
		faelle = new ArrayList<>();
		konten = new HashMap<>();
	}

	public Kontenverwaltung(File file, ArrayList<Konto> kontenListe, LocalDate gfBeginn) {
		speicherort = file;
		faelle = new ArrayList<>();
		konten = new HashMap<>();
		geschaeftsjahrBeginn = gfBeginn;
		for (Konto konto : kontenListe) {
			konten.put(konto.getKuerzel(), konto);
		}
	}
	
	public Kontenverwaltung(File file, HashMap<String, Konto> kontenListe, ArrayList<Geschaeftsfall> faelle, LocalDate gfBeginn) {
		speicherort = file;
		faelle = new ArrayList<>(faelle);
		konten = new HashMap<>(kontenListe);
		geschaeftsjahrBeginn = gfBeginn;
	}

	// Konto wird der HashMap hinzugefügt
	public void addKonto(Konto myKonto) {
		konten.put(myKonto.getKuerzel(), myKonto);
	}
	//Geschäftsfälle werden der ArrayList hinzugefügt
	public void addGeschaeftsfall(Geschaeftsfall myGFall) {
		faelle.add(myGFall);
	}
	//dem Geschäftsfall wird ein Buchungssatz hinzugefügt	
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

	public Iterator<Konto> getKontenIterator() {
		return konten.values().iterator();
	}
	
	public HashMap<String, Konto> getKonten() {
		return konten;
	}

	public ArrayList<Geschaeftsfall> getFaelle() {
		return faelle;
	}

	public File getSpeicherort() {
		return speicherort;
	}

	public LocalDate getGeschaeftsjahrBeginn() {
		return geschaeftsjahrBeginn;
	}
}
