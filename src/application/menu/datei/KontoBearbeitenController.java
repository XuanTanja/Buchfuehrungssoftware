package application.menu.datei;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
/**
 * 
 * KontoBearbeiten dient zum Bearbeiten eines Kontos w�hrend der Bilanzerstellung.�ber den Anfangsbestand hinaus k�nnen weitere Eigenschaften ge�ndert werden.
 *
 */
public class KontoBearbeitenController implements Initializable {
	@FXML
	private TextField textfieldKontenname;
	@FXML
	private TextField textfieldKuerzel;
	@FXML
	private Button buttonAddKonto;
	@FXML
	private TextField textfieldAB;
	@FXML
	private RadioButton radioAktivkonto;
	@FXML
	private RadioButton radioPassivkonto;
	@FXML
	private RadioButton radioErtragskonto;
	@FXML
	private RadioButton radioAufwandskonto;
	@FXML
	private RadioButton radioBestandskonto;
	@FXML
	private RadioButton radioErfolgskonto;
	@FXML
	private ComboBox<String> verrechnungskonto;

	private Konto changedKonto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup group2 = new ToggleGroup();
		ToggleGroup group3 = new ToggleGroup();

		radioErtragskonto.setToggleGroup(group2);
		radioAufwandskonto.setToggleGroup(group2);
		radioAktivkonto.setToggleGroup(group3);
		radioPassivkonto.setToggleGroup(group3);
	}
	/**
	 * <i><b>Ereignisbehandlung: Konto hinzuf�gen</b></i><br>
	 * <br>
	 * �ndern eines Kontos nach den Nutzereingaben.
	 * Schlie�en des Fensters bei Button-Event zum Hinzuf�gen.
	 * Event Listener on Button[#buttonAddKonto].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handle_KontoHinzufuegen(ActionEvent event) {
		changedKonto.setTitel(textfieldKontenname.getText());
		changedKonto.setKuerzel(textfieldKuerzel.getText());
		changedKonto.getGuiContainer().setName(textfieldKontenname.getText());

		if (radioBestandskonto.isSelected()) {
			Bestandskonto newKonto = (Bestandskonto) changedKonto;
			newKonto.setVerrechnungKonto("SBK");
			newKonto.setAnfangsbestand(Double.parseDouble(textfieldAB.getText()));
			newKonto.setAktivkonto(radioAktivkonto.isSelected());
		} else if (radioErfolgskonto.isSelected()) {
			Erfolgskonto newKonto = (Erfolgskonto) changedKonto;
			newKonto.setVerrechnungKonto(verrechnungskonto.getValue());
			newKonto.setErtragskonto(radioErtragskonto.isSelected());
		}
		((Stage) buttonAddKonto.getScene().getWindow()).close();
	}
	/**
	 * <i><b>Anzeigen eines zu �ndernden Kontos</b></i><br>
	 * <br>
	 *  Es wird das bearbeitende Konto aus den bisherigen Daten in die GUI �bertragen. Dabei wird je nach Kontoart unterschieden bei den Zust�nden der FXML-Elemente.<br>
	 * 
	 * @param konto
	 * 			- das zu bearbeitende Konto
	 * @param kontenListe
	 * 			- Liste aller Konten
	 */
	public void setChangeKonto(Konto konto, ObservableList<String> kontenListe) {
		textfieldKontenname.setText(konto.getTitel());
		textfieldKuerzel.setText(konto.getKuerzel());
		switch (konto.getKontoart()) {
		case (1):
			radioErfolgskonto.setDisable(true);
			radioErfolgskonto.setSelected(false);
			radioAufwandskonto.setDisable(true);
			radioErtragskonto.setDisable(true);
			verrechnungskonto.setDisable(true);

			Bestandskonto bkonto = (Bestandskonto) konto;
			textfieldAB.setText(bkonto.getAnfangsbestand() + "");
			if (bkonto.isAktivkonto()) {
				radioAktivkonto.setSelected(true);
			} else {
				radioPassivkonto.setSelected(true);
			}
			break;
		case (2):
			radioBestandskonto.setDisable(true);
			radioBestandskonto.setSelected(false);
			radioAktivkonto.setDisable(true);
			radioPassivkonto.setDisable(true);
			textfieldAB.setDisable(true);

			Erfolgskonto ekonto = (Erfolgskonto) konto;
			verrechnungskonto.setItems(kontenListe);
			verrechnungskonto.getSelectionModel().select(ekonto.getVerrechnungKonto());
			if (ekonto.isErtragskonto()) {
				radioErtragskonto.setSelected(true);
			} else {
				radioAufwandskonto.setSelected(true);
			}
			break;
		default:
			((Stage) buttonAddKonto.getScene().getWindow()).close();
		}
		changedKonto = konto;
	}
}
