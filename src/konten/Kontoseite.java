package konten;

import java.util.HashMap;

import geschaeftsfall.Buchungssatz;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Kontoseite {
	
	private boolean sollseite;
	private HBox container;
	private VBox refName;
	private VBox refBetrag;
	private HashMap<String, Buchungssatz> buchungen;
	// String steht doch hier f�r Kontotitel. Es k�nnnen doch f�r ein Konto mehrere Buchungss�tze existieren?! Warum dann schl�ssel?
	
	private void aktualisieren(){
	
	}
	
	//zB buchungen.getBetragssumme(..
	private double getBetragssumme(Konto myKonto){
		//String titel = myKonto.getTitel();
		//buchungen.get(titel).getBetrag();
		return 0;
	}
	
}
