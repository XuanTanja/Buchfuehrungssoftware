package konten;

import java.io.Serializable;
/**
 * Die Klasse Abschlusskonto repr�sentiert Konten, die zuletzt abgeschlossen werden.
 */
public class Abschlusskonto extends Konto implements Serializable{
	
	private static final int KONTENART_ID = 4;

	public Abschlusskonto(String titel, String kuerzel, String verrechnungskonto) {
		super(titel, kuerzel, verrechnungskonto);
	}

	@Override
	public int getKontoart() {
		return KONTENART_ID;
	}

	@Override
	public String description() {
		return getTitel() + " (" + getKuerzel() + ") " + "ist ein Abschlusskonto.";
	}

}
