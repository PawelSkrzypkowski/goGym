package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import user.User;

public class StartPageController implements Initializable {
	@FXML
	private Button homeButton, plansButton, calculatorsButton, progressButton, logsButton, settingsButton;
	@FXML
	private Label userData;
	private VBox mainPage = new VBox();
	@FXML
	private HBox page;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			User user = User.readUser();
			userData.setText("Witaj " + user.getFirstName() + " " + user.getLastName() + "!\n" + "Waga: "
					+ user.getLogs().get(user.getLogs().size() - 1).getWeight() + " kg\n" + "Wiek: "
					+ user.calculateAge() + " lat");
			loadMain();
			plansButton.setOnAction((event) -> {
				PlansController plansController = new PlansController();
				plansController.createStage(mainPage);
			});
			homeButton.setOnAction((event) -> {
				page.getChildren().remove(page.getChildren().size() - 1);
				mainPage.getChildren().clear();
				loadMain();
			});
			progressButton.setOnAction((event) -> {
				TrainingProgressController trainingProgressController = new TrainingProgressController();
				trainingProgressController.createStage(mainPage);
			});

		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B³¹d: " + e.toString() + ". Uszkodzony lub brak pliku u¿ytkownika! Usuñ lub przywróc plik user i uruchom aplikacje ponownie.");
			alert.showAndWait();
		}
	}

	public void loadMain() {
		ImageView home = new ImageView("file:images/home.png");
		mainPage.getChildren().add(home);
		mainPage.setPadding(new Insets(10));
		ScrollPane sp = new ScrollPane(mainPage);
		sp.setFitToHeight(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setFitToWidth(true);
		page.getChildren().add(sp);
	}
}
