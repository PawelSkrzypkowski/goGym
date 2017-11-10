package application;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Klasa tworz�ca scene do pierwszego w��czenia aplikacji
 * @author Pawe�
 *
 */
public class FirstStart {
	Stage secondaryStage = new Stage();
	/**
	 * Metoda sprawdzajaca czy istenieje juz u�ytkownik
	 * @return true je�eli istnieje, false je�eli nie istnieje
	 */
	public static boolean checkIfUserExist(){
		boolean check = new File("user").isFile();
		return check;
	}
	/**
	 * Metoda tworz�ca scene
	 * @throws IOException
	 */
	public void newUser() throws IOException{
		if(checkIfUserExist() == false)
		{
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("/FirstStart.fxml"));
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			secondaryStage.setScene(scene);
			secondaryStage.setResizable(false);
			secondaryStage.setTitle("goGym - Rejestracja");
			secondaryStage.getIcons().add(new Image((getClass().getResource("/images/icon.png").toExternalForm())));
			secondaryStage.show();
		}
	}
	public void closeWindow(){
		secondaryStage.close();
	}
}
