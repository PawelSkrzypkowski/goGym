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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import user.User;
/**
 * Klasa  - kontroler do obslugi okna Strony G³ownej aplikacji
 * @author Pawe³
 *
 */
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
	/**
	 * Metoda inicjalizujaca, ladujaca dane uzytkownika, strone glowna oraz dodajaca handlery
	 */
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
					double margin = (avatar.getFitWidth() - image.getWidth()) / 2;
					avatar.setX(margin);
					BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
					try{
						File dir = new File("avatar.png");
						ImageIO.write(bi, "png", dir);
						loadAvatar();
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
	/**
	 * Metoda ladujaca dane uzytkownika
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void loadUserDetails()
			throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException {
		User user = User.readUser();
		userData.setText("Witaj " + user.getFirstName() + " " + user.getLastName() + "!\n" + "Waga: "
				+ user.getLogs().get(user.getLogs().size() - 1).getWeight() + " kg\n" + "Wiek: " + user.calculateAge()
				+ " lat");
	}
	/**
	 * Metoda laduajca avatar
	 */
	public void loadAvatar(){
		File externalAvatar = new File("avatar.png");
		Image avatarImage;
		if(externalAvatar.exists())
			avatarImage = new Image("file:avatar.png");
		else
			avatarImage = new Image(getClass().getResource("/avatar.png").toExternalForm());
		double margin = (avatar.getFitWidth() - avatarImage.getWidth()) / 2;
		avatar.setImage(avatarImage);
		avatar.setX(margin);
	}
	/**
	 * Metoda ladujaca strone glowna
	 */
	public void loadMain() {
		ImageView home = new ImageView("/home.png");
		mainPage.getChildren().add(home);
		mainPage.setPadding(new Insets(10));
		mainPage.setPrefWidth(500);
		mainPage.setStyle("-fx-background-color:  #2e3539");
		mainPage.setSpacing(0);
		ScrollPane sp = new ScrollPane(mainPage);
		sp.setFitToHeight(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setFitToWidth(true);
		sp.setStyle("-fx-background-color:  #2e3539");
		page.getChildren().add(sp);
		Text text = new Text("Witaj w aplikacji goGym. Aplikacja umo¿liwia uk³adanie w³asnych treningów oraz przeprowadzanie ich w czasie rzeczywistym. Wspieranie siê goGym w codziennych treningach gwarantuje wykonanie zaplanowanej iloœci æwiczeñ, serii oraz przerw.\n"
				+ "Ponadto aplikacja umo¿liwia obliczanie podstawowych wskaŸników cia³a cz³owieka oraz œledzenie postêpów w perspektywie æwiczeñ, miesiêcy oraz w³asnego cia³a.\n\n Zapraszamy do æwiczenia i ¿yczymy wielu sukcesów!");
		text.setWrappingWidth(450);
		text.setFill(Color.WHITE);
		mainPage.getChildren().add(text);
		loadAvatar();
	}
}
