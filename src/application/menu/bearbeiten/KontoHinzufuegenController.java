package application.menu.bearbeiten;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import konten.Bestandskonto;
import konten.Erfolgskonto;
import konten.Konto;
import utility.alertDialog.AlertDialogFrame;

public class KontoHinzufuegenController implements Initializable {

	@FXML
	private TextField textfieldKontenname;

	@FXML
	private TextField textfieldKuerzel;

	@FXML
	private Button buttonAddKonto;

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

	//werden �bergeben
	private ObservableList<String> kontoListe;
	private ObservableList<Konto> kontenListe;
	
	//nur die neuen Konten
	private  ObservableList<Konto> neueKonten;
	
	private boolean kontenErstellt;

	@FXML
	void handle_KontoHinzufuegen(ActionEvent event) {
		String fehlermeldung = "";
		if (textfieldKuerzel.getText().length() > 6 || textfieldKuerzel.getText().length() == 0) {
			fehlermeldung += "- Das K�rzel ist bez�glich seiner L�nge ung�ltig\n";
		}
		if (textfieldKontenname.getText().length() == 0) {
			fehlermeldung += "- Keinen Kontonamen angegeben\n";
		}
		if (radioErfolgskonto.isSelected() && verrechnungskonto.getSelectionModel().isEmpty()) {
			fehlermeldung += "- Bitte geben Sie ein Verrechnungskonto f�r das Konto an\n";
		}
		// Fehler�berpr�fung abgeschlossen
		if (fehlermeldung.equals("")) {
			// Anfangsbestand ist bei dem sp�teren Hinzuf�gen von Bestandskonten = 0
			if (radioBestandskonto.isSelected()) {
				Bestandskonto newBKonto = new Bestandskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						"SBK", 0, radioAktivkonto.isSelected());
				kontenListe.add(newBKonto);
				neueKonten.add(newBKonto);
			} else if (radioErfolgskonto.isSelected()) {
				Erfolgskonto newEKonto = new Erfolgskonto(textfieldKontenname.getText(), textfieldKuerzel.getText(),
						verrechnungskonto.getValue(), radioErtragskonto.isSelected());
				kontenListe.add(newEKonto);
				neueKonten.add(newEKonto);
			}
			new AlertDialogFrame().showConfirmDialog("\"" + textfieldKontenname.getText() + "\" hinzugef�gt!",
					"Das Konto wurde erfolgreich angelegt.", "Ok", AlertDialogFrame.INFORMATION_TYPE);
			textfieldKontenname.setText("");
			textfieldKuerzel.setText("");
			kontenErstellt = true;
			
		} else {
			new AlertDialogFrame().showConfirmDialog(
					"Das Konto \"" + textfieldKontenname.getText()
							+ "\" konnte aus folgenden Gr�nden nicht hinzugef�gt werden:",
					fehlermeldung, "Ok", AlertDialogFrame.WARNING_TYPE);
		}
		

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		kontoListe = FXCollections.observableArrayList();
		neueKonten= FXCollections.observableArrayList();
		
		ToggleGroup group1 = new ToggleGroup();
		ToggleGroup group2 = new ToggleGroup();
		ToggleGroup group3 = new ToggleGroup();

		radioBestandskonto.setToggleGroup(group1);
		radioErfolgskonto.setToggleGroup(group1);
		radioErtragskonto.setToggleGroup(group2);
		radioAufwandskonto.setToggleGroup(group2);
		radioAktivkonto.setToggleGroup(group3);
		radioPassivkonto.setToggleGroup(group3);
		
		radioBestandskonto.setOnAction(e -> {
			radioAufwandskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioErtragskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioAktivkonto.setDisable(!(radioBestandskonto.isSelected()));
			radioPassivkonto.setDisable(!(radioBestandskonto.isSelected()));
		});
		
		radioErfolgskonto.setOnAction(e -> {
			radioAufwandskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioErtragskonto.setDisable(!(radioErfolgskonto.isSelected()));
			radioAktivkonto.setDisable(!(radioBestandskonto.isSelected()));
			radioPassivkonto.setDisable(!(radioBestandskonto.isSelected()));
		});
	}

	public void setKonten(ObservableList<Konto> kntList) {

		kontenListe = kntList;

		// ObservableList<Konto> zu ObservableList<String> mithilfe Iterator
		Iterator<Konto> iter = kntList.iterator();
		int x = 0;
		while (iter.hasNext()) {
			kontoListe.add(kntList.get(x).getKuerzel());
			x++;
			iter.next();
		}
		// ComboBox verrechnungsKonto werden die bereits bestehenden Konten �bergeben
		verrechnungskonto.setItems((ObservableList<String>) FXCollections.observableList(kontoListe));

	}
	
	public  ObservableList<Konto> getNeueKonten(){
		return neueKonten;
	}
	
	public boolean isNeueKontenErstellt() {
		return kontenErstellt;
	}

}
