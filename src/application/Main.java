package application;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
/**
 * Klasa do obs³ugi uruchomienia aplikacji
 * @author Pawe³
 *
 */
public class Main extends Application {
	/**
	 * Metoda decyduj¹ca czy utworzyæ scenê do normalnego u¿ytkowania czy stworzyæ obiekt FirstStart
	 */
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
				primaryStage.setTitle("goGym");
				primaryStage.getIcons().add(new Image((getClass().getResource("/icon.png").toExternalForm())));
				primaryStage.show();
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
