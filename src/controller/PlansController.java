package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import diary.Exercise;
import diary.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PlansController {
	private List<Workout> workoutList;

	public PlansController() {
		workoutList = new LinkedList<Workout>();
	}

	public void downloadPlans() {
		File folder = new File("workouts/");
		File[] listOfWorkouts = folder.listFiles();
		for (File file : listOfWorkouts) {
			if (file.isFile()) {
				try {
					workoutList.add(Workout.readWorkout(file.getName()));
				} catch (ClassNotFoundException | IOException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("Uszkodzony lub brak pliku treningowego! UsuÒ lub przywrÛc plik "
							+ file.getAbsolutePath() + " i uruchom aplikacje ponownie.");
					alert.showAndWait();
				}

			}
		}
	}

	public boolean checkIntegerCorrectness(String number) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(number);
		if (matcher.find() == true)// jesli zostal odnaleziony znak spoza
									// zakresu 0-9
			return false;
		return true;
	}
	
	public boolean checkStringCorrectness(String name) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9•π∆Ê Í£≥—Ò”ÛåúèüØø -]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find() == true || name.length() == 0)// jesli zostal odnaleziony znak spoza
									// zakresu a-z i A-Z -, czyli niepoprawny lub pusty
			return false;
		return true;
	}

	public void editExerciseSupport(Exercise exercise, Workout workout, ObservableList<String> options, int i,
			VBox mainPage) {
		ComboBox<String> cb = new ComboBox<String>(options);
		cb.setValue(exercise.getName());
		cb.setMaxWidth(150);
		cb.setStyle("-fx-background-color: #3399FF;");
		TextField editSetsNumber = new TextField(workout.getSetsNumber().get(i).toString());
		editSetsNumber.setMaxWidth(50);
		TextField editRest = new TextField(workout.getRest().get(i).toString());
		editRest.setMaxWidth(50);
		Label setsNumber = new Label("Iloúc serii: "), rest = new Label("Odpoczynek: ");
		Button save = new Button("Zapisz"), delete = new Button("UsuÒ"), up = new Button("Do gÛry"),
				down = new Button("Do dolu");
		HBox hb = new HBox();
		hb.setSpacing(30);
		if (i == 0 && i == workout.getExercises().size() - 1)
			hb.getChildren().addAll(save, delete);
		else if (i == 0)
			hb.getChildren().addAll(save, delete, down);
		else if (i == workout.getExercises().size() - 1)
			hb.getChildren().addAll(save, delete, up);
		else
			hb.getChildren().addAll(save, delete, up, down);
		VBox vb = new VBox();
		if (i % 2 == 1) {
			vb.setStyle("-fx-background-color: #3399FF;");
			cb.setStyle("-fx-background-color: #0066CC;");
			editSetsNumber.setStyle("-fx-background-color: #0066CC;");
			editRest.setStyle("-fx-background-color: #0066CC;");
		} else {
			vb.setStyle("-fx-background-color: #0066CC;");
			cb.setStyle("-fx-background-color: #3399FF;");
			editSetsNumber.setStyle("-fx-background-color: #3399FF;");
			editRest.setStyle("-fx-background-color: #3399FF;");
		}
		vb.getChildren().addAll(cb, setsNumber, editSetsNumber, rest, editRest, hb);
		mainPage.getChildren().add(vb);
		final int finalI = i;
		save.setOnAction((event) -> {
			saveEdittedExercise(workout, cb.getValue(), editSetsNumber.getText(), editRest.getText(), i);
			editWorkout(workout, mainPage);
		});
		delete.setOnAction((event) -> {
			workout.deleteItem(finalI);
			editWorkout(workout, mainPage);
		});
		up.setOnAction((edit) -> {
			workout.moveUpExercise(finalI);
			editWorkout(workout, mainPage);
		});
		down.setOnAction((edit) -> {
			workout.moveDownExercise(finalI);
			editWorkout(workout, mainPage);
		});
	}

	public void saveEdittedExercise(Workout workout, String exerciseName, String setsNumber, String rest, int i) {
		Exercise ex = Exercise.readExercise(exerciseName);
		if (ex == null || checkIntegerCorrectness(setsNumber) == false || checkIntegerCorrectness(rest) == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Podales niepoprawne dane!");
			alert.showAndWait();
		} else
			workout.editExercise(i, ex, Integer.parseInt(setsNumber), Integer.parseInt(rest));
	}

	public void saveNewExercise(Workout workout, String exerciseName, String setsNumber, String rest) {
		Exercise ex = Exercise.readExercise(exerciseName);
		if (ex == null || checkIntegerCorrectness(setsNumber) == false || checkIntegerCorrectness(rest) == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Podales niepoprawne dane!");
			alert.showAndWait();
		} else
			workout.addItemAtTheEnd(ex, Integer.parseInt(setsNumber), Integer.parseInt(rest));
	}

	public void addExerciseSupport(Workout workout, ObservableList<String> options, VBox mainPage) {
		Label add = new Label("Dodaj: ");
		add.setFont(new Font(15));
		Label setsNumber = new Label("Iloúc serii: "), rest = new Label("Odpoczynek: ");
		TextField editSetsNumber = new TextField("3");
		TextField editRest = new TextField();
		editRest.setPromptText("sekundy");
		ComboBox<String> cb = new ComboBox<String>(options);
		Button save = new Button("Zapisz");
		mainPage.getChildren().addAll(add, cb, setsNumber, editSetsNumber, rest, editRest, save);
		save.setOnAction((event) -> {
			saveNewExercise(workout, cb.getValue(), editSetsNumber.getText(), editRest.getText());
			editWorkout(workout, mainPage);
		});
	}
	public void editWorkoutPropertiesSupport(Workout workout, VBox mainPage) {
		Label name = new Label("Nazwa:"), description = new Label("Opis:"), type = new Label("Typ treningu:"),
				level = new Label("Poziom treningu:"), header = new Label("Cwiczenia:");
		header.setFont(new Font(15));
		TextField editName = new TextField(workout.getWorkoutName()),
				editType = new TextField(workout.getWorkoutType()),
				editLevel = new TextField(workout.getDifficultyLevel());
		TextArea editDescription = new TextArea(workout.getWorkoutDescription());
		Button save = new Button("Zapisz");
		mainPage.getChildren().addAll(name, editName, description, editDescription, type, editType, level, editLevel, save,
				header);
		save.setOnAction((event) -> {
			try {
				if(checkStringCorrectness(editName.getText()) == false || 
						checkStringCorrectness(editDescription.getText()) == false || 
						checkStringCorrectness(editType.getText()) == false || 
						checkStringCorrectness(editLevel.getText()) == false){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("Moøesz korzystaÊ tylko z liter, cyfr i znaku -");
					alert.showAndWait();
				}
				else if(workout.checkIfWorkoutExist(editName.getText())){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("Podana nazwa juz jest zajÍta");
					alert.showAndWait();
				}
				else{
					workout.changeWorkoutProperties(editName.getText(), editDescription.getText(), editType.getText(), editLevel.getText());
					editWorkout(workout, mainPage);
				}
			} catch (ClassNotFoundException | IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Uszkodzony lub brak pliku treningowego!");
				alert.showAndWait();
			}
		});
	}

	public void editWorkout(Workout workout, VBox mainPage) {
		mainPage.getChildren().clear();
		editWorkoutPropertiesSupport(workout, mainPage);
		List<Exercise> exerciseList = Exercise.downloadExercises();
		ObservableList<String> options = FXCollections.observableArrayList();
		for (Exercise exercise : exerciseList)
			options.add(exercise.getName());
		int i = 0;
		for (Exercise exercise : workout.getExercises()) {
			mainPage.getChildren().add(new Label());
			editExerciseSupport(exercise, workout, options, i, mainPage);
			i++;
		}
		addExerciseSupport(workout, options, mainPage);
	}
	public void addWorkout(VBox mainPage){
		mainPage.getChildren().clear();
		Workout workout = new Workout("", "", "", ""
				);
		String name = "Nowy";
		int i = 1;
		try {
			while(workout.checkIfWorkoutExist(name)){
				i++;
				name = "Nowy " + i;
			}
			workout.setWorkoutName(name);
			workout.saveWorkout();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		editWorkoutPropertiesSupport(workout, mainPage);
		List<Exercise> exerciseList = Exercise.downloadExercises();
		ObservableList<String> options = FXCollections.observableArrayList();
		for (Exercise exercise : exerciseList)
			options.add(exercise.getName());
		addExerciseSupport(workout, options, mainPage);
	}
	public void showWorkoutSupport(Workout workout, VBox mainPage, int i){
		HBox hb = new HBox(10);
		Label name = new Label(workout.getWorkoutName());
		Button start = new Button("Rozpocznij");
		Button edit = new Button("Edytuj");
		Button delete = new Button("UsuÒ");
		hb.getChildren().addAll(name, start, edit, delete);
		Label description = new Label(workout.getWorkoutDescription());
		description.setMaxHeight(30);
		description.setMaxWidth(450);
		Label type = new Label("Typ: " + workout.getWorkoutType());
		type.setMaxHeight(15);
		type.setMaxWidth(450);
		Label level = new Label("Poziom: " + workout.getDifficultyLevel());
		level.setMaxHeight(15);
		level.setMaxWidth(450);
		VBox vb = new VBox();
		vb.getChildren().addAll(hb, description, type, level);
		if (i % 2 == 1)
			vb.setStyle("-fx-background-color: #3399FF;");
		else
			vb.setStyle("-fx-background-color: #0066CC;");
		mainPage.getChildren().add(vb);
		delete.setOnAction((event) -> {
			workout.deleteWorkout();
			workoutList.clear();
			createStage(mainPage);
		});
		edit.setOnAction((event) -> {
			editWorkout(workout, mainPage);
		});
	}
	public void createStage(VBox mainPage) {
		downloadPlans();
		mainPage.getChildren().clear();
		ImageView imageView = new ImageView("file:images/plans.png");
		Button addNewWorkout = new Button("Dodaj nowy plan");
		mainPage.getChildren().addAll(imageView, addNewWorkout);
		int i=0;
		for (Workout workout : workoutList) {
			mainPage.getChildren().add(new Label());
			showWorkoutSupport(workout, mainPage, i);
			i++;
		}
		addNewWorkout.setOnAction((event) -> {
			addWorkout(mainPage);
		});
	}
}