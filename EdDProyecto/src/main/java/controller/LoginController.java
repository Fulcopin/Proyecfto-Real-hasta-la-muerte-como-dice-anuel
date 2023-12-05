package controller;


import ec.edu.espol.eddproyecto.clases.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



public class LoginController {
	
	
	private Stage currStage;
	
	@FXML private TextField tfUsername, tfPassField;
	@FXML private PasswordField passField;
	@FXML private Button btnLogin, btnShowHide;
	@FXML private Label lblErrorMsg;
	@FXML private AnchorPane paneBtnLogin;

	
	private ArrayList<User> userList;
	private int totalUsers, currUserCount;
   
	public void start(Stage mainStage, boolean resetApp) {
		currStage = mainStage;
		mainStage.setTitle("Inicio");
		lblErrorMsg.setVisible(false);

		btnLogin.requestFocus();
		
		
		
		tfPassField.setVisible(false);
		tfPassField.setText(passField.getText());
		
		
		
		importUsersList();

		passField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					loginProcess();
				}
			}
		});

	}

	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		if (btn == btnLogin)
			loginProcess();
		else if (btn == btnShowHide) {
			showHideProcess();
		}
	}
	
	
	private void loginProcess() {

		String myUsername = tfUsername.getText();
		
		String myPassword = null;
		if (tfPassField.isVisible()) {
			myPassword = tfPassField.getText();
		}
		else if (passField.isVisible()) {
			myPassword = passField.getText();
		}
		
		
		
			lblErrorMsg.setVisible(false);
			User validUser = null;
			for (User u : userList) {
				if (myUsername.equals(u.getUsername())) {
					if (myPassword.equals(u.getPassword())) {
						validUser = u;
					} else {
						validUser = null;
						break;
					
				}
			}
			
			
			if (validUser == null) {
				lblErrorMsg.setVisible(true);
			}
			else
			{
				openListContact(validUser);
			}
			
		}
		
		
		
		
		

		

	}

	
	private void showHideProcess() {
		if (passField.isVisible()) {
			
			tfPassField.setText(passField.getText());
			passField.setVisible(false);
			tfPassField.setVisible(true);
			btnShowHide.setText("E");
		}
		else 
		{
			
			passField.setText(tfPassField.getText());
			passField.setVisible(true);
			tfPassField.setVisible(false);
			btnShowHide.setText("M");
		}
	}
	
	private void openListContact(User currUser) {
		
                         try {

			FXMLLoader contactLoader = new FXMLLoader();
			contactLoader.setLocation(getClass().getResource("/fxml/ContactLibrary.fxml"));
			AnchorPane LibraryContact = (AnchorPane) contactLoader.load();

			Stage LibraryContactStage = new Stage();
			LibraryContactStage.setTitle("Contactos");
			LibraryContactStage.initModality(Modality.WINDOW_MODAL);
			LibraryContactStage.initOwner(currStage);
			LibraryContactStage.setResizable(false);
			Scene scene = new Scene(LibraryContact);
			LibraryContactStage.setScene(scene);

			String globalCSS = getClass().getResource("/css/ZGlobalElementsCSS.css").toExternalForm();
			String uniqueCSS = getClass().getResource("/css/ContactPageCSS.css").toExternalForm();
			scene.getStylesheets().addAll(globalCSS,uniqueCSS);
			

			ContactLibraryController lictContactController = contactLoader.getController();
			currUser.logout = false;
			lictContactController.start(LibraryContactStage,currUser, userList, totalUsers, currUserCount );
			

		         
			currStage.hide();
			LibraryContactStage.showAndWait();
			currStage.show();
			


		} catch (IOException e) {
			e.printStackTrace();  
		}
			
		     
			
	}
	
		
	
	
        


	public void exportProgramData() {
			
	
		
		try {
			File usersFile = new File("usersList.txt");
			FileOutputStream userOut = new FileOutputStream(usersFile);
			ObjectOutputStream oout = new ObjectOutputStream(userOut);
			for (User u : userList) {
				oout.writeObject(u);
			}
			oout.close();
			
	
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
	}
	
	

	

	
	public void importUsersList() {

            userList = (ArrayList<User>)ArrayList.deserialize("src/main/resources/user.ser");
	}
	
	
}
