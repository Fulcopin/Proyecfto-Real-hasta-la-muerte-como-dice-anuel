package controller;

import ec.edu.espol.eddproyecto.clases.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class UserMenuController {
	
	@FXML private Button btnUShowHide, btnUEditPass, btnUSavePass, btnLogout, btnUCancel;
	@FXML private TextField tfUsername, tfPass;
	@FXML private PasswordField passField;
	int state = 0;
	
	private Stage currStage;
	private User currUser;
	private ArrayList<User> userList;
	private int totalUsers, currUserCount;
	
	public void start(Stage mainStage, User currUser, ArrayList<User> userList, int totalUsers, int currUserCount) {
		mainStage.setTitle("Menu Usuario");
		this.currStage = mainStage;
		this.currUser = currUser;
		this.userList = userList;
		this.totalUsers = totalUsers;
		this.currUserCount = currUserCount;
		tfUsername.setText(currUser.getUsername());
		tfPass.setText(currUser.getPassword());
		passField.setText(currUser.getPassword());
		passField.setDisable(true);
		tfPass.setDisable(true);
		tfPass.setVisible(false);
		btnUSavePass.setVisible(false);
	}
	
	@FXML 
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();

		if (btn == btnUShowHide) {
			showHideProcess();
		} else if (btn == btnUEditPass) {
			editPassProcess();
		} else if (btn == btnUSavePass) {
			savePassProcess(); 
		} else if (btn == btnLogout) {
			logoutProcess();
		} else if (btn == btnUCancel ) {
			cancelProcess();
		}
	}

	private void cancelProcess() {
		if (state == 0) {
			currStage.close();
		}
		else {
			
			state = 0;
			
			tfPass.setText(currUser.getPassword());
			passField.setText(currUser.getPassword());
		}
	}

	private void logoutProcess() {
		try {
			
			FileWriter fw = new FileWriter("appStats.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			String x = totalUsers+"\n";
			String y = currUserCount+"\n";
			bw.write(x);
			bw.write(y);

			bw.close();
			fw.close();			
		}
		catch(Exception e){
			e.printStackTrace();  
		}
		
		
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
		
		currUser.logout = true;
		currStage.close();
	}

	private void savePassProcess() {
		state = 0;
		btnUEditPass.setVisible(true);
		tfPass.setDisable(true);
		passField.setDisable(true);
		btnUSavePass.setVisible(false);
		
		if (passField.isVisible())
			currUser.setPassword(passField.getText());
		else
			currUser.setPassword(tfPass.getText());
		
		
	}

	private void editPassProcess() {
		state = 1;
		btnUEditPass.setVisible(false);
		btnUSavePass.setVisible(true);
		btnUSavePass.requestFocus();
		tfPass.setDisable(false);
		passField.setDisable(false);
		
		
	}

	private void showHideProcess() {
		if (passField.isVisible()) {
			
			tfPass.setText(passField.getText());
			passField.setVisible(false);
			tfPass.setVisible(true);
			btnUShowHide.setText("M");
		}
		else 
		{
			
			passField.setText(tfPass.getText());
			passField.setVisible(true);
			tfPass.setVisible(false);
			btnUShowHide.setText("E");
			
		}
	}

	
	
	


}
