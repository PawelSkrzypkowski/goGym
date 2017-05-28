package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Logs {
	public void createStage(VBox mainPage) throws IOException {
		GridPane root =(GridPane) FXMLLoader.load(getClass().getResource("/application/LogFxml.fxml"));
		mainPage.getChildren().clear();
		mainPage.getChildren().add(root);
	}

}
