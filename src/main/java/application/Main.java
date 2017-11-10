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
 * Klasa do obs�ugi uruchomienia aplikacji
 * @author Pawe�
 *
 */
public class Main extends Application {
	/**
	 * Metoda decyduj�ca czy utworzy� scen� do normalnego u�ytkowania czy stworzy� obiekt FirstStart
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FirstStart newUser = new FirstStart();
			if (FirstStart.checkIfUserExist() == false) {
				newUser.newUser();
			} else {
				VBox root = (VBox) FXMLLoader.load(getClass().getResource("/StartPage.fxml"));
				Scene scene = new Scene(root, 600, 400);
				scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.setTitle("goGym");

				primaryStage.getIcons().add(new Image((getClass().getResource("/images/icon.png").toExternalForm())));
				primaryStage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B��d: " + e.getStackTrace());
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
