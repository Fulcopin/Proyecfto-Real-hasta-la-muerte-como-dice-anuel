package com.mycompany.javaproyect;

import controller.*;
import ec.edu.espol.eddproyecto.clases.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * JavaFX App
 */
public class App extends Application {

    private LoginController loginController;
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(getClass().getResource("/fxml/LoginPage.fxml"));
		AnchorPane root = (AnchorPane) loginLoader.load();
		loginController = loginLoader.getController();
		boolean resetApp = false;

		resetApp = true; 
		
		
		loginController.start(primaryStage, resetApp);
		
		
                
		Scene scene = new Scene(root);
		String globalCSS = getClass().getResource("/css/ZGlobalElementsCSS.css").toExternalForm();
                System.out.println(getClass().getResource("/css/ZGlobalElementsCSS.css").toExternalForm());
		
		scene.getStylesheets().addAll(globalCSS);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void stop(){
		
	    loginController.exportProgramData();
	    
	   
	}
}