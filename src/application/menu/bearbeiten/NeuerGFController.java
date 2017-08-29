package application.menu.bearbeiten;

import java.net.URL;
import java.util.ResourceBundle;

import geschaeftsfall.Geschaeftsfall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NeuerGFController implements Initializable {
	private boolean closeButtonPressed;
	@FXML
	private TextField textfieldTitel;
	@FXML
	private TextArea textfieldBeschreibung;
	@FXML
	private Button buttonHinzuf�gen;
	@FXML
	private Button buttonSchliessen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		closeButtonPressed = false;
	}
	/**
	 * <i><b>Ereignisbehandlung: bei Hinzuf�gen Gesch�ftsfall</b></i><br>
	 * <br>
	 * Schlie�en des Fensters bei Button-Event zum Hinzuf�gen..
	 * Event Listener on Button[#buttonHinzuf�gen].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handleHinzuf�gen(ActionEvent event) {
		((Stage) buttonHinzuf�gen.getScene().getWindow()).close();
	}
	/**
	 * <i><b>Ereignisbehandlung: Fenster Schlie�en</b></i><br>
	 * <br>
	 * Schlie�en des Fensters bei Button-Event zum Schlie�en.
	 * Event Listener on Button[#buttonSchliessen].onAction <br>
	 * 
	 * @param event
	 * 			- Nutzeraktion
	 */
	@FXML
	public void handleSchlie�en(ActionEvent event) {
		closeButtonPressed = true;
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}

	/**
	 * <i><b>Erstellen eines neuen Gesch�ftsfalles</b></i><br>
	 * <br>
	 * Mit der �bergebenen ID und den Nutzereingaben wird ein neuer Gesch�ftsfall erstellt. <br>
	 * 
	 * @param ID
	 * 			- die ID, die der Gesch�ftsfall erh�lt
	 * @return ein neuer Gesch�ftfall
	 */
	public Geschaeftsfall getGeschaeftsfall(int ID) {
		return new Geschaeftsfall(ID, textfieldTitel.getText(), textfieldBeschreibung.getText());
	}

	public boolean isCloseButtonPressed() {
		return closeButtonPressed;
	}
}
