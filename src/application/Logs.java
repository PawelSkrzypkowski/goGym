package application;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * Klasa do obs³ugi pliku FXML u¿ywanego w Pomiarach cia³a
 * @author Pawe³
 *
 */
public class Logs {
	public void createStage(VBox mainPage) throws IOException {
		GridPane root =(GridPane) FXMLLoader.load(getClass().getResource("/application/LogFxml.fxml"));
		mainPage.getChildren().clear();
		mainPage.setSpacing(0);
		ImageView logs = new ImageView("/logs.png");
		mainPage.getChildren().add(logs);
		mainPage.getChildren().add(root);
	}

}
