package konten;

import java.io.Serializable;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import konten.gui.KontoContainer;

/**
 * Die Klasse Bestandskonto repr�sentiert ein Konto mit Anfangsbest�nden.
 */
public class Bestandskonto extends Konto implements Serializable{

	private static final int KONTENART_ID = 1;
	private double anfangsbestand;
	private boolean aktivkonto;

	public Bestandskonto(String titel, String kuerzel, String verrechnungskonto, double anfangsbestand,
			boolean aktivkonto) {
		super(titel, kuerzel, verrechnungskonto);
		this.anfangsbestand = anfangsbestand;
		this.aktivkonto = aktivkonto;
		setBeschreibung(description());
	}

	/**
	 * <i><b>Ausgabe der Anfangsbest�nde</b></i><br>
	 * <br>
	 * Die Anfangsbest�nde werden auf der GUI ausgegeben.  <br>
	 * 
	 */
	public void confirmAB() {
		if (isAktivkonto()) {
			getGuiContainer().getRefNameS().getChildren().add(new Label("AB"));
			getGuiContainer().getRefBetragS().getChildren().add(new Label(anfangsbestand + "�"));
		} else {
			getGuiContainer().getRefNameH().getChildren().add(new Label("AB"));
			getGuiContainer().getRefBetragH().getChildren().add(new Label(anfangsbestand + "�"));
		}
	}

	/**
	 * <i><b>Vergabe der Beschreibung</b></i><br>
	 * <br>
	 * Es wird eine Beschreibung je ach Kontoart vergeben. <br>
	 *
	 * @return Beschreibung f�r das Konto
	 */
	@Override
	public String description() {
		String beschreibung = "";
		if (isAktivkonto()) {
			beschreibung += "aktives ";
		} else {
			beschreibung += "pasives ";
		}
		beschreibung += "Bestandskonto; Anfangsbestand: " + getAnfangsbestand() + "; " + getTitel() + " ("
				+ getKuerzel() + ") wird in das Konto " + getVerrechnungKonto() + " saldiert.";
		return beschreibung;
	}
	
	/**
	 * <i><b>Neuer Container f�r das Bestandskonto</b></i><br>
	 * <br>
	 * Die Benutzeroberfl�che des Kontos wird neu erstellt. Wird f�r das �ffnen
	 * einer Bilanz ben�tigt. Es wird erstellt f�r die eingegebenen Anfangsbestand.  <br>
	 * 
	 */
	@Override
	public void newContainer() {
		setGuiContainer(new KontoContainer(getTitel()));
		confirmAB();
		for (Buchungssatz bsatz : getSollSeite().getArrayOfBuchungen()) {
			addBuchungssatzToContainer(bsatz, true);
		}
		for (Buchungssatz bsatz : getHabenSeite().getArrayOfBuchungen()) {
			addBuchungssatzToContainer(bsatz, false);
		}
	}
	
	public double getAnfangsbestand() {
		return anfangsbestand;
	}

	public void setAnfangsbestand(double anfangsbestand) {
		this.anfangsbestand = anfangsbestand;
	}

	public boolean isAktivkonto() {
		return aktivkonto;
	}

	public void setAktivkonto(boolean aktivkonto) {
		this.aktivkonto = aktivkonto;
	}

	public int getKontoart() {
		return KONTENART_ID;
	}
	
	

}
