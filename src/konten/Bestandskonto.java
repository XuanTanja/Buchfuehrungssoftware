package konten;

import java.io.Serializable;

import geschaeftsfall.Buchungssatz;
import javafx.scene.control.Label;
import konten.gui.KontoContainer;

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

	public void confirmAB() {
		if (isAktivkonto()) {
			getGuiContainer().getRefNameS().getChildren().add(new Label("AB"));
			getGuiContainer().getRefBetragS().getChildren().add(new Label(anfangsbestand + "�"));
		} else {
			getGuiContainer().getRefNameH().getChildren().add(new Label("AB"));
			getGuiContainer().getRefBetragH().getChildren().add(new Label(anfangsbestand + "�"));
		}
	}

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
