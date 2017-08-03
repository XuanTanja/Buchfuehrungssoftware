package application.menu.bearbeiten;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import konten.Konto;
import utility.alertDialog.AlertDialogFrame;

/**
 * FXML Controller class
 *
 * @author tmanlik
 */
public class UebersichtanzeigenController implements Initializable {
	/**
	 * Initializes the controller class.
	 */
	private ArrayList<Geschaeftsfall> faelle;
	@FXML
	private ComboBox<String> CB_GF_auswaehlen;

	@FXML
	private Button BTN_alle_GF_anzeigen;

	@FXML
	private Button handle_btnCheck;

	@FXML
	private VBox VBox_Anzeige;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	void handle_alle_GF_anzeigen(ActionEvent event) {
		
		VBox_Anzeige.getChildren().clear();

		for (int i = 0; i < faelle.size(); i++) {
			Geschaeftsfall fall = faelle.get(i);
			// Titel der Gesch�ftsf�lle in Label speichern
			Label lb1 = new Label(fall.getID() + ". " + fall.getTitel());
			lb1.setFont(Font.font("System", FontWeight.BOLD, 18));
			// Beschreibung der Gesch�ftsf�lle in Label speichern
			Label lb2 = new Label(fall.getBeschreibung());

			lb2.setWrapText(true);
			VBox_Anzeige.getChildren().add(lb1);
			VBox_Anzeige.getChildren().add(lb2);

			// Informationen zu den BS ausgeben:
			ArrayList<Buchungssatz> neuListe = fall.getSaetze();
			for (int j = 0; j <= neuListe.size() - 1; j++) {

				Label lb3 = new Label(neuListe.get(j).getSollKonto() + " an " + neuListe.get(j).getHabenKonto() + " "
						+ neuListe.get(j).getBetrag() + " �");

				VBox_Anzeige.getChildren().add(lb3);
			}
		}

	}

	@FXML
	void handle_btnCheck(ActionEvent event){
		VBox_Anzeige.getChildren().clear();
			//new AlertDialogFrame().showConfirmDialog("Fehlerhafter Buchungssatz","Die Betr�ge der Soll- und Habenseite stimmen in der Summe nicht �berein! \nBitte �berpr�fen Sie Ihre Eingabe.","OK", AlertDialogFrame.WARNING_TYPE);
		String auswahl = CB_GF_auswaehlen.getValue();
		for (int i = 0; i < faelle.size(); i++) {
			Geschaeftsfall fall = faelle.get(i);
			if(faelle.get(i).getTitel().equals(auswahl)){
				
				Label lb1 = new Label(fall.getID() + ". " + fall.getTitel());
				lb1.setFont(Font.font("System", FontWeight.BOLD, 18));
				// Beschreibung der Gesch�ftsf�lle in Label speichern
				Label lb2 = new Label(fall.getBeschreibung());

				lb2.setWrapText(true);
				VBox_Anzeige.getChildren().add(lb1);
				VBox_Anzeige.getChildren().add(lb2);

				// Informationen zu den BS ausgeben:
				ArrayList<Buchungssatz> neuListe = fall.getSaetze();
				for (int j = 0; j <= neuListe.size() - 1; j++) {

					Label lb3 = new Label(neuListe.get(j).getSollKonto() + " an " + neuListe.get(j).getHabenKonto() + " "
							+ neuListe.get(j).getBetrag() + " �");

					VBox_Anzeige.getChildren().add(lb3);
				}
			}
		}
	}

	public void setFaelle(ArrayList<Geschaeftsfall> gfall) {
		faelle = gfall;

		ArrayList<String> geschaeftsfaelle = new ArrayList<>();

		for (Geschaeftsfall fall : faelle) {
			geschaeftsfaelle.add(fall.getTitel());
		}
		CB_GF_auswaehlen.setItems((ObservableList<String>) FXCollections.observableArrayList(geschaeftsfaelle));
	}

}
