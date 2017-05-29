package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.Logs;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import user.User;

public class StartPageController implements Initializable {
	@FXML
	private Button homeButton, plansButton, calculatorsButton, progressButton, logsButton;
	@FXML
	private ImageView refreshUserData, editAvatar, avatar;
	@FXML
	private Label userData;
	private VBox mainPage = new VBox();
	@FXML
	private HBox page;
	@FXML
	private VBox root;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadUserDetails();
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
			logsButton.setOnAction((event) -> {
				Logs logs = new Logs();
				try {
					logs.createStage(mainPage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			calculatorsButton.setOnAction((event) -> {
				CalculatorsController cc = new CalculatorsController();
				cc.createStage(mainPage);
			});
			refreshUserData.setOnMouseClicked((event) -> {// moze by dodac
															// podswietlnie? :)
				try {
					loadUserDetails();
				} catch (ClassNotFoundException | IOException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informacja");
					alert.setHeaderText("");
					alert.setContentText("B³¹d: " + e.toString()
							+ ". Uszkodzony lub brak pliku u¿ytkownika! Usuñ lub przywróc plik user i uruchom aplikacje ponownie.");
					alert.showAndWait();
				}
			});
			editAvatar.setOnMouseClicked((event) -> {
				FileChooser chooseFile = new FileChooser();
				chooseFile.setTitle("Wybierz plik");
				chooseFile.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				Stage stage = (Stage) root.getScene().getWindow();
				File file = chooseFile.showOpenDialog(stage);
				if (file != null) {
					Image image = new Image(file.toURI().toString());
					if (image.getWidth() / image.getHeight() > avatar.getFitWidth() / avatar.getFitHeight()) {
						image = new Image(file.toURI().toString(), avatar.getFitWidth(), avatar.getFitHeight(), false,
								false);
					} else {
						image = new Image(file.toURI().toString(), avatar.getFitWidth(), avatar.getFitHeight(), true,
								false);
					}
					avatar.setImage(image);
					double margin = (avatar.getFitWidth() - image.getWidth()) / 2;
					avatar.setX(margin);
					BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
					try{
						File dir = new File("images/avatar.png");
						ImageIO.write(bi, "png", dir);
					} catch(IOException | IllegalArgumentException e){
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Informacja");
						alert.setHeaderText("");
						alert.setContentText("B³¹d: " + e.toString()
								+ ". Zapis pliku nie powiód³ siê.");
						alert.showAndWait();
					}
				}
			});
		} catch (ClassNotFoundException | IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("B³¹d: " + e.toString()
					+ ". Uszkodzony lub brak pliku u¿ytkownika! Usuñ lub przywróc plik user i uruchom aplikacje ponownie.");
			alert.showAndWait();
		}
	}

	public void loadUserDetails()
			throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException {
		User user = User.readUser();
		userData.setText("Witaj " + user.getFirstName() + " " + user.getLastName() + "!\n" + "Waga: "
				+ user.getLogs().get(user.getLogs().size() - 1).getWeight() + " kg\n" + "Wiek: " + user.calculateAge()
				+ " lat");
	}

	public void loadMain() {
		ImageView home = new ImageView("file:images/home.png");
		mainPage.getChildren().add(home);
		mainPage.setPadding(new Insets(10));
		mainPage.setPrefWidth(500);
		ScrollPane sp = new ScrollPane(mainPage);
		sp.setFitToHeight(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setFitToWidth(true);
		page.getChildren().add(sp);
		
		Image avatarImage = new Image("file:images/avatar.png");
		double margin = (avatar.getFitWidth() - avatarImage.getWidth()) / 2;
		avatar.setImage(avatarImage);
		avatar.setX(margin);
	}
}
