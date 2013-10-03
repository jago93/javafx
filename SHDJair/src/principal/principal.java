package principal;

import java.io.IOException;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class principal extends Application {

		@FXML private Button btnpersona;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root=
	FXMLLoader.load(getClass().getResource("../view/fxml/fxmlMenu.fxml"));
				
				Scene escena= new Scene(root);
				primaryStage.setScene(escena);
				primaryStage.setTitle("Carrera");
		
				primaryStage.show();
				
	escena.getStylesheets().add(principal.class.getResource("caspian.css").toExternalForm());
				

				
	}
	
	
	
	
	
	@FXML protected void ClickCarrera(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent carrera = FXMLLoader.load(getClass().getResource("../view/fxml/Carrera.fxml"));
		
		Scene scene = new Scene(carrera);
		scene.getStylesheets().add(principal.class.getResource("caspian.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Carreras");
		primaryStage.initModality(Modality.WINDOW_MODAL);
	//	primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}
	
	@FXML protected void ClickAlumno(ActionEvent evnt) throws IOException{
		Stage primaryStage = new Stage();
		Parent carrera = FXMLLoader.load(getClass().getResource("../view/fxml/Alumno.fxml"));
		
		Scene scene = new Scene(carrera);
		scene.getStylesheets().add(principal.class.getResource("caspian.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alumnos");
		primaryStage.initModality(Modality.WINDOW_MODAL);
	//	primaryStage.initOwner(((Node)evnt.getSource()).getScene().getWindow());
		primaryStage.show();
	}

	
	
	@FXML protected void ClickSalir(ActionEvent evnt) throws IOException{
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
