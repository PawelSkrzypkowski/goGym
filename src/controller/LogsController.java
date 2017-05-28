package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import user.Log;
import user.User;

public class LogsController implements Initializable {
	@FXML
	private TextField setWeight, setNeck, setChest, setBiceps, setWaist, setStomach, setHips, setThigh, setCalf;
	@FXML
	private Button addLog;
	private TextField[] logTable=new TextField[9];
	public boolean checkFloatCorrectness(String number) {
		Pattern pattern = Pattern.compile("[^0-9,.]");
		Matcher matcher = pattern.matcher(number);
		if (matcher.find() == true)// jesli zostal odnaleziony znak spoza
									// zakresu 0-9 , .
			return false;
		return true;
	}
	
	public void addLog(ActionEvent event){
		Float[] logInFloat = new Float[9];
		int i = 0;
		boolean fail = false;
		for (TextField field : logTable) {
			field.setText(field.getText().replace(',', '.'));
			if (field.getText().isEmpty() == true)
				field.setText("0");
			try {
				logInFloat[i] = Float.parseFloat(field.getText());
			} catch (NumberFormatException e){// jesli zly format liczby
				fail = true;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
				break;
			}
			i++;
		}
		if (fail == false) {// jesli mozna dodac log
			try {
				User user = User.readUser();
				Log log = new Log(logInFloat);
				user.addLog(log);
				user.saveUser();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Doda³eœ pomiary!");
				alert.showAndWait();
			} catch (IOException | ClassNotFoundException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B³¹d: " + e.toString());
				alert.showAndWait();
			}
		}
	}
	
	public void createStage(VBox mainPage) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TextField[] logTable = { setWeight, setNeck, setChest, setBiceps, setWaist, setStomach, setHips, setThigh,
				setCalf };
		this.logTable = logTable;
		for (TextField field : logTable) {// dodaje listenery
			field.textProperty().addListener((observable, oldValue, newValue) -> {
				if (checkFloatCorrectness(field.getText()) == false)
					field.setText(oldValue);
			});
		}
		addLog.setOnAction(this::addLog);
	}
}
