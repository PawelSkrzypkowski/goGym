package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import diary.Diary;
import diary.Exercise;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TrainingProgressController {

	public void showExerciseChart(Exercise exercise, VBox mainPage) {
		try {
			TreeMap<Date, Double> map = Diary.getMapDateRecord(exercise);
			Double max = 0.0;
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
			chart.setTitle("Postêpy w æwiczeniu " + exercise.getName());
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			Set<Entry<Date, Double>> entrySet = map.entrySet();
			for (Entry<Date, Double> entry : entrySet) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				series.getData().add(new XYChart.Data<String, Number>(sdf.format(entry.getKey()), entry.getValue()));
				if (max < entry.getValue())
					max = entry.getValue();
			}
			chart.getData().add(series);
			Label record = new Label("Aktualny rekord: " + max.toString());
			mainPage.getChildren().addAll(chart, record);

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showMonthlyRaisedWeightChart(VBox mainPage) {
		try{
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<String, Number> chart = new LineChart<String, Number>(xAxis, yAxis);
		chart.setTitle("Raport miesiêczny - podnoszony ciê¿ar");
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		Double max = 0.0;
		for (int i = 12; i >= 0; i--) {
			Double raised = Diary.getMonthlyRaisedWeight(i);
			String m;
			if (raised > 0.0) {
				if (Calendar.MONTH - i > 0)
					m = FirstStartController.getMonthOptions().get(Calendar.MONTH - i - 1);
				else
					m = FirstStartController.getMonthOptions().get(12 - Math.abs(Calendar.MONTH - i) - 1);
				series.getData().add(new XYChart.Data<String, Number>(m, raised));
				if(raised > max)
					max = raised;
			}
		}
		chart.getData().add(series);
		Label record = new Label("Aktualny miesiêczny rekord: " + max.toString());
		mainPage.getChildren().add(chart);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void showTrainingSummary(VBox mainPage, String fileName) {
		Diary diary = null;
		try {
			diary = Diary.readDiary(fileName);
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Nie odnaleziono pliku! B³¹d: " + e.toString());
			alert.showAndWait();
		}
		mainPage.getChildren().clear();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Label summary = new Label("Podsumowanie treningu z dnia " + format.format(diary.getStartDate()));
		summary.setFont(new Font(20));
		mainPage.getChildren().add(summary);
		Integer restTime = diary.getRestTime() / 60;// min
		Integer exerciseTime = diary.showTrainingTime() - restTime;
		Label label1 = new Label("Ca³kowity czas treningu:"), label2 = new Label("Czas æwiczeñ:");
		label1.setPrefWidth(245);
		label2.setPrefWidth(245);
		Label label3 = new Label(diary.showTrainingTime().toString() + " min");
		label3.setFont(new Font(15));
		Label label4 = new Label(exerciseTime.toString() + " min");
		label4.setFont(new Font(15));
		Label label5 = new Label("Czas odpoczynku:"), label6 = new Label("Wykonane æwiczenia:");
		Label label7 = new Label(restTime.toString() + " min");
		label7.setFont(new Font(15));
		Label label8 = new Label(new Integer(diary.getExercisesDone().size()).toString());
		label8.setFont(new Font(15));
		Label label9 = new Label("Podniesiony ciê¿ar:");
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
		/*
		 * usun to tuaj nizej
		 */
		showExerciseChart(diary.getExercisesDone().get(0).getExercise(), mainPage);
		showMonthlyRaisedWeightChart(mainPage);
	}

	public void createStage(VBox mainPage) {
		mainPage.getChildren().clear();
		showTrainingSummary(mainPage, "16-05-2017 14-55-51");
	}
}
