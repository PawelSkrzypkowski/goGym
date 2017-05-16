package application;


import diary.Exercise;
import diary.Workout;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FirstStart newUser = new FirstStart();
			if (FirstStart.checkIfUserExist() == false) {
				newUser.newUser();
			} else {
				VBox root = (VBox) FXMLLoader.load(getClass().getResource("StartPage.fxml"));
				Scene scene = new Scene(root, 600, 400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();

				for(int i=0;i<20;i++){
					Exercise ex = new Exercise("Wyciskanie na lawce prostej" + i, "Machanie sztangaMachanie sztangaMachanie sztangaMachanie sztangaMachanie sztangaMachanie sztangaMachanie sztangaMachanie sztangaMachanie sztanga",new String[] {"Miesieñ piersiowy wiekszy", "triceps"});
					ex.saveExercise();
				}
				for(int i=0;i<9;i++){
					Workout work = new Workout("Split 3 dniowy nr " + i, "Opis splituOpis splituOpis splituOpis splituOpis splituOpis splituOpis splituOpis splitu", "split", "œrednio-zaawansowany");
					work.getExercises().add(Exercise.readExercise("Wyciskanie na lawce prostej1"));
					work.getSetsNumber().add(new Integer(4));
					work.getRest().add(60);
					work.getExercises().add(Exercise.readExercise("Wyciskanie na lawce prostej3"));
					work.getSetsNumber().add(new Integer(14));
					work.getRest().add(600);
					work.saveWorkout();
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B³¹d: " + e.toString());
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
