package application;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstStart {
	Stage secondaryStage = new Stage();
	public static boolean checkIfUserExist(){
		boolean czy = new File("user").isFile();
		return czy;
	}
	public void newUser() throws IOException{
		if(checkIfUserExist() == false)
		{
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("FirstStart.fxml"));
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			secondaryStage.setScene(scene);
			secondaryStage.setResizable(false);
			secondaryStage.show();
		}
	}
	public void closeWindow(){
		secondaryStage.close();
	}
}
