package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import diary.Diary;
import diary.Exercise;
import diary.ExercisesDone;
import diary.Set;
import diary.Workout;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import javafx.util.Duration;
/**
 * Klasa - kontroler do obslugi planow treningowych
 * @author Pawe≥
 *
 */
public class PlansController {
	private List<Workout> workoutList;
	/**
	 * Konstruktor tworzacy pusty obiekt
	 */
	public PlansController() {
		workoutList = new LinkedList<Workout>();
	}
	/**
	 * Metoda pobierajaca plany treningowe
	 */
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
		if (matcher.find() == true || number.length() == 0)// jesli zostal
															// odnaleziony znak
															// spoza
			// zakresu 0-9
			return false;
		return true;
	}

	public boolean checkStringCorrectness(String name) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9•π∆Ê Í£≥—Ò”ÛåúèüØø -]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find() == true || name.length() == 0)// jesli zostal
															// odnaleziony znak
															// spoza
			// zakresu a-z i A-Z -, czyli niepoprawny lub pusty
			return false;
		return true;
	}
	/**
	 * Metoda zapewniajaca wyglad do edycji cwiczenia
	 * @param exercise
	 * @param workout
	 * @param options
	 * @param i
	 * @param mainPage
	 */
	public void editExerciseSupport(Exercise exercise, Workout workout, ObservableList<String> options, int i,
			VBox mainPage) {
		ComboBox<String> cb = new ComboBox<String>(options);
		cb.setValue(exercise.getName());
		cb.setMaxWidth(150);
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
		vb.setPadding(new Insets(5));
		if (i % 2 == 1) {
			vb.setStyle("-fx-background-color: #bc5856; -fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5;");
		} else {
			vb.setStyle("-fx-background-color: #0db5df; -fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5;");
		}
		vb.getChildren().addAll(cb, setsNumber, editSetsNumber, rest, editRest, hb);
		mainPage.getChildren().add(vb);
		final int finalI = i;
		save.setOnAction((event) -> {
			saveEdittedExercise(workout, cb.getValue(), editSetsNumber.getText(), editRest.getText(), i);
			editWorkout(workout, mainPage);
		});
		delete.setOnAction((event) -> {
			try {
				workout.deleteItem(finalI);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B≥πd: " + e.toString());
				alert.showAndWait();
			}
			editWorkout(workout, mainPage);
		});
		up.setOnAction((edit) -> {
			try {
				workout.moveUpExercise(finalI);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B≥πd: " + e.toString());
				alert.showAndWait();
			}
			editWorkout(workout, mainPage);
		});
		down.setOnAction((edit) -> {
			try {
				workout.moveDownExercise(finalI);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B≥πd: " + e.toString());
				alert.showAndWait();
			}
			editWorkout(workout, mainPage);
		});
	}
	/**
	 * Metoda do zapisu edytowanego cwiczenia
	 * @param workout
	 * @param exerciseName
	 * @param setsNumber
	 * @param rest
	 * @param i
	 */
	public void saveEdittedExercise(Workout workout, String exerciseName, String setsNumber, String rest, int i) {
		Exercise ex;
		try {
			ex = Exercise.readExercise(exerciseName);
				workout.editExercise(i, ex, Integer.parseInt(setsNumber), Integer.parseInt(rest));
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B≥πd pliku. B≥πd: " + e.toString());
			alert.showAndWait();
		} catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Podales niepoprawne dane!");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda do zapisu nowego cwiczenia
	 * @param workout
	 * @param exerciseName
	 * @param setsNumber
	 * @param rest
	 */
	public void saveNewExercise(Workout workout, String exerciseName, String setsNumber, String rest) {
		Exercise ex;
		try {
			ex = Exercise.readExercise(exerciseName);
			workout.addItemAtTheEnd(ex, Integer.parseInt(setsNumber), Integer.parseInt(rest));
		} catch(NumberFormatException | ClassNotFoundException | IOException e){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Podales niepoprawne dane!");
			alert.showAndWait();
		}

	}
	/**
	 * Metoda zapewbiajaca wyglad przy dodawaniu nowego cwiczenia
	 * @param workout
	 * @param options
	 * @param mainPage
	 */
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
	/**
	 * Metoda do edycji elementow opisowych treningu
	 * @param workout
	 * @param mainPage
	 */
	public void editWorkoutPropertiesSupport(Workout workout, VBox mainPage) {
		Label name = new Label("Nazwa:"), description = new Label("Opis:"), type = new Label("Typ treningu:"),
				level = new Label("Poziom treningu:"), header = new Label("Cwiczenia:");
		header.setFont(new Font(15));
		TextField editName = new TextField(workout.getWorkoutName()),
				editType = new TextField(workout.getWorkoutType()),
				editLevel = new TextField(workout.getDifficultyLevel());
		TextArea editDescription = new TextArea(workout.getWorkoutDescription());
		Button save = new Button("Zapisz");
		mainPage.getChildren().addAll(name, editName, description, editDescription, type, editType, level, editLevel,
				save, header);
		save.setOnAction((event) -> {
			try {
				if (checkStringCorrectness(editName.getText()) == false
						|| checkStringCorrectness(editDescription.getText()) == false
						|| checkStringCorrectness(editType.getText()) == false
						|| checkStringCorrectness(editLevel.getText()) == false) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("Moøesz korzystaÊ tylko z liter, cyfr i znaku -");
					alert.showAndWait();
				} else if (workout.checkIfWorkoutExist(editName.getText())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("Podana nazwa juz jest zajÍta");
					alert.showAndWait();
				} else {
					workout.changeWorkoutProperties(editName.getText(), editDescription.getText(), editType.getText(),
							editLevel.getText());
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
	/**
	 * Metoda do edycji treningu
	 * @param workout
	 * @param mainPage
	 */
	public void editWorkout(Workout workout, VBox mainPage) {
		mainPage.getChildren().clear();
		editWorkoutPropertiesSupport(workout, mainPage);
		List<Exercise> exerciseList;
		try {
			exerciseList = Exercise.downloadExercises();
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
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B≥πd pliku. B≥πd: " + e.toString());
			alert.showAndWait();
		}
	}
	/**
	 * Metoda dodajaca trening
	 * @param mainPage
	 */
	public void addWorkout(VBox mainPage) {
		mainPage.getChildren().clear();
		Workout workout = new Workout("", "", "", "");
		String name = "Nowy";
		int i = 1;
		try {
			while (workout.checkIfWorkoutExist(name)) {
				i++;
				name = "Nowy " + i;
			}
			workout.setWorkoutName(name);
			workout.saveWorkout();
			editWorkoutPropertiesSupport(workout, mainPage);
			List<Exercise> exerciseList = Exercise.downloadExercises();
			ObservableList<String> options = FXCollections.observableArrayList();
			for (Exercise exercise : exerciseList)
				options.add(exercise.getName());
			addExerciseSupport(workout, options, mainPage);
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B≥πd podczas zapisywania/odczytywania pliku");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda do odbywania przerwy
	 * @param workout
	 * @param diary
	 * @param exerciseNumber
	 * @param setNumber
	 * @param mainPage
	 */
	public void doRest(Workout workout, Diary diary, int exerciseNumber, int setNumber, VBox mainPage) {
		int restTime = workout.getRest().get(exerciseNumber - 1);
		mainPage.getChildren().clear();
		final int START = restTime;
		IntegerProperty startTime = new SimpleIntegerProperty(START);
		Label seconds = new Label();
		seconds.textProperty().bind(startTime.asString());
		Button skip = new Button("PomiÒ");
		mainPage.getChildren().addAll(seconds, skip);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START + 1), new KeyValue(startTime, 0)));
		timeline.playFromStart();
		timeline.setOnFinished((event) -> {
			diary.increaseRestTime(START);
			if (setNumber == workout.getSetsNumber().get(exerciseNumber - 1)) // jesli ostatnia seria
				doSet(workout, diary, exerciseNumber + 1, 1, mainPage);
			else// jesli w srodku serii
				doSet(workout, diary, exerciseNumber, setNumber + 1, mainPage);
		});
		skip.setOnAction((event) -> {
			diary.increaseRestTime(START - startTime.intValue());
			timeline.stop();
			if (setNumber == workout.getSetsNumber().get(exerciseNumber - 1))//jesli ostatnia seria
				doSet(workout, diary, exerciseNumber + 1, 1, mainPage);
			else// jesli w srodku cwiczenia
				doSet(workout, diary, exerciseNumber, setNumber + 1, mainPage);
		});
	}
	/**
	 * Metoda do wykonywania wybranej serii treningu
	 * @param workout
	 * @param diary
	 * @param exerciseNumber
	 * @param setNumber
	 * @param mainPage
	 */
	public void doSet(Workout workout, Diary diary, int exerciseNumber, int setNumber, VBox mainPage) {
		mainPage.getChildren().clear();
		Label header = new Label(workout.getWorkoutName());
		header.setFont(new Font(15));
		mainPage.getChildren().add(header);
		Exercise exercise = workout.getExercises().get(exerciseNumber - 1);
		Label exerciseName = new Label(exercise.getName());
		TextField reps = new TextField(), weight = new TextField();
		reps.setPromptText("IloúÊ powtÛrzeÒ");
		weight.setPromptText("CiÍøar");
		HBox set = new HBox(15);
		set.getChildren().addAll(reps, new Label("x"), weight);
		Button save = new Button("Zapisz"), skipSet = new Button("PomiÒ seriÍ"),
				skipExercise = new Button("PomiÒ Êwiczenie");
		mainPage.getChildren().addAll(exerciseName, set, save, skipSet, skipExercise);
		skipExercise.setOnAction((event) -> {
			if (workout.getExercises().size() == exerciseNumber){// jesli koniec treningu
				try {
					diary.setFinishDate(new Date());
					diary.saveDiary();
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("B≥πd podczas zapisywania treningu");
					alert.showAndWait();
				}
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
				TrainingProgressController tpc = new TrainingProgressController();
				tpc.showTrainingSummary(mainPage, format.format(diary.getStartDate()));
				return;
			}
			else
				doSet(workout, diary, exerciseNumber + 1, 1, mainPage);
		});
		skipSet.setOnAction((event) -> {
			if(setNumber == 1){//jesli pierwsza seria
				ExercisesDone ed = new ExercisesDone(workout.getExercises().get(exerciseNumber-1));
				diary.getExercisesDone().add(ed);//dodaje tylko cwiczenie bez wykonanych serii
			}
			if (setNumber == workout.getSetsNumber().get(exerciseNumber - 1)) {// jesli ostatnia seria
				if (workout.getExercises().size() == exerciseNumber){// jesli koniec treningu
					try {
						diary.setFinishDate(new Date());
						diary.saveDiary();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Informacja");
						alert.setHeaderText("");
						alert.setContentText("B≥πd podczas zapisywania treningu");
						alert.showAndWait();
					}
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
					TrainingProgressController tpc = new TrainingProgressController();
					tpc.showTrainingSummary(mainPage, format.format(diary.getStartDate()));
					return;
				}
				else
					doSet(workout, diary, exerciseNumber + 1, 1, mainPage);
			} else// jesli w srodku cwiczenia
				doSet(workout, diary, exerciseNumber, setNumber + 1, mainPage);
		});
		save.setOnAction((event) -> {
			weight.setText(weight.getText().replace(',', '.'));
			try{
				Double w = Double.parseDouble(weight.getText());
				Integer r = Integer.parseInt(reps.getText());
				if (setNumber == 1) {// jesli 1 seria nowego cwiczenia
					ExercisesDone ed = new ExercisesDone(workout.getExercises().get(exerciseNumber - 1));
					ed.getSets().add(new Set(r, w));
					diary.getExercisesDone().add(ed);
				} else {
					diary.getExercisesDone().get(diary.getExercisesDone().size() - 1).getSets().add(new Set(r, w));
				}
				if(setNumber == workout.getSetsNumber().get(exerciseNumber - 1) && workout.getExercises().size() == exerciseNumber){//jesli koniec treningu
					try {
						diary.setFinishDate(new Date());
						diary.saveDiary();
					} catch (IOException e) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Informacja");
						alert.setHeaderText("");
						alert.setContentText("B≥πd podczas zapisywania treningu");
						alert.showAndWait();
					}
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
					TrainingProgressController tpc = new TrainingProgressController();
					tpc.showTrainingSummary(mainPage, format.format(diary.getStartDate()));
					return;
				}
				else
					doRest(workout, diary, exerciseNumber, setNumber, mainPage);
			}
			catch(NumberFormatException e){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wprowadzona wartoúÊ nie jest liczbπ");
				alert.showAndWait();
			}
		});
	}
	/**
	 * Metoda do obslugi wybranego treningu
	 * @param workout
	 * @param mainPage
	 * @param i
	 */
	public void showWorkoutSupport(Workout workout, VBox mainPage, int i) {
		HBox hb = new HBox(5);
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
		vb.setMaxWidth(475);
		vb.setPadding(new Insets(5));
		vb.getChildren().addAll(hb, description, type, level);
		if (i % 2 == 1)
			vb.setStyle("-fx-background-color: #0db5df; -fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5;");
		else
			vb.setStyle("-fx-background-color: #bc5856; -fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5;");
		mainPage.getChildren().add(vb);
		delete.setOnAction((event) -> {
			try {
				workout.deleteWorkout();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("B≥πd usuwania pliku");
				alert.showAndWait();
			}
			workoutList.clear();
			createStage(mainPage);
		});
		edit.setOnAction((event) -> {
			editWorkout(workout, mainPage);
		});
		start.setOnAction((event) -> {
			Diary diary = new Diary();
			if(workout.getExercises().isEmpty()){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Informacja");
				alert.setHeaderText("");
				alert.setContentText("Wybrany trening jest pusty.");
				alert.showAndWait();
			} else {
				doSet(workout, diary, 1, 1, mainPage);
			}
		});
	}
	/**
	 * Metoda umozliwiajaca obsluge wszystkich opcji obslugi treningow
	 * @param mainPage
	 */
	public void createStage(VBox mainPage) {
		downloadPlans();
		mainPage.getChildren().clear();
		mainPage.setSpacing(0);
		ImageView plans = new ImageView("/plans.png");
		mainPage.getChildren().add(plans);
		Button addNewWorkout = new Button("Dodaj nowy plan");
		mainPage.getChildren().add(addNewWorkout);
		int i = 0;
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