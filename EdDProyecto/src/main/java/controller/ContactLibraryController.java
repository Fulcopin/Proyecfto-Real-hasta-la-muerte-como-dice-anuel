/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ec.edu.espol.eddproyecto.clases.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fulco
 */
public class ContactLibraryController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenContact;

    @FXML
    private Button btnRenameContact;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnTag;

    @FXML
    private Button btnUser;

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scrPane;
    @FXML
    private AnchorPane prevSelPane;
    
    private Stage currStage;
    private User currUser;
    private ArrayList<User> userList;
    private int totalUsers, currUserCount;
    private Person selectedPerson;
    private AnchorPane selPane;
    int totalContat = 0;
    
    public void start(Stage mainStage, User currUser, ArrayList<User> userList, int totalUsers, int currUserCount) {
		mainStage.setTitle("Contactos");
		currStage = mainStage;
		this.currUser = currUser;
		this.userList = userList;
		this.totalUsers = totalUsers;
		this.currUserCount = currUserCount;
		
		btnUser.setPrefWidth(125);
		btnUser.setMinWidth(125);
		btnUser.setMaxWidth(125);
		
		
		scrPane.requestFocus();
		
		initializeGraphics();
		restoreContectData();
		buttonVisibility();
	}
	
	
	@FXML 
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();

		if (btn == btnAdd) {
			addContactProcess();
		} else if (btn == btnDelete) {
			deleteContact();
		} else if (btn == btnRenameContact) {
			editContactProcess(); 
		} else if (btn == btnUser ) {
			userButton();
		} else if (btn == btnOpenContact) {
			ViewaContactList(); 
		}  else if (btn == btnTag) {
			 Tags();
                        
    	
    	}else if (btn == btnSearch) {
    		SearchAdvence();
    	} 
	}

@FXML


    

public void addContactProcess() {
    // Crear el cuadro de diálogo personalizado
    Dialog<Person> dialog = new Dialog<>();
    dialog.setTitle("Agregar Contacto");
    dialog.setHeaderText("Añade un contacto a tu libreta de direcciones");

    // Configurar los botones del cuadro de diálogo
    ButtonType addButton = new ButtonType("Añadir", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

    // Crear los campos del formulario dentro del cuadro de diálogo
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    // Agregar el ComboBox para la selección de tipo de contacto
    // ComboBox para el tipo de contacto
    ComboBox<String> contactTypeComboBox = new ComboBox<>();
    contactTypeComboBox.getItems().addAll("Contacto de Empresa", "Contacto de Persona");
    contactTypeComboBox.setValue("Contacto de Empresa"); // Valor predeterminado
    grid.add(new Label("Tipo de Contacto:"), 0, 0);
    grid.add(contactTypeComboBox, 1, 0);


    TextField nameField = new TextField();
    nameField.setPromptText("Nombre");
    grid.add(new Label("Nombre:"), 0, 1);
    grid.add(nameField, 1, 1);

   TextField numberField = new TextField();
   numberField.setPromptText("Número de contacto");
   grid.add(new Label("Número de contacto:"), 0, 2);
   grid.add(numberField, 1, 2);
 
   TextField emailField = new TextField();
   emailField.setPromptText("Correo electrónico");
   grid.add(new Label("Correo electrónico:"), 0, 3);
   grid.add(emailField, 1, 3);


   TextField addressField = new TextField();
   addressField.setPromptText("Dirección");
   grid.add(new Label("Dirección:"), 0, 4);
   grid.add(addressField, 1, 4);


   TextField countryField = new TextField();
   countryField.setPromptText("País");
   grid.add(new Label("País:"), 0, 5);
   grid.add(countryField, 1, 5);

    
   
   
    TextField lastnameField = new TextField();
    lastnameField.setPromptText("Apellido");
    grid.add(new Label("Apellido:"), 0, 6);
    grid.add(lastnameField, 1, 6);

    TextField workNumberField = new TextField();
    workNumberField.setPromptText("Número de trabajo");
    grid.add(new Label("Número de trabajo:"), 0, 7);
    grid.add(workNumberField, 1, 7);
    
    TextField workEmail = new TextField();
    workEmail.setPromptText("Correo Trabajo");
    grid.add(new Label("Correo de trabajo:"), 0, 8);
    grid.add(workEmail, 1, 8);

    
    TextField workAddres = new TextField();
    workAddres.setPromptText("Direccion de Trabajo");
    grid.add(new Label("Direccion de trabajoo:"), 0, 9);
    grid.add(workAddres, 1, 9);
    
    
    DatePicker birthDatePicker = new DatePicker();
    birthDatePicker.setPromptText("Fecha de Nacimiento");
    grid.add(new Label("Fecha de Nacimiento:"), 0, 10);
    grid.add(birthDatePicker, 1, 10);

    contactTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
        // Mostrar u ocultar campos según la selección
        if("Contacto de Empresa".equals(newValue)){
        boolean isCompany = "Contacto de Empresa".equals(newValue);

        
        nameField.setVisible(isCompany);
        emailField.setVisible(isCompany);
        numberField.setVisible(isCompany);
        addressField.setVisible(isCompany);
        countryField.setVisible(isCompany);
        
        
        lastnameField.setVisible(!isCompany);
        workNumberField.setVisible(!isCompany);
        workEmail.setVisible(!isCompany);
        workAddres.setVisible(!isCompany);
        birthDatePicker.setVisible(!isCompany);
        }
        boolean isCompany = !("Contacto de Empresa".equals(newValue));
        lastnameField.setVisible(isCompany);
        workNumberField.setVisible(isCompany);
        workEmail.setVisible(isCompany);
        workAddres.setVisible(isCompany);
        birthDatePicker.setVisible(isCompany);
        
    });
    dialog.getDialogPane().setContent(grid);

    
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButton) {
            String contactType = contactTypeComboBox.getValue();
            String name = nameField.getText().trim();
            LinkedList<Photo> photos = new LinkedList<>();
            ArrayList<String> addresses = new ArrayList<>();
            addresses.add(addressField.getText().trim());
            String country=countryField.getText().trim();
            String number=numberField.getText().trim();
            String  email=emailField.getText().trim();
            if ("Contacto de Persona".equals(contactType)) {
             System.out.println(contactType);   
            ArrayList<String> workAddresses = new ArrayList<>();
            workAddresses.add(workAddres.getText().trim());
            LocalDate selectedDate = birthDatePicker.getValue();
            String lastname=lastnameField.getText().trim();
            //Person(String name, String lastname,String country, String contactNumber,LinkedList<String> photos, String workNumber, String email, String workEmail, ArrayList<String> address, ArrayList<String> workAddress,LocalDate birthay)
            return new Person(
                    name,
                    lastname,
                    country,
                    number,
                    photos,
                    workNumberField.getText().trim(),
                   email,
                    workEmail.getText().trim(),
                    addresses,
                    workAddresses,
                    selectedDate
            );
            } else {
                //uper(name,country, contactNumber, email, photos, address);
                System.out.println(contactType); 
                return  new Person(
                    name,
                   country,
                    number,
                    email,
                    photos,
                    addresses
                    
            );
            }
        }
        return null;
    });

    // Mostrar el cuadro de diálogo y procesar el resultado
    Optional<Person> result = dialog.showAndWait();

    result.ifPresent(newPerson -> {
        if (!isDuplicateContact(newPerson.getContactNumber())) {
            // Añadir el nuevo contacto al usuario
            currUser.addNewContact(newPerson);
            createPe(newPerson);
            System.out.println("Contacto editado: " + newPerson.getBirthay());
            // También puedes llamar a un método que maneje la interfaz gráfica para mostrar el nuevo contacto
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Contacto duplicado");
            alert.setHeaderText("Contacto duplicado"); 
            alert.setContentText("Ya existe el contacto, intente de nuevo");

            alert.showAndWait();
            addContactProcess();
        }
    });

    buttonVisibility();
}




	private void deleteContact() {
    if (selectedPerson != null) {
        String contactNumber = selectedPerson.getContactNumber();
        System.out.println(contactNumber);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Seguro que quieres eliminar a " + contactNumber + " " + selectedPerson.getName() + " - " + selectedPerson.getLastname(), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            currUser.getAllContact().remove(flow.getChildren().indexOf(selPane));
            int currSelected = flow.getChildren().indexOf(selPane);
            flow.getChildren().remove(currSelected);
            selPane = null;
        }
    } 

    buttonVisibility();
}
private void editContactProcess() {
    if (selectedPerson != null) {
        // Crear el cuadro de diálogo para la edición
        
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Editar Contacto");
        dialog.setHeaderText("Edita los detalles del contacto");

        // Configuramos los botones del cuadro de diálogo
        ButtonType editButton = new ButtonType("Guardar Cambios", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(editButton, ButtonType.CANCEL);

        // Creamos los campos del formulario dentro del cuadro de diálogo
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        TextField nameField = new TextField(selectedPerson.getName());
        nameField.setPromptText("Nombre");

        TextField numberField = new TextField(selectedPerson.getContactNumber());
        numberField.setPromptText("Número de contacto");
        
        TextField countryField = new TextField(selectedPerson.getCountry());
        countryField.setPromptText("País");
        
        TextField emailField = new TextField(selectedPerson.getEmail());
        emailField.setPromptText("Correo electrónico");
        
        TextField addressField = new TextField(selectedPerson.getAddress().isEmpty() ? "" : selectedPerson.getAddress().get(0));
        addressField.setPromptText("Dirección");
        
        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Número de contacto:"), 0, 1);
        grid.add(numberField, 1, 1);
        grid.add(new Label("País:"), 0, 2);
        grid.add(countryField, 1, 2);
        grid.add(new Label("Correo electrónico:"), 0, 3);
        grid.add(emailField, 1, 3);
        grid.add(new Label("Dirección:"), 0, 4);
        grid.add(addressField, 1, 4);
        
        dialog.getDialogPane().setContent(grid);
        TextField lastnameField = new TextField();
        TextField workNumberField = new TextField();
        TextField workEmailField = new TextField();
        TextField workAddressField = new TextField();
        DatePicker birthDatePicker = new DatePicker();
        if(selectedPerson.getTipo().equals("Persona")){
         lastnameField.setText(selectedPerson.getLastname());
         workNumberField.setText(selectedPerson.getWorkNumber());
         workEmailField.setText(selectedPerson.getWorkEmail());
          workAddressField.setText(selectedPerson.getWorkAddress().isEmpty() ? "" : selectedPerson.getWorkAddress().get(0));
          birthDatePicker.setValue(selectedPerson.getBirthay());
        lastnameField.setPromptText("Apellido");
        
        workNumberField.setPromptText("Número de trabajo");
        
        workEmailField.setPromptText("Correo electrónico de trabajo");
               workAddressField.setPromptText("Dirección de trabajo");
       
        birthDatePicker.setPromptText("Fecha de Nacimiento"); 
        grid.add(new Label("Apellido:"), 0, 5);
        grid.add(lastnameField, 1, 5);
        
        grid.add(new Label("Número de trabajo:"), 0, 6);
        grid.add(workNumberField, 1, 6);
        
        grid.add(new Label("Correo electrónico de trabajo:"), 0, 7);
        grid.add(workEmailField, 1, 7);

        
        
        grid.add(new Label("Dirección de trabajo:"), 0, 8);
        grid.add(workAddressField, 1, 8);
        
        grid.add(new Label("Fecha de Nacimiento:"), 0, 9);
        grid.add(birthDatePicker, 1, 9);
        dialog.getDialogPane().setContent(grid);
        
        
        
       
        
        
        }
        
        // Configuramos el resultado del cuadro de diálogo
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButton) {
                if(selectedPerson.getTipo().equals("Empresa")){
                selectedPerson.setName(nameField.getText().trim());
                
                selectedPerson.setContactNumber(numberField.getText().trim());
                
                selectedPerson.setEmail(emailField.getText().trim());
                

                
                ArrayList<String> addresses = new ArrayList<>();
                addresses.add(addressField.getText().trim());
                selectedPerson.setAddress(addresses);
                selectedPerson.setCountry(countryField.getText().trim());
                }else{
                selectedPerson.setName(nameField.getText().trim());
                selectedPerson.setLastname(lastnameField.getText().trim());
                selectedPerson.setContactNumber(numberField.getText().trim());
                selectedPerson.setWorkNumber(workNumberField.getText().trim());
                selectedPerson.setEmail(emailField.getText().trim());
                selectedPerson.setWorkEmail(workEmailField.getText().trim());
                selectedPerson.setCountry(countryField.getText().trim());
             
                ArrayList<String> addresses = new ArrayList<>();
                addresses.add(addressField.getText().trim());
                selectedPerson.setAddress(addresses);
                 selectedPerson.setBirthay(birthDatePicker.getValue());
                ArrayList<String> workAddresses = new ArrayList<>();
                workAddresses.add(workAddressField.getText().trim());
                selectedPerson.setWorkAddress(workAddresses);
                }

                return selectedPerson;
            }
            return null;
        });

        // Mostrar el cuadro de diálogo y procesar el resultado
        Optional<Person> result = dialog.showAndWait();

        result.ifPresent(editedPerson -> {
            // Aquí podrías realizar acciones adicionales si es necesario
            System.out.println("Contacto editado: " + editedPerson.getName());
            updateContactView(editedPerson);
        });

        
    } else {
        System.out.println("La persona editada");
    }
}

private void updateContactView(Person editedPerson) {
    for (Node node : flow.getChildren()) {
        if (node instanceof AnchorPane) {
            AnchorPane anchorPane = (AnchorPane) node;

            // Obtén la persona almacenada como userData
            Person personInPane = (Person) anchorPane.getUserData();

            // Compara las personas
            if (personInPane != null && personInPane.equals(editedPerson)) {
                // Actualiza el AnchorPane con los nuevos detalles

                // Obtén las etiquetas directamente desde el AnchorPane
                ObservableList<Node> children = anchorPane.getChildren();

                Label lblcontact = (Label) children.get(1);
                Label lblNumOfPics = (Label) children.get(2);
                Label lblFrom = (Label) children.get(3);
                

                // Actualiza las etiquetas
                lblcontact.setText("Nombre:"+editedPerson.getName() + " " + editedPerson.getLastname());
                
                lblFrom.setText("Contact Number: " + editedPerson.getContactNumber());
                lblNumOfPics.setText("Email: " + editedPerson.getEmail());

               

                break; 
            }
        }
    }
}


	
	
	
 private void ViewaContactList() {
    try {
        // Cargar el FXML del ContactListController
        FXMLLoader contactLoader = new FXMLLoader();
        contactLoader.setLocation(getClass().getResource("/fxml/ContactListController.fxml"));
        AnchorPane contactRoot = (AnchorPane) contactLoader.load();

        // Obtener el controlador de la lista de contactos (ContactListController)
        ContactListController contactListController = contactLoader.getController();

        // Inicializar la lista de contactos en el controlador de la lista de contactos
        

        // Crear la escena y configurar CSS
        Scene contactScene = new Scene(contactRoot);
        String globalCSS = getClass().getResource("/css/ViewsContact.css").toExternalForm();
        contactScene.getStylesheets().addAll(globalCSS);
  contactListController.initializeContacts(currUser.getAllContact(),contactScene);
        // Crear y mostrar la ventana de lista de contactos
        Stage contactStage = new Stage();
        contactStage.setTitle("Lista de Contactos de " + currUser.getUsername());
        contactStage.initModality(Modality.WINDOW_MODAL);
        contactStage.initOwner(currStage);
        contactStage.setScene(contactScene);
        contactStage.showAndWait();
        
        // Puedes realizar acciones adicionales después de cerrar la ventana si es necesario
    } catch (IOException e) {
        e.printStackTrace();
        // Manejar la excepción según tus necesidades
    }
}
 	
 	


	
	private void userButton() {
		try {
		
			FXMLLoader user = new FXMLLoader();
			user.setLocation(getClass().getResource("/fxml/UserDetails.fxml"));
			AnchorPane root = (AnchorPane) user.load();
			
			
			Stage userStage = new Stage();
			userStage.initModality(Modality.WINDOW_MODAL);
			userStage.initOwner(currStage);
			userStage.setResizable(false);
			Scene scene = new Scene(root);
			userStage.setScene(scene);
			
			
			String globalCSS = getClass().getResource("/css/ZGlobalElementsCSS.css").toExternalForm();
			
			scene.getStylesheets().addAll(globalCSS);
			
			
			boolean isLogoffClicked = false;
			
			
			UserMenuController userMenuController = user.getController();
				userMenuController.start(userStage, currUser, userList, totalUsers, currUserCount);
				
			userStage.showAndWait();
			
			if (currUser.logout)
				currStage.close();
		} catch (IOException e) {
			e.printStackTrace();  		}
	}
 	
	private void createPe(Person p) {
		AnchorPane anchTile = new AnchorPane();
                anchTile.setMinWidth(300);
                anchTile.setMaxWidth(300);
                anchTile.setMinHeight(200);  // Aumenté la altura para dar más espacio a las etiquetas
                anchTile.setMaxHeight(200);

                StackPane stackPane = new StackPane();
                stackPane.setLayoutX(10);  // Añadí un pequeño espacio desde el borde izquierdo
                stackPane.setLayoutY(10);  // Añadí un pequeño espacio desde el borde superior
                stackPane.setMinWidth(180);  // Ajusté el ancho para que sea un poco más grande
                stackPane.setMaxWidth(180);
                stackPane.setMinHeight(180);
                stackPane.setMaxHeight(180);
		
		ImageView imgView = new ImageView();
		imgView.setX(1);
		imgView.setY(1);
		
		if (p.getPhotos().size() > 0) {
			try {
                            String url=p.getPhotos().get(0).getFile().toURI().toURL().toString();
                            
              Image image = new Image(url);
				
                              
			 	imgView.setImage(image);
			} catch (IOException e) {
				e.printStackTrace();  
			}
		}
		else {
			imgView.setImage(new Image(getClass().getResourceAsStream("/resources/person.png")));
		}
		imgView.setViewport(new Rectangle2D(170, 170, 0, 0));
		imgView.setPreserveRatio(true);
		imgView.setFitWidth(180);
		imgView.setFitHeight(180);
		
		
		stackPane.getChildren().add(imgView);
		
		StackPane.setAlignment(imgView, Pos.CENTER);
		
		
		
		Label lblContactN = createLabel("Nombre"+p.getName() , 200, 15, 150, 150, 17, 17);
                Label lblNumOfPics = createLabel("Email: "+p.getEmail(), 200, 40, 150, 150, 17, 17);
                Label lblFrom = createLabel("Telefono: "+p.getContactNumber(), 200, 100, 34, 34, 17, 17);
                
		
		

		
		
		anchTile.getChildren().add(stackPane);
                anchTile.getChildren().add(lblContactN);
                anchTile.getChildren().add(lblFrom);
		anchTile.getChildren().add(lblNumOfPics);
		
		
		anchTile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectPer(event, anchTile);
			}
		});

		imgView.toFront();
		flow.getChildren().add(anchTile);
	}

	
	private void selectPer(MouseEvent event, AnchorPane anchTile) {
		if (selPane != anchTile) {
			prevSelPane = selPane;
			selPane = anchTile;
		}
		anchTile.setStyle("-fx-border-color: black; -fx-alignment: top-center");
		anchTile.setEffect(new Glow(0.5));
		
		if (prevSelPane != null) {
			if (prevSelPane != selPane) {
				prevSelPane.setStyle("-fx-alignment: top-center");
				prevSelPane.setEffect(new Glow(0));
			}
		} else {
			prevSelPane = selPane;
		}
		
		
		selectedPerson = currUser.getContact(flow.getChildren().indexOf(selPane));
		buttonVisibility();
		event.consume();
		
		
		if (event.getClickCount() == 2) {
			if (selPane != anchTile) {
				prevSelPane = selPane;
				selPane = anchTile;
			}
			anchTile.setStyle("-fx-border-color: black; -fx-alignment: top-center");
			anchTile.setEffect(new Glow(0.5));
			
			if (prevSelPane != null) {
				if (prevSelPane != selPane) {
					prevSelPane.setStyle("-fx-alignment: top-center");
					prevSelPane.setEffect(new Glow(0));
				}
			} else {
				prevSelPane = selPane;
			}
			
			selectedPerson = currUser.getContact(flow.getChildren().indexOf(selPane));
			buttonVisibility();
			event.consume();
			openContact();
                        for(String h:selectedPerson.getTags()){
                            System.out.println(h);
                        }
			
		}
	}
	
	
	
        boolean isDuplicateContact(String c) {
		for (Person a : currUser.getAllContact()) {
			if (a.getContactNumber().equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	private void restoreContectData() {
		flow.getChildren().clear();
		LinkedList<Person> arrCont = currUser.getAllContact();
		selPane = null;
		prevSelPane = null;
		for(Person p : arrCont) {
			createPe(p);
			totalContat++;
		}
		buttonVisibility();
	}
	private void initializeGraphics() {
		Image userStock = new Image(getClass().getResourceAsStream("/resources/userStock.png"));
                
		ImageView userStockView = new ImageView(userStock);
		userStockView.setViewport(new Rectangle2D(40, 40, 0, 0));
		userStockView.setFitHeight(40);
		userStockView.setFitWidth(40);
		btnUser.setGraphic(userStockView);
		btnUser.setContentDisplay(ContentDisplay.LEFT);
		btnUser.setText(currUser.getUsername());
		btnUser.setMinWidth(150);
		
		Image Tagvi = new Image(getClass().getResourceAsStream("/resources/tag.png"));
		ImageView EtiTags = new ImageView(Tagvi);
		EtiTags.setViewport(new Rectangle2D(50,50,0,0));
		EtiTags.setFitHeight(50);
		EtiTags.setFitWidth(50);
		
		btnTag.setGraphic(EtiTags);
		btnTag.setContentDisplay(ContentDisplay.TOP);
		btnTag.setText("Lista Etiqueta");
		

		
		
		Image Search = new Image(getClass().getResourceAsStream("/resources/ico.jpg"));
		ImageView vieSearch = new ImageView(Search);
		vieSearch.setViewport(new Rectangle2D(50,50,0,0));
		vieSearch.setFitHeight(50);
		vieSearch.setFitWidth(50);
		btnSearch.setGraphic(vieSearch);
		btnSearch.setContentDisplay(ContentDisplay.TOP);
		btnSearch.setText("Buscar");
		
		Image imgAddContact = new Image(getClass().getResourceAsStream("/resources/add.png"));
		ImageView viewAddContact = new ImageView(imgAddContact);
		viewAddContact.setViewport(new Rectangle2D(50,50,0,0));
		viewAddContact.setFitHeight(50);
		viewAddContact.setFitWidth(50);
		btnAdd.setGraphic(viewAddContact);
		btnAdd.setContentDisplay(ContentDisplay.TOP);
		btnAdd.setText("Añadir");
		
		Image viewEditContact = new Image(getClass().getResourceAsStream("/resources/edit.png"));
		ImageView vieEditCont = new ImageView(viewEditContact);
		vieEditCont.setViewport(new Rectangle2D(50,50,0,0));
		vieEditCont.setFitHeight(50);
		vieEditCont.setFitWidth(50);
		btnRenameContact.setGraphic(vieEditCont);
		btnRenameContact.setContentDisplay(ContentDisplay.TOP);
		btnRenameContact.setText("Renombrar");
		
		Image viewDeleteContact = new Image(getClass().getResourceAsStream("/resources/delete_1.png"));
		ImageView contactDeleteIma = new ImageView(viewDeleteContact);
		contactDeleteIma.setViewport(new Rectangle2D(50,50,0,0));
		contactDeleteIma.setFitHeight(50);
		contactDeleteIma.setFitWidth(50);
		btnDelete.setGraphic(contactDeleteIma);
		btnDelete.setContentDisplay(ContentDisplay.TOP);
		btnDelete.setText("Eliminar");
                
                Image viewOpenList = new Image(getClass().getResourceAsStream("/resources/list.png"));
		ImageView vieList = new ImageView(viewOpenList);
		vieList.setViewport(new Rectangle2D(50,50,0,0));
		vieList.setFitHeight(50);
		vieList.setFitWidth(50);
		btnOpenContact.setGraphic(vieList);
		btnOpenContact.setContentDisplay(ContentDisplay.TOP);
		btnOpenContact.setText("Lista");

	}

	
	private void buttonVisibility() {
		if (flow.getChildren().size() > 0 && selPane != null) {
			
			btnRenameContact.setDisable(false);
			btnDelete.setDisable(false);
			
		}
		else {			
			
			btnRenameContact.setDisable(true);
			btnDelete.setDisable(true);
			
		}
		
		
	}
	
	
	private Label createLabel(String setText, int setLayoutX, int setLayoutY, int setMinWidth, int setMaxWidth, int setMinHeight, int setMaxHeight) {
		Label x = new Label(setText);
		x.setLayoutX(setLayoutX);
		x.setLayoutY(setLayoutY);
		x.setMinWidth(setMinWidth);
		x.setMaxWidth(setMaxWidth);
		x.setMinHeight(setMinHeight);
		x.setMaxHeight(setMaxHeight);

		return x;
	}
	
	
	public void exitApplication(ActionEvent event) {
		
	    Platform.exit();
	}
	public void openContact() {
    try {
        FXMLLoader viewsPhoto = new FXMLLoader();
        viewsPhoto.setLocation(getClass().getResource("/fxml/ContactImageViewsController.fxml"));
        AnchorPane root = (AnchorPane) viewsPhoto.load();

        ContactImageViewsController viewContact = viewsPhoto.getController();
       viewContact.setUser(currUser);
        viewContact.setContact(selectedPerson); 
        viewContact.initializep(selectedPerson.getPhotos(), selectedPerson);

        Scene viewScene = new Scene(root);
        String CSS = getClass().getResource("/css/ViewsCSS.css").toExternalForm();
        viewScene.getStylesheets().addAll(CSS);

        Stage photosStage = new Stage();
        photosStage.setTitle("Fotos de " + selectedPerson.getName() + " : " + selectedPerson.getContactNumber());
        photosStage.initModality(Modality.WINDOW_MODAL);
        photosStage.initOwner(currStage);
        photosStage.setScene(viewScene);
        photosStage.showAndWait();

    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error al cargar FXML: " + e.getMessage());
    }
}
        private void Tags(){
            try {
 			
 			
			FXMLLoader tags = new FXMLLoader();
			tags.setLocation(getClass().getResource("/fxml/Tags.fxml"));
			AnchorPane root = (AnchorPane) tags.load();
                        
			 
                        TagsController tagController = tags.getController();
	                tagController.start(currStage,selectedPerson);
                         
			Stage tagsView = new Stage();
			tagsView.setTitle("Etiquetas");
			tagsView.initModality(Modality.WINDOW_MODAL);
			tagsView.initOwner(currStage);
			Scene scene = new Scene(root);
			tagsView.setScene(scene);
	               
				
				
				
				tagsView.showAndWait();
 		} catch (IOException e) {
			e.printStackTrace();  
		}
	}
        
         private void SearchAdvence(){
            try {
 			
 			
			FXMLLoader searchSear = new FXMLLoader();
			searchSear.setLocation(getClass().getResource("/fxml/SearchAdvance.fxml"));
			AnchorPane root = (AnchorPane) searchSear.load();
                        
			 
                        SearchAdvanceController tagController =searchSear.getController();
	                tagController.initializeContacts(currUser);
                         
			Stage conSear = new Stage();
			conSear.setTitle("Busqueda Avnzada");
			conSear.initModality(Modality.WINDOW_MODAL);
			conSear.initOwner(currStage);
			Scene scene = new Scene(root);
			conSear.setScene(scene);
	               
				
				
				
				conSear.showAndWait();
 		} catch (IOException e) {
			e.printStackTrace();  
		}
	}
        
        

	
	
	

   
    
}

