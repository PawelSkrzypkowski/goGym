package application;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * Klasa do obsługi pliku FXML używanego w Pomiarach ciała
 * @author Paweł
 *
 */
public class Logs {
	public void createStage(VBox mainPage) throws IOException {
		GridPane root =(GridPane) FXMLLoader.load(getClass().getResource("/LogFxml.fxml"));
		mainPage.getChildren().clear();
		mainPage.setSpacing(0);
		ImageView logs = new ImageView("/images/logs.png");
		mainPage.getChildren().add(logs);
		mainPage.getChildren().add(root);
	}

}
