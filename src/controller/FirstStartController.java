package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.Log;
import user.User;

public class FirstStartController implements Initializable {
	@FXML
	private VBox vb;
	@FXML
	private ComboBox<Integer> birthDay;
	@FXML
	private ComboBox<String> birthMonth;
	@FXML
	private ComboBox<Integer> birthYear;
	@FXML
	private TextField setFirstName;
	@FXML
	private TextField setLastName;
	@FXML
	private TextField setWeight, setNeck, setChest, setBiceps, setWaist, setStomach, setHips, setThigh, setCalf;
	@FXML
	private Button createUser;
	ObservableList<Integer> dayOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
	private static ObservableList<String> monthOptions = FXCollections.observableArrayList("StyczeÒ", "Luty", "Marzec", "KwiecieÒ",
			"Maj", "Czerwiec", "Lipiec", "SierpieÒ", "WrzesieÒ", "Paüdziernik", "Listopad", "GrudzieÒ");

	public static ObservableList<String> getMonthOptions() {
		return monthOptions;
	}

	public boolean checkNameCorrectness(String name) {
		Pattern pattern = Pattern.compile("[^a-zA-Z•π∆Ê Í£≥—Ò”ÛåúèüØø -]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find() == true)// jesli zostal odnaleziony znak spoza
									// zakresu a-z i A-Z -, czyli niepoprawny
			return false;
		return true;
	}

	public boolean checkFloatCorrectness(String number) {
		Pattern pattern = Pattern.compile("[^0-9,.]");
		Matcher matcher = pattern.matcher(number);
		if (matcher.find() == true)// jesli zostal odnaleziony znak spoza
									// zakresu 0-9 , .
			return false;
		return true;
	}

	public void handleCreateUser(ActionEvent event) {
		Float[] logInFloat = new Float[9];
		int i = 0;
		boolean fail = false;
		TextField[] logTable = { setWeight, setNeck, setChest, setBiceps, setWaist, setStomach, setHips, setThigh,
				setCalf };
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
				alert.setContentText("Wprowadzona wartoúÊ nie jest liczbπ");
				alert.showAndWait();
				break;
			}
			i++;
		}
		if (setFirstName.getText().isEmpty() == true || setLastName.getText().isEmpty() == true
				|| birthDay.getValue() == null || birthMonth.getValue() == null || birthYear.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Nie poda≥eú wymaganych danych");
			alert.showAndWait();
			fail = true;
		}
		if (fail == false) {// jesli mozna zarejestrowaÊ
			try {
				Date birthDate = changeToDateType(birthDay.getValue(), birthMonth.getValue(), birthYear.getValue());
				User user = new User(setFirstName.getText(), setLastName.getText(), birthDate);
				Log log = new Log(logInFloat);
				user.addLog(log);
				user.saveUser();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Zostaleú zarejestrowany!");
				alert.showAndWait();
				Stage stage = (Stage) vb.getScene().getWindow();// zamykanie
																// okna
																// rejestracji
				stage.close();
				Main startAgain = new Main();
				startAgain.start(stage);
			} catch (ParseException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B≥πd: " + e.toString());
				alert.showAndWait();
			}
		}
	}

	public Date changeToDateType(Integer day, String month, Integer year) throws ParseException {
		switch (month) {
		case "StyczeÒ":
			month = "01";
			break;
		case "Luty":
			month = "02";
			break;
		case "Marzec":
			month = "03";
			break;
		case "KwiecieÒ":
			month = "04";
			break;
		case "Maj":
			month = "05";
			break;
		case "Czerwiec":
			month = "06";
			break;
		case "Lipiec":
			month = "07";
			break;
		case "SierpieÒ":
			month = "08";
			break;
		case "WrzesieÒ":
			month = "09";
			break;
		case "Paüdziernik":
			month = "10";
			break;
		case "Listopad":
			month = "11";
			break;
		case "GrudzieÒ":
			month = "12";
			break;
		default:
			month = "1";
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.parse(day.toString() + "-" + month + "-" + year.toString());
	}

	public void initialize(URL url, ResourceBundle rb) {
		birthDay.setItems(dayOptions);
		birthMonth.setItems(monthOptions);
		for (int i = 1920; i <= Calendar.getInstance().get(Calendar.YEAR); i++)
			birthYear.getItems().add(i);
		setFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (checkNameCorrectness(setFirstName.getText()) == false)
				setFirstName.setText(oldValue);
		});
		setLastName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (checkNameCorrectness(setLastName.getText()) == false)
				setLastName.setText(oldValue);
		});
		TextField[] logTable = { setWeight, setNeck, setChest, setBiceps, setWaist, setStomach, setHips, setThigh,
				setCalf };
		for (TextField field : logTable) {// sprawdzam dla kazdej pozycji z logs
											// czy znajduja sie prawidlowe znaki
			field.textProperty().addListener((observable, oldValue, newValue) -> {
				if (checkFloatCorrectness(field.getText()) == false)
					field.setText(oldValue);
			});
		}
		createUser.setOnAction(this::handleCreateUser);
	}
}
