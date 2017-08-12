package application.menu.bearbeiten;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import konten.Konto;
import utility.alertDialog.AlertDialogFrame;
import utility.converter.TypeConverter;
import utility.map.IDMap;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import geschaeftsfall.Buchungssatz;
import geschaeftsfall.Geschaeftsfall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

public class NeuerBSController implements Initializable {

	private ObservableList<String> comboItemsList;
	private ArrayList<ComboBox<String>> comboListSoll, comboListHaben;
	private ArrayList<TextField> betragListSoll, betragListHaben;
	private ArrayList<Double> betragSoll, betragHaben;
	private IDMap<Integer, Buchungssatz> buchungss�tze;
	private int rowSoll, rowHaben;
	@FXML
	private TextField textfieldTitel;
	@FXML
	private ComboBox<String> comboSoll;
	@FXML
	private ComboBox<String> comboHaben;
	@FXML
	private TextField textfieldBetragHaben;
	@FXML
	private TextField textfieldBetragSoll;
	@FXML
	private Button buttonPlusSoll;
	@FXML
	private Button buttonPlusHaben;
	@FXML
	private ComboBox<String> comboGF;
	@FXML
	private CheckBox checkboxBetragGleichsetzen;
	@FXML
	private Button buttonHinzuf�gen;
	@FXML
	private Button buttonSchliessen;
	@FXML
	private GridPane gridpaneBS;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonPlusSoll.setOnAction(e -> handleAddRow(true));
		buttonPlusHaben.setOnAction(e -> handleAddRow(false));
		comboListSoll = new ArrayList<>();
		comboListHaben = new ArrayList<>();
		betragListSoll = new ArrayList<>();
		betragListHaben = new ArrayList<>();
		buchungss�tze = new IDMap<>();
		rowSoll = 1;
		rowHaben = 1;

		comboListSoll.add(comboSoll);
		comboListHaben.add(comboHaben);
		betragListSoll.add(textfieldBetragSoll);
		betragListHaben.add(textfieldBetragHaben);
	}

	private void handleAddRow(boolean soll) {
		ComboBox<String> combobox = new ComboBox<>(comboItemsList);
		TextField textfield = new TextField();
		Label euro = new Label();
		euro.setFont(new Font(15));
		textfield.setPromptText("Betrag");
		combobox.setPrefWidth(125);
		GridPane.setMargin(combobox, new Insets(10, 0, 0, 0));
		if (soll) {
			comboListSoll.add(combobox);
			betragListSoll.add(textfield);
			euro.setText("�  an");

			gridpaneBS.add(combobox, 0, rowSoll);
			gridpaneBS.add(textfield, 1, rowSoll);
			gridpaneBS.add(euro, 3, rowSoll++);
			gridpaneBS.getChildren().remove(buttonPlusSoll);
			gridpaneBS.add(buttonPlusSoll, 0, rowSoll);

		} else {
			comboListHaben.add(combobox);
			betragListHaben.add(textfield);
			euro.setText("�");

			gridpaneBS.add(combobox, 4, rowHaben);
			gridpaneBS.add(textfield, 5, rowHaben);
			gridpaneBS.add(euro, 7, rowHaben++);
			gridpaneBS.getChildren().remove(buttonPlusHaben);
			gridpaneBS.add(buttonPlusHaben, 4, rowHaben);
		}
	}

	public void setParameter(HashMap<String, Konto> konten, ArrayList<Geschaeftsfall> faelle) {
		comboItemsList = FXCollections
				.observableArrayList(new TypeConverter<String, Konto>().hashmapkeysToArrayList(konten));
		comboSoll.setItems(comboItemsList);
		comboHaben.setItems(comboItemsList);

		ArrayList<String> geschaeftsfaelle = new ArrayList<>();
		for (Geschaeftsfall fall : faelle) {
			geschaeftsfaelle.add(fall.getTitel());
		}
		comboGF.setItems(FXCollections.observableArrayList(geschaeftsfaelle));
	}

	// Event Listener on Button[#buttonHinzuf�gen].onAction
	@FXML
	public void handleHinzuf�gen(ActionEvent event) {
		if (exceptionhandling()) {
			putBuchungssatz(0, 0);
			test();
			resetGUI();
		}
	}

	private void resetGUI() {
		rowSoll = 1;
		rowHaben = 1;
		gridpaneBS.getChildren().removeAll(comboListSoll);
		gridpaneBS.getChildren().removeAll(comboListHaben);
		gridpaneBS.getChildren().removeAll(betragListSoll);
		gridpaneBS.getChildren().removeAll(betragListHaben);
		gridpaneBS.getChildren().removeAll(buttonPlusHaben, buttonPlusSoll, comboSoll, comboHaben, textfieldBetragSoll,
				textfieldBetragHaben);
		gridpaneBS.add(buttonPlusSoll, 0, rowSoll);
		gridpaneBS.add(buttonPlusHaben, 4, rowHaben);
		gridpaneBS.add(comboSoll, 0, 0);
		gridpaneBS.add(textfieldBetragSoll, 1, 0);
		gridpaneBS.add(comboHaben, 4, 0);
		gridpaneBS.add(textfieldBetragHaben, 5, 0);
		comboSoll.getSelectionModel().clearSelection();
		comboHaben.getSelectionModel().clearSelection();
		textfieldBetragHaben.setText("");
		textfieldBetragSoll.setText("");
		textfieldTitel.setText("");
	}

	private void putBuchungssatz(int sollPos, int habenPos) {
		double buchungsbetrag;
		String sollKonto, habenKonto;
		boolean isSollBigger = betragSoll.get(sollPos) > betragHaben.get(habenPos);
		if (isSollBigger) {
			buchungsbetrag = betragHaben.get(habenPos);
			betragSoll.set(sollPos, betragSoll.get(sollPos) - betragHaben.get(habenPos));

			sollKonto = comboListSoll.get(sollPos).getSelectionModel().getSelectedItem();
			habenKonto = comboListHaben.get(habenPos).getSelectionModel().getSelectedItem();
			habenPos++;

		} else {
			buchungsbetrag = betragSoll.get(sollPos);
			betragHaben.set(habenPos, betragHaben.get(habenPos) - betragSoll.get(sollPos));

			sollKonto = comboListSoll.get(sollPos).getSelectionModel().getSelectedItem();
			habenKonto = comboListHaben.get(habenPos).getSelectionModel().getSelectedItem();
			sollPos++;
		}

		Buchungssatz bs = new Buchungssatz(textfieldTitel.getText(), sollKonto, habenKonto, buchungsbetrag);
		buchungss�tze.put(comboGF.getSelectionModel().getSelectedIndex(), bs);
		if (sollPos == rowSoll - 1 && habenPos == rowHaben - 1) {
			Buchungssatz bs2 = new Buchungssatz(textfieldTitel.getText(),
					comboListSoll.get(sollPos).getSelectionModel().getSelectedItem(),
					comboListHaben.get(habenPos).getSelectionModel().getSelectedItem(), betragSoll.get(sollPos));
			buchungss�tze.put(comboGF.getSelectionModel().getSelectedIndex(), bs2);
		} else if (!(rowSoll == 1 && rowHaben == 1)) {
			putBuchungssatz(sollPos, habenPos);
		}
	}

	private void test() {
		System.out.println("---------------------------------------------------");
		Iterator<ArrayList<Buchungssatz>> it = buchungss�tze.values().iterator();
		while (it.hasNext()) {
			ArrayList<Buchungssatz> blist = it.next();
			for (Buchungssatz b : blist) {
				System.out.println("Buchungssatz:  " + b.getSollKonto() + " an " + b.getHabenKonto() + "  "
						+ b.getBetrag() + " �");
			}
		}
		System.out.println("---------------------------------------------------");
	}

	private ArrayList<Double> toDoubleArraylist(ArrayList<TextField> list) throws NumberFormatException {
		ArrayList<Double> doublearraylist = new ArrayList<>();
		for (TextField tf : list) {
			doublearraylist.add(Double.parseDouble(tf.getText()));
		}
		return doublearraylist;
	}

	private double sumOfValues(ArrayList<Double> list) {
		double sum = 0;
		for (double value : list) {
			sum += value;
		}
		return sum;
	}

	private boolean exceptionhandling() {
		try {
			betragSoll = toDoubleArraylist(betragListSoll);
			betragHaben = toDoubleArraylist(betragListHaben);
		} catch (NumberFormatException e) {
			new AlertDialogFrame().showConfirmDialog("Fehlerhafter Buchungssatz",
					"Es wurde eine ung�ltige Zahl bei den Wertbetr�gen eingetragen! \nBitte �berpr�fen Sie Ihre Eingabe.",
					"OK", AlertDialogFrame.WARNING_TYPE);
			return false;
		}
		System.out.println(sumOfValues(betragSoll)+ " " + sumOfValues(betragHaben));
		if (sumOfValues(betragSoll) != sumOfValues(betragHaben)) {
			new AlertDialogFrame().showConfirmDialog("Fehlerhafter Buchungssatz",
					"Die Betr�ge der Soll- und Habenseite stimmen in der Summe nicht �berein! \nBitte �berpr�fen Sie Ihre Eingabe.",
					"OK", AlertDialogFrame.WARNING_TYPE);
			return false;
		}
		if(comboGF.getSelectionModel().isEmpty()){
			new AlertDialogFrame().showConfirmDialog("Keinen Gesch�ftsfall ausgew�hlt",
					"Bitte w�hlen Sie einen Gesch�ftsfall aus, dem der Buchungssatz zugeordnet werden kann.",
					"OK", AlertDialogFrame.WARNING_TYPE);
			return false;
		}
		for(ComboBox<String> cb : comboListSoll){
			if(cb.getSelectionModel().isEmpty()){
				new AlertDialogFrame().showConfirmDialog("Fehlerhafter Buchungssatz",
						"Es wurde(n) kein(e) Konto/Konten angegeben. \nBitte �berpr�fen Sie Ihre Eingabe.",
						"OK", AlertDialogFrame.WARNING_TYPE);
				return false;
			}
		}
		for(ComboBox<String> cb : comboListHaben){
			if(cb.getSelectionModel().isEmpty()){
				new AlertDialogFrame().showConfirmDialog("Fehlerhafter Buchungssatz",
						"Es wurde(n) kein(e) Konto/Konten angegeben. \nBitte �berpr�fen Sie Ihre Eingabe.",
						"OK", AlertDialogFrame.WARNING_TYPE);
				return false;
			}
		}
		return true;
	}

	// Event Listener on Button[#buttonSchliessen].onAction
	@FXML
	public void handleSchlie�en(ActionEvent event) {
		((Stage) buttonSchliessen.getScene().getWindow()).close();
	}
	
	public IDMap<Integer, Buchungssatz> getNeueBuchungssaetze() {
		return buchungss�tze;

	}
}
