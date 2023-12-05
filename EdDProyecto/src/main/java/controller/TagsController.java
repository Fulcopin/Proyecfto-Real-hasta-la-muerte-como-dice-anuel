package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import ec.edu.espol.eddproyecto.clases.ArrayList;
import ec.edu.espol.eddproyecto.clases.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;


public class TagsController {

    @FXML private Button btnAdd, btnRemove;
    @FXML private ListView<String> listTags;
    private ArrayList<String> tagsContact;
    private ObservableList<String> obsList;
    private Person currPerso;
    public void start(Stage tagsViewStage,Person p) {
        // Inicializar la lista observable
        currPerso=p;
        /*this.currUser = user;
		
		userTagTypes = currUser.getAllTagTypes();
		
		ArrayList<TagType> listArr = new ArrayList<TagType>();
		obsList = FXCollections.observableList(listArr);
		
		
		for (TagType t: userTagTypes) {
			obsList.add(t);
		}
		listTags.setItems(obsList); TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Agregar Etiqueta");
		dialog.setHeaderText("Crear categorias");
		dialog.setContentText("Nombre etiqueta:");

		
		Optional<String> result = dialog.showAndWait();
		
		
		if (result.isPresent()) {
			String newTypeName = result.get();
			
			if (!isDuplicateTag(newTypeName)) {
				TagType x = new TagType (result.get());
				
				userTagTypes.add(x);
				currUser.updateAddTags();
				obsList.add(x);
				
				listTags.refresh();
				listTags.getSelectionModel().select(listTags.getItems().indexOf(x));
				listTags.requestFocus();
				
				
			}
			else
			{
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Etiqueta duplicada!");
				alert.setContentText("Ya existe intente de nuevo");

				alert.showAndWait();
				addTagTypeProcess();
			}
			
			
			
		}
		else{
			
		}
	}
	*/
        tagsContact = currPerso.getTags();
        
		
        obsList = FXCollections.observableArrayList(tagsContact);
        listTags.setItems(obsList);
        listTags.getSelectionModel().select(0);
    }

    @FXML
    public void btnPress(ActionEvent e) {
        Button btn = (Button) e.getSource();

        if (btn == btnAdd) {
            addTagProcess();
        } else if (btn == btnRemove) {
            removeTagProcess();
        }
    }

    private void addTagProcess() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Etiqueta");
        
        dialog.setContentText("Nombre etiqueta:");

        Optional<String> result = dialog.showAndWait();
          
        if (result.isPresent() && !result.get().isEmpty()) {
            String newTag = result.get();

            if (!isDuplicate(newTag)) {
                obsList.add(newTag);
                currPerso.getTags().add(newTag);
		
                listTags.refresh();
              
		
            } else {
                showAlert("Etiqueta duplicada", "Ya existe la etiqueta: " + newTag);
                addTagProcess();  // Llamada recursiva si la etiqueta está duplicada
            }
        }
    }

    private void removeTagProcess() {
        int selectedIndex = listTags.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            obsList.remove(selectedIndex);
            listTags.refresh();
        } else {
            showAlert("Selección no válida", "Por favor, seleccione una etiqueta para eliminar.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private boolean isDuplicate(String newTypeName) {
		for(String t: currPerso.getTags()) {
			if (t.equals(newTypeName)) {
				return true;
			}
		}
		return false;
	}
}
