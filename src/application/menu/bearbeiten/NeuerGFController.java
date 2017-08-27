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
	
	// Event Listener on Button[#buttonHinzuf�gen].onAction
	@FXML
	public void handleHinzuf�gen(ActionEvent event) {
		((Stage) buttonHinzuf�gen.getScene().getWindow()).close();
	}

	// Event Listener on Button[#buttonSchliessen].onAction
	@FXML
	public void handleSchlie�en(ActionEvent event) {
		closeButtonPressed = true;
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}

	public Geschaeftsfall getGeschaeftsfall(int ID) {
		return new Geschaeftsfall(ID, textfieldTitel.getText(), textfieldBeschreibung.getText());
	}

	public boolean isCloseButtonPressed() {
		return closeButtonPressed;
	}
}
