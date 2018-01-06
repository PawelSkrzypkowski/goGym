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
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.user.Log;
import model.user.User;

/**
 * Klasa obsługująca sekcję kalkulatorów aplikacji
 * @author Paweł
 *
 */
public class CalculatorsController {
	/**
	 * Metoda do obsługi obliczania BMI
	 * @param mainPage
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void showBMI(VBox mainPage)
			throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException {
		Label weight = new Label("Waga:"), height = new Label("Wzrost:");
		Label title = new Label("Wskażnik masy ciała - BMI");
		title.setFont(new Font(15));
		Label des[] = new Label[8];
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		des[0] = new Label("do 16: wygłodzenie");
		des[1] = new Label("od 16 do 17: wychudzenie");
		des[2] = new Label("od 17 do 18.5: niedowaga");
		des[3] = new Label("od 18.5 do 25: wartość prawidłowa");
		des[4] = new Label("od 25 do 30: nadwaga");
		des[5] = new Label("od 30 do 35: I stopień otyłości");
		des[6] = new Label("od 35 do 40: II stopień otyłości");
		des[7] = new Label("powyżej 40: III stopień otyłości");
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
				alert.setContentText("Wprowadzona wartość nie jest liczbą");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obsługi obliczania BMR
	 * @param mainPage
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void showBMR(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mężczyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label weight = new Label("Waga:"), height = new Label("Wzrost:"), age = new Label("Wiek:");
		Label title = new Label("Wskaźnik podstawowej przemiany materii - BMR");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Współczynnik ten określa minimalną ilość kalorii niezbędnych do zachowania podstawowych funkcji organizmu.\nKalkulator dodatkowo określa niezbędną ilość kalorii i składników pożywienia przy określeniu poziomu Twojej aktywności fizycznej. Podany udział procentowy składników pożywienia zapewnia zdrowy i bezpieczny sposób odżywiania.");
		descr.setWrappingWidth(450);
		descr.setFill(Color.WHITE);
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
				alert.setContentText("Wprowadzona wartość nie jest liczbą�");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obsługi obliczania perfekcyjnej wagi
	 * @param mainPage
	 */
	public void showPerfectWeight(VBox mainPage){
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mężczyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label height = new Label("Wzrost:");
		Label title = new Label("Wskaźnik idealenj wagi - Wskaznik Broca");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Podczas badał prowadzonych przez francuskiego lekarza Pierre'a Broca na żołnierzach w XIX wieku zauważono zależność, według której przeciętnie masa ciała badanych stanowiła wartość wzrostu w cm - 100. Obecnie stosuje się modyfikację tego wzoru z uwzględnieniem płci. Przyjmuje się, że wzór Broca jest miarodajny dla osób o wzroście nie mniejszym niż 160 cm i nie większym niż 190 cm.");
		descr.setWrappingWidth(450);
		descr.setFill(Color.WHITE);
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
				alert.setContentText("Wprowadzona wartość nie jest liczbą");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obsługi obliczania ilości tkanki tłuszczowej
	 * @param mainPage
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void showFat(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		ToggleGroup sex = new ToggleGroup();
		RadioButton female = new RadioButton("Kobieta");
		RadioButton male = new RadioButton("Mężczyzna");
		female.setToggleGroup(sex);
		male.setToggleGroup(sex);
		female.setSelected(true);
		Label weight = new Label("Waga:"), waist = new Label("Talia:");
		Label title = new Label("Wskaźnik poziomu tkanki tłuszczowej");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Jest to wskaźnik określający procentowy udział tłuszczu w masie całego ciała");
		descr.setWrappingWidth(450);
		descr.setFill(Color.WHITE);
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
				alert.setContentText("Wprowadzona wartość nie jest liczbą");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obsługi obliczania WHR
	 * @param mainPage
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void showWHR(VBox mainPage) throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		Label hips = new Label("Biodra:"), waist = new Label("Talia:");
		Label title = new Label("Wskaźnik dystrybucji tkanki tłuszczowej - WHR");
		title.setFont(new Font(15));
		Label score = new Label();
		score.setVisible(false);
		score.setFont(new Font(15));
		Text descr = new Text(
				"Jest kolejnym sposobem na obliczanie poziom zapasowej tkanki tłuszczowej. Optymalna wartość nie powinna przekraczać 0,8 wśród kobiet i 1 u mężczyzn. Proporcje te minimalizują prawdopodobieństwo wystąpienia choroby wieńcowej i cukrzycy typu II.");
		descr.setWrappingWidth(450);
		descr.setFill(Color.WHITE);
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
				alert.setContentText("Wprowadzona wartość nie jest liczbą");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obsługi kalkulatorów
	 * @param mainPage
	 */
	public void createStage(VBox mainPage) {
		mainPage.getChildren().clear();
		ImageView calc = new ImageView("/images/calculators.png");
		mainPage.getChildren().add(calc);
		mainPage.setSpacing(10);
		Button BMI = new Button("Kalkulator BMI"), BMR = new Button("Kalkulator BMR"),
				perfectWeight = new Button("Kalkulator idealnej wagi"),
				fat = new Button("Kalkulator tkanki tłuszczowej"), WHR = new Button("Kalkulator WHR");
		mainPage.getChildren().addAll(BMI, BMR, perfectWeight, fat, WHR);
		BMI.setOnAction((event) -> {
			try {
				showBMI(mainPage);
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Błąd odczytu pliku. Błąd: " + e.toString());
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
				alert.setContentText("Błąd odczytu pliku. Błąd: " + e.toString());
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
				alert.setContentText("Błąd odczytu pliku. Błąd: " + e.toString());
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
				alert.setContentText("Błąd odczytu pliku. Błąd: " + e.toString());
				alert.showAndWait();
			}
		});
	}
}
