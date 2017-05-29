package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import user.Log;
import user.User;

public class CalculatorsController {
	public void showBMI(VBox mainPage)
			throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException {
		Label weight = new Label("Waga:"), height = new Label("Wzrost:");
		Label title = new Label("WskaŸnik masy cia³a - BMI");
		title.setFont(new Font(15));
		Label des[] = new Label[8];
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		des[0] = new Label("do 16: wyg³odzenie");
		des[1] = new Label("od 16 do 17: wychudzenie");
		des[2] = new Label("od 17 do 18.5: niedowaga");
		des[3] = new Label("od 18.5 do 25: wartoœæ prawid³owa");
		des[4] = new Label("od 25 do 30: nadwaga");
		des[5] = new Label("od 30 do 35: I stopieñ oty³oœci");
		des[6] = new Label("od 35 do 40: II stopieñ oty³oœci");
		des[7] = new Label("powy¿ej 40: III stopieñ oty³oœci");
		TextField setWeight = new TextField(), setHeight = new TextField();
		Button calculate = new Button("Oblicz");
		User user = User.readUser();
		setWeight.setPromptText("kg");
		setWeight.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getWeight()).toString());
		setWeight.setMaxWidth(100);
		setHeight.setPromptText("cm");
		setHeight.setMaxWidth(100);
		mainPage.getChildren().clear();
		mainPage.getChildren().add(title);
		mainPage.getChildren().addAll(des);
		mainPage.getChildren().addAll(weight, setWeight, height, setHeight, calculate, score);
		calculate.setOnAction((event) -> {
			setWeight.setText(setWeight.getText().replace(',', '.'));
			setHeight.setText(setHeight.getText().replace(',', '.'));
			try {
				Double bmi = Log.calculateBMI(Float.parseFloat(setWeight.getText()),
						Integer.parseInt(setHeight.getText()));
				score.setText("Wynik: " + bmi.toString().substring(0, 4));
				score.setVisible(true);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
			}
		});
	}

	public void showBMR(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mê¿czyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label weight = new Label("Waga:"), height = new Label("Wzrost:"), age = new Label("Wiek:");
		Label title = new Label("WskaŸnik podstawowej przemiany materii - BMR");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Wspó³czynnik ten okreœla minimaln¹ iloœæ kalorii niezbêdnych do zachowania podstawowych funkcji organizmu.\nKalkulator dodatkowo okreœla niezbêdn¹ iloœæ kalorii i sk³adników po¿ywienia przy okreœleniu poziomu Twojej aktywnoœci fizycznej. Podany udzia³ procentowy sk³adników po¿ywienia zapewnia zdrowy i bezpieczny sposób od¿ywiania.");
		descr.setWrappingWidth(450);
		TextField setWeight = new TextField(), setHeight = new TextField(), setAge = new TextField();
		Button calculate = new Button("Oblicz");
		User user = User.readUser();
		setWeight.setPromptText("kg");
		setWeight.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getWeight()).toString());
		setWeight.setMaxWidth(100);
		setHeight.setPromptText("cm");
		setHeight.setMaxWidth(100);
		setAge.setPromptText("lata");
		setAge.setText(new Integer(user.calculateAge()).toString());
		mainPage.getChildren().clear();
		mainPage.getChildren().addAll(title, descr, female, male, weight, setWeight, height, setHeight, age, setAge,
				calculate, score);
		calculate.setOnAction((event) -> {
			boolean isFemale;
			if (female.isSelected())
				isFemale = true;
			else
				isFemale = false;
			setWeight.setText(setWeight.getText().replace(',', '.'));
			setHeight.setText(setHeight.getText().replace(',', '.'));
			setAge.setText(setAge.getText().replace(',', '.'));
			try {
				Integer bmr = Log.calculateBMR(isFemale, Float.parseFloat(setWeight.getText()),
						Integer.parseInt(setHeight.getText()), Integer.parseInt(setAge.getText()));
				score.setText("Wynik: " + bmr.toString());
				score.setVisible(true);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
			}
		});
	}

	public void showPerfectWeight(VBox mainPage){
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mê¿czyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label height = new Label("Wzrost:");
		Label title = new Label("WskaŸnik idealenj wagi - Wskaznik Broca");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Podczas badañ prowadzonych przez francuskiego lekarza Pierre’a Broca na ¿o³nierzach w XIX wieku zauwa¿ono zale¿noœæ, wed³ug której przeciêtnie masa cia³a badanych stanowi³a wartoœæ wzrostu w cm – 100. Obecnie stosuje siê modyfikacjê tego wzoru z uwzglêdnieniem p³ci. Przyjmuje siê, ¿e wzór Broca jest miarodajny dla osób o wzroœcie nie mniejszym ni¿ 160 cm i nie wiêkszym ni¿ 190 cm.");
		descr.setWrappingWidth(450);
		TextField setHeight = new TextField();
		Button calculate = new Button("Oblicz");
		setHeight.setPromptText("cm");
		setHeight.setMaxWidth(100);
		mainPage.getChildren().clear();
		mainPage.getChildren().addAll(title, descr, female, male, height, setHeight, calculate, score);
		calculate.setOnAction((event) -> {
			boolean isFemale;
			if (female.isSelected())
				isFemale = true;
			else
				isFemale = false;
			setHeight.setText(setHeight.getText().replace(',', '.'));
			try {
				Double pw = Log.calculateBroc(isFemale, Integer.parseInt(setHeight.getText()));
				score.setText("Wynik: " + pw.toString().substring(0, 4));
				score.setVisible(true);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
			}
		});
	}
	
	public void showFat(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mê¿czyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label weight = new Label("Waga:"), waist = new Label("Talia:");
		Label title = new Label("WskaŸnik poziomu tkanki t³usczowej");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Jest to wskaŸnik okreœlaj¹cy procentowy udzia³ t³uszczu w masie ca³ego cia³a");
		descr.setWrappingWidth(450);
		TextField setWeight = new TextField(), setWaist = new TextField();
		Button calculate = new Button("Oblicz");
		User user = User.readUser();
		setWeight.setPromptText("kg");
		setWeight.setMaxWidth(100);
		setWeight.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getWeight()).toString());
		setWaist.setPromptText("cm");
		setWaist.setMaxWidth(100);
		setWaist.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getWaist()).toString());
		mainPage.getChildren().clear();
		mainPage.getChildren().addAll(title, descr, female, male, weight, setWeight, waist, setWaist, calculate, score);
		calculate.setOnAction((event) -> {
			boolean isFemale;
			if (female.isSelected())
				isFemale = true;
			else
				isFemale = false;
			setWeight.setText(setWeight.getText().replace(',', '.'));
			setWaist.setText(setWaist.getText().replace(',', '.'));
			try {
				Double pw = Log.calculateFat(isFemale, Float.parseFloat(setWeight.getText()), Float.parseFloat(setWaist.getText()));
				score.setText("Wynik: " + pw.toString().substring(0, 4));
				score.setVisible(true);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
			}
		});
	}
	
	public void showWHR(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		Label hips = new Label("Biodra:"), waist = new Label("Talia:");
		Label title = new Label("WskaŸnik dystrybucji tkanki t³uszczowej - WHR");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Jest kolejnym sposobem na obliczanie poziom zapasowej tkanki t³uszczowej. Optymalna wartoœæ nie powinna przekraczaæ 0,8 wœród kobiet i 1 u mê¿czyzn. Proporcje te minimalizuj¹ prawdopodobieñstwo wyst¹pienia choroby wieñcowej i cukrzycy typu II.");
		descr.setWrappingWidth(450);
		TextField setHips = new TextField(), setWaist = new TextField();
		Button calculate = new Button("Oblicz");
		User user = User.readUser();
		setHips.setPromptText("kg");
		setHips.setMaxWidth(100);
		setHips.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getHips()).toString());
		setWaist.setPromptText("cm");
		setWaist.setMaxWidth(100);
		setWaist.setText(new Float(user.getLogs().get(user.getLogs().size() - 1).getWaist()).toString());
		mainPage.getChildren().clear();
		mainPage.getChildren().addAll(title, descr, hips, setHips, waist, setWaist, calculate, score);
		calculate.setOnAction((event) -> {
			setHips.setText(setHips.getText().replace(',', '.'));
			setWaist.setText(setWaist.getText().replace(',', '.'));
			try {
				Double whr = Log.calculateWHR(Float.parseFloat(setHips.getText()), Float.parseFloat(setWaist.getText()));
				score.setText("Wynik: " + whr.toString().substring(0, 4));
				score.setVisible(true);
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoœæ nie jest liczb¹");
				alert.showAndWait();
			}
		});
	}

	public void createStage(VBox mainPage) {
		mainPage.getChildren().clear();
		Button BMI = new Button("Kalkulator BMI"), BMR = new Button("Kalkulator BMR"),
				perfectWeight = new Button("Kalkulator idealnej wagi"),
				fat = new Button("Kalkulator tkanki t³uszczowej"), WHR = new Button("Kalkulator WHR");
		mainPage.getChildren().addAll(BMI, BMR, perfectWeight, fat, WHR);
		BMI.setOnAction((event) -> {
			try {
				showBMI(mainPage);
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B³¹d odczytu pliku. B³¹d: " + e.toString());
				alert.showAndWait();
			}
		});
		BMR.setOnAction((event) -> {
			try {
				showBMR(mainPage);
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B³¹d odczytu pliku. B³¹d: " + e.toString());
				alert.showAndWait();
			}
		});
		perfectWeight.setOnAction((event) -> {
			showPerfectWeight(mainPage);
		});
		fat.setOnAction((event) -> {
			try {
				showFat(mainPage);
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B³¹d odczytu pliku. B³¹d: " + e.toString());
				alert.showAndWait();
			}
		});
		WHR.setOnAction((event) -> {
			try {
				showWHR(mainPage);
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B³¹d odczytu pliku. B³¹d: " + e.toString());
				alert.showAndWait();
			}
		});
	}
}
