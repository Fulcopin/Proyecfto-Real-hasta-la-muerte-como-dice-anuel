/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ec.edu.espol.eddproyecto.clases.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author fulco
 */
public class ContactImageViewsController {
    @FXML
    private Label a;

    @FXML
    private Label b;

    @FXML
    private Label c;

    @FXML
    private Label d;

    @FXML
    private Label e;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAfter;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNext;

    @FXML
    private ImageView contactImageView;

    @FXML
    private Label lastNameField;

    @FXML
    private Label nacimiento;

    @FXML
    private Label setAdrres;

    @FXML
    private Label setCountry;

    @FXML
    private Label setEmail;

    @FXML
    private Label setName;

    @FXML
    private Label setNumber;

    @FXML
    private Label workAddressField;

    @FXML
    private Label workEmailField;

    @FXML
    private Label workNumberField;

    private Person currConta;
    private Photo selectedPhoto;
    private User currUser;
    private ObservableList<Photo> observablePhotos;
    private int currentIndex;

    public void initializep(LinkedList<Photo> poto, Person actu) {
        System.out.println("Hola");
        actu=currConta;
        info();
        observablePhotos = FXCollections.observableArrayList(currConta.getPhotos());
        btnAfter.setOnAction(event -> showPreviousPhoto());
        btnNext.setOnAction(event -> showNextPhoto());
        
        // Inicializa la vista de la primera foto (si existe)
        if (!currConta.getPhotos().isEmpty()) {
            currentIndex = 0;
            showPhoto();
        }
       
        
    }

    private void showPhoto() {
        Photo currentPhoto = observablePhotos.get(currentIndex);
        contactImageView.setImage(new Image("file:" + currentPhoto.getFile()));
         
    }
    private void info() {
    
    if (currConta != null) {
        setName.setText(currConta.getName() != null ? currConta.getName() : "");
        setNumber.setText(currConta.getContactNumber() != null ? currConta.getContactNumber() : "");
        setCountry.setText(currConta.getCountry() != null ? currConta.getCountry() : "");
        setEmail.setText(currConta.getEmail() != null ? currConta.getEmail() : "");
        
            setAdrres.setText(currConta.getAddress().get(0) != null ?currConta.getAddress().get(0):"");
            
        if (currConta.getTipo().equals("Persona")) {
            a.setVisible(true);
             b.setVisible(true);
           c.setVisible(true);
          d.setVisible(true);
          e.setVisible(true);
            lastNameField.setVisible(true); 
            workNumberField.setVisible(true); 
            workEmailField.setVisible(true);
             workAddressField.setVisible(true);
             nacimiento.setVisible(true);
            lastNameField.setText(currConta.getLastname()!=null?currConta.getLastname():"");
            workNumberField.setText(currConta.getWorkNumber()!=null ?currConta.getWorkNumber():"");
            workEmailField.setText(currConta.getWorkEmail()!=null?currConta.getWorkEmail():"");
            workAddressField.setText(currConta.getWorkAddress().get(0)!=null?currConta.getWorkAddress().get(0):"");
              LocalDate birthDay = currConta.getBirthay();
              if (birthDay != null) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
             String fechaNacimiento = currConta.getBirthay().format(formatter);
            
              nacimiento.setText(fechaNacimiento);
         } else {
              nacimiento.setText("");
         }
        
        }   
            
        

       

        
    }
}

    @FXML
    private void showPreviousPhoto() {
        currentIndex = (currentIndex - 1 + observablePhotos.size()) % observablePhotos.size();
        showPhoto();
    }

    @FXML
    private void showNextPhoto() {
        currentIndex = (currentIndex + 1) % observablePhotos.size();
        showPhoto();
    }

    @FXML
    private void btnPress(ActionEvent e) {
        Button btn = (Button) e.getSource();
        if (btn == btnAdd) {
            addNewPhotoProcess();
        } else if (btn == btnDelete) {
            deletePhotoProcess();
        }
    }

    private void addNewPhotoProcess() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Agregar nueva foto");
            dialog.setHeaderText("Ingrese el nombre de la foto:");
            dialog.setContentText("Nombre:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                String photoName = result.get();
                Photo newPhoto = new Photo(photoName, selectedFile);
                currConta.getPhotos().add(newPhoto);
                observablePhotos.add(newPhoto); // Agrega la nueva foto a la lista observable
                currentIndex = observablePhotos.size() - 1; // Mueve el índice al final
                showPhoto();
            }
        }
    }

    private void deletePhotoProcess() {
        selectedPhoto=currConta.getPhoto(currentIndex);
    if (selectedPhoto != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar foto");
        alert.setHeaderText("¿Estás seguro de que quieres eliminar la foto?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (observablePhotos.contains(selectedPhoto)) {
                currConta.getPhotos().remove(selectedPhoto);
                int indexToRemove = observablePhotos.indexOf(selectedPhoto);
                observablePhotos.remove(selectedPhoto); // Elimina la foto de la lista observable

                // Ajusta currentIndex si es necesario
                


                showPhoto();
            }
        }
    }
}


    public void setUser(User currUser) {
        this.currUser = currUser;
    }

    public void setContact(Person contact) {
        this.currConta = contact;
    }

    @FXML
    private void handleCargarImagen(ActionEvent event) {
        // Aquí puedes manejar la carga de la imagen si es necesario
    }
}

