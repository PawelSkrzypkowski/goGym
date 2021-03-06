package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.diary.Diary;
import model.diary.Exercise;
import model.user.User;

/**
 * Klasa - kontroler obsługujący sekcję do przeglądania postępów treningowych
 * @author Pawe�
 *
 */
public class TrainingProgressController {
	private String[] logNames = new String[]{"Weight", "Neck", "Chest", "Biceps", "Waist", "Stomach", "Hips", "Thigh", "Calf"};
	private String[] logNamesPl = new String[]{"Waga", "Szyja", "Klatka piersiowa", "Biceps", "Talia", "Brzuch", "Biodra", "Udo", "Łydka"};
	/**
	 * Metoda pokazująca wykres wybranego ćwiczenia
	 * @param exercise
	 * @param mainPage
	 */
	public void showExerciseChart(Exercise exercise, VBox mainPage) {
		try {
			mainPage.getChildren().clear();
			TreeMap<Date, Double> map = Diary.getMapDateRecord(exercise);
			Double max = 0.0;
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
			chart.setTitle("Postępy w ćwiczeniu " + exercise.getName());
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			Set<Entry<Date, Double>> entrySet = map.entrySet();
			for (Entry<Date, Double> entry : entrySet) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				series.getData().add(new XYChart.Data<String, Number>(sdf.format(entry.getKey()), entry.getValue()));
				if (max < entry.getValue())
					max = entry.getValue();
			}
			series.setName("Maksymalne wyniki na poszczególnych treningach");
			chart.getData().add(series);
			Label record = new Label("Aktualny rekord: " + max.toString());
			mainPage.getChildren().addAll(chart, record);

		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Błąd: " + e.toString()
					+ ". Odczyt pliku nie powiódł się.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda pokazująca wykres wybranego elementu pomiarów ciała
	 * @param i
	 * @param mainPage
	 */
	public void showLogChart(int i, VBox mainPage){
		mainPage.getChildren().clear();
		try {
			TreeMap<Date, Float> tm = User.readUser().getDateLogMap(logNames[i]);
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			Float max = (float) 0;
			Float min = (float) 999;
			for(Entry<Date, Float> entry : tm.entrySet()){
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				series.getData().add(new XYChart.Data<String, Number>(sdf.format(entry.getKey()), entry.getValue()));
				if(max < entry.getValue())
					max = entry.getValue();
				if(min > entry.getValue())
					min = entry.getValue();
			}
			series.setName("Zmiany partii ciała: " + logNamesPl[i]);
			chart.getData().add(series);
			Label recordMin = new Label("Najmniejsza wartość: " + min.toString()), recordMax = new Label("Największa wartość: " + max.toString());
			mainPage.getChildren().addAll(chart, recordMin, recordMax);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Błąd: " + e.toString());
			alert.showAndWait();
		}
	}
	/**
	 * Metoda pokazuj�ca wykres podnoszonego cięzaru w przeciągu roku
	 * @param mainPage
	 */
	public void showMonthlyRaisedWeightChart(VBox mainPage) {
		try{
			mainPage.getChildren().clear();
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
			chart.setTitle("Raport miesięczny - podnoszony cięar");
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			Double max = 0.0;
			for (int i = 11; i >= 0; i--) {
				Double raised = Diary.getMonthlyRaisedWeight(i);
				String m = FirstStartController.getMonthOptions().get(((Calendar.getInstance().get(Calendar.MONTH) - i) % 12 + 12) % 12);
				series.getData().add(new XYChart.Data<String, Number>(m, raised));
				if(raised > max)
					max = raised;
			}
			series.setName("Podnoszony cięar w przeciągu roku");
			chart.getData().add(series);
			Label record = new Label("Aktualny miesięczny rekord: " + max.toString());
			mainPage.getChildren().addAll(chart, record);
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Błąd: " + e.toString()
					+ ". Błąd odczytu pliku.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda pokazujaca podsumowanie wybranego treningu
	 * @param mainPage
	 * @param fileName
	 */
	public void showTrainingSummary(VBox mainPage, String fileName) {
		Diary diary = null;
		try {
			diary = Diary.readDiary(fileName);
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Nie odnaleziono pliku! Błąd: " + e.toString());
			alert.showAndWait();
		}
		mainPage.getChildren().clear();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Label summary = new Label("Podsumowanie treningu z dnia " + format.format(diary.getStartDate()));
		summary.setFont(new Font(20));
		mainPage.getChildren().add(summary);
		Integer restTime = diary.getRestTime() / 60;// min
		Integer exerciseTime = diary.showTrainingTime() - restTime;
		Label label1 = new Label("Całkowity czas treningu:"), label2 = new Label("Czas ćwiczeń:");
		label1.setPrefWidth(245);
		label2.setPrefWidth(245);
		Label label3 = new Label(diary.showTrainingTime().toString() + " min");
		label3.setFont(new Font(15));
		Label label4 = new Label(exerciseTime.toString() + " min");
		label4.setFont(new Font(15));
		Label label5 = new Label("Czas odpoczynku:"), label6 = new Label("Wykonane ćwiczenia:");
		Label label7 = new Label(restTime.toString() + " min");
		label7.setFont(new Font(15));
		Label label8 = new Label(new Integer(diary.getExercisesDone().size()).toString());
		label8.setFont(new Font(15));
		Label label9 = new Label("Podniesiony cięar:");
		Label label10 = new Label(new Double(diary.showRaisedWeight()).toString() + " kg");
		label10.setFont(new Font(17));
		GridPane gp = new GridPane();
		gp.setPrefWidth(493);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.addRow(0, label1, label2);
		gp.addRow(1, label3, label4);
		gp.addRow(2, label5, label6);
		gp.addRow(3, label7, label8);
		gp.addRow(4, label9);
		gp.addRow(5, label10);
		mainPage.getChildren().add(gp);
	}
	/**
	 * Metoda pokauzjąca podsumowanie wybranego miesiąca
	 * @param mainPage
	 * @param minusMonth
	 */
	public void showMonthSummary(VBox mainPage, int minusMonth) {
		mainPage.getChildren().clear();
		try{
		Label summary = new Label("Podsumowanie treningów z miesiąca " + FirstStartController.getMonthOptions().get(((Calendar.getInstance().get(Calendar.MONTH) - minusMonth) % 12 + 12) % 12));
		summary.setFont(new Font(20));
		mainPage.getChildren().add(summary);
		Integer restTime = Diary.getMonthlyRestTime(minusMonth) / 60;// min
		Integer exerciseTime = Diary.getMonthlyExercisingTime(minusMonth);
		Label label1 = new Label("Całkowity czas treningu:"), label2 = new Label("Czas ćwiczeń:");
		label1.setPrefWidth(245);
		label2.setPrefWidth(245);
		Label label3 = new Label(Diary.getMonthlyTrainingTime(minusMonth) + " min");
		label3.setFont(new Font(15));
		Label label4 = new Label(exerciseTime.toString() + " min");
		label4.setFont(new Font(15));
		Label label5 = new Label("Czas odpoczynku:"), label6 = new Label("Wykonane ćwiczenia:");
		Label label7 = new Label(restTime.toString() + " min");
		label7.setFont(new Font(15));
		Label label8 = new Label(Diary.getMonthlyExercisesDone(minusMonth).toString());
		label8.setFont(new Font(15));
		Label label9 = new Label("Podniesiony cięar:");
		Label label10 = new Label(Diary.getMonthlyRaisedWeight(minusMonth).toString() + " kg");
		label10.setFont(new Font(17));
		GridPane gp = new GridPane();
		gp.setPrefWidth(493);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.addRow(0, label1, label2);
		gp.addRow(1, label3, label4);
		gp.addRow(2, label5, label6);
		gp.addRow(3, label7, label8);
		gp.addRow(4, label9);
		gp.addRow(5, label10);
		mainPage.getChildren().add(gp);
		}
		catch(IOException | ClassNotFoundException e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Błąd: " + e.toString()
					+ ". Błąd odczytu pliku.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda umożliwiająca wybranie wybranego podsumowania
	 * @param mainPage
	 */
	public void createStage(VBox mainPage) {
		mainPage.getChildren().clear();
		ImageView progress = new ImageView("/images/progress.png");
		mainPage.getChildren().add(progress);
		Button trainingsSummaries = new Button("Pokaż podsumowanie treningów"), showExercisesSummaries = new Button("Pokaż podsumowanie ćwiczeń"), showMonthByMonthSummaries = new Button("Pokaż podsumowanie miesięczne"),
				showMeansurmentsSummaries = new Button("Pokaż podsumowanie pomiarów ciała");
		mainPage.setSpacing(10);
		mainPage.getChildren().addAll(trainingsSummaries, showExercisesSummaries, showMonthByMonthSummaries, showMeansurmentsSummaries);
		trainingsSummaries.setOnAction((event) -> {
			mainPage.getChildren().clear();
			try {
				LinkedList<Diary> list = Diary.downloadDiaries();
				SortedSet<Date> set = new TreeSet<Date>(Collections.reverseOrder());
				for(Diary d : list)
					set.add(d.getStartDate());
				for(Date date : set){
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Button button = new Button("Trening z dnia: " + sdf.format(date));
					mainPage.getChildren().add(button);
					button.setOnAction((event2) -> {
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
						showTrainingSummary(mainPage, sdf2.format(date));
					});
				}
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Błąd: " + e.toString()
						+ ". Błąd odczytu pliku.");
				alert.showAndWait();
			}
		});
		showExercisesSummaries.setOnAction((event) -> {
			mainPage.getChildren().clear();
			try {
				List<Exercise> list = Exercise.downloadExercises();
				for(Exercise ex : list){
					Button button = new Button("Zobacz postępy w ćwiczeniu " + ex.getName());
					mainPage.getChildren().add(button);
					button.setOnAction((event2) -> {
						showExerciseChart(ex, mainPage);
					});
				}
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Błąd: " + e.toString()
						+ ". Błąd odczytu pliku.");
				alert.showAndWait();
			}
		});
		showMonthByMonthSummaries.setOnAction((event) -> {
			showMonthlyRaisedWeightChart(mainPage);
			for (int i = 0; i <= 11; i++) {
				String m = FirstStartController.getMonthOptions().get(((Calendar.getInstance().get(Calendar.MONTH) - i) % 12 + 12) % 12);
				Button monthSummary = new Button("Podsumowanie miesiąca " + m);
				mainPage.getChildren().add(monthSummary);
				final int i2 = i;
				monthSummary.setOnAction((event2) -> {
					showMonthSummary(mainPage, i2);
				});
			}
		});
		showMeansurmentsSummaries.setOnAction((event) -> {
			mainPage.getChildren().clear();
			int i=0;
			for(@SuppressWarnings("unused") String s : logNames){
				Button button = new Button("Postępy - " + logNamesPl[i]);
				mainPage.getChildren().add(button);
				final int in = i;
				button.setOnAction((event2) -> {
					showLogChart(in, mainPage);
				});
				i++;
			}
		});
	}
}
