/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ec.edu.espol.eddproyecto.clases.ArrayList;
import ec.edu.espol.eddproyecto.clases.LinkedList;
import java.util.*;
import ec.edu.espol.eddproyecto.clases.Person;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;



public class ContactListController  {

//@FXML
//private FlowPane contactFlowPane;
@FXML
private AnchorPane anchorPane;

@FXML
private TableView<Person> tableView;

@FXML
private TableColumn<Person, String> nombreColumn;

@FXML
private TableColumn<Person, String> numeroColumn;

@FXML
private TableColumn<Person, String> emailColumn;
 @FXML
  private ComboBox<String> orderByComboBox;
@FXML
  private TextField searchTextField;

 private LinkedList<Person> originalContacts;
    private LinkedList<Person> currentContacts;
    private int currentIndex;
    public void initializeContacts(LinkedList<Person> contactos,Scene sc) {
        
        
         originalContacts = new LinkedList<>();
        //anchorPane.setOnKeyPressed(this);
        currentContacts = new LinkedList<>();
        for(Person p:contactos){
            originalContacts.add(p);
            currentContacts.add(p);
        }
        currentIndex = 0;
        //anchorPane.setOnKeyPressed(this);
        Queue<Person> contactQueue = new PriorityQueue<>((a1, a2) -> a1.getName().compareTo(a2.getName())
        );
        
        // Agregar elementos de LinkedList a PriorityQueue
        contactQueue.addAll(contactos);
      ObservableList<String> orderOptions = FXCollections.observableArrayList(
                "Cumpleaños", "Apellido y Nombre Ascendente", "Tipo de Contacto Ascendente", "Tipo de Contacto Descendente","País Ascendente","País Descendente","Cantidad de Atributos Ascendente","Empresa Ascendente"
        );
        orderByComboBox.setItems(orderOptions);
    // Configuración de la TableView y las columnas
    tableView.getItems().setAll(contactos);
    nombreColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    numeroColumn.setCellValueFactory(new PropertyValueFactory<>("workNumber")); 
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("birthay"));
   orderByComboBox.setOnAction(event -> handleOrderBySelection(contactQueue));
   searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        // Cuando el texto cambia, ejecuta la función de búsqueda
        handleFilterChange(contactQueue);
    });
        /*for (Person contacto : contactos) {
            createContactTile(contacto);
        }
    }

    private void createContactTile(Person contacto) {
        AnchorPane tileContacto = new AnchorPane();
        tileContacto.setMinWidth(200);
        tileContacto.setMaxWidth(200);
        tileContacto.setMinHeight(120);
        tileContacto.setMaxHeight(120);

        Label nombreLabel = createLabel(contacto.getName() + " " + contacto.getLastname(), 10, 10, 180, 20);
        Label numeroLabel = createLabel("Número de contacto: " + contacto.getLastname(), 10, 40, 180, 20);
        Label emailLabel = createLabel("Correo electrónico: " + contacto.getEmail(), 10, 70, 180, 20);

        tileContacto.getChildren().addAll(nombreLabel, numeroLabel, emailLabel);
        tileContacto.setUserData(contacto);

        tileContacto.setOnMouseClicked(event -> {
            // Manejar el evento de clic, puedes navegar a los detalles del contacto u realizar otras acciones
            System.out.println("Clic en: " + contacto.getName() + " " + contacto.getLastname());
        });

        //contactFlowPane.getChildren().add(tileContacto);
    }

    private Label createLabel(String texto, double layoutX, double layoutY, double ancho, double alto) {
        Label etiqueta = new Label(texto);
        etiqueta.setLayoutX(layoutX);
        etiqueta.setLayoutY(layoutY);
        etiqueta.setPrefWidth(ancho);
        etiqueta.setPrefHeight(alto);
        return etiqueta;
    }*/
         
    if (sc != null) {
        sc.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    navigateBackward();
                    break;
                case DOWN:
                    navigateForward();
                    break;
                // Puedes agregar más casos según sea necesario
            }
        });
    } else {
        // Aquí puedes imprimir un mensaje o tomar alguna acción si la Scene es nula
        System.err.println("La Scene es nula");
    }
    tableView.setFocusTraversable(true);
    tableView.requestFocus();
}
    
    private void navigateForward() {
        currentIndex = (currentIndex + 1) % currentContacts.size();
        updateTableView();
    }

    private void navigateBackward() {
        currentIndex = (currentIndex - 1 + currentContacts.size()) % currentContacts.size();
        updateTableView();
    }

    // Método para actualizar la TableView con el contacto actual
    private void updateTableView() {
        // Obtén el contacto actual
        Person currentContact = currentContacts.get(currentIndex);

        // Actualiza la TableView con el contacto actual
        tableView.getItems().setAll(currentContact);
    }
   private void handleOrderBySelection(Queue<Person> contactos) {
    String selectedOption = orderByComboBox.getValue();

    // Crear una nueva cola de prioridad con el comparador seleccionado
    PriorityQueue<Person> sortedContacts = new PriorityQueue<>(getComparator(selectedOption));
    
    // Transferir los elementos de la cola de contactos original a la nueva cola ordenada
    sortedContacts.addAll(contactos);

    // Configurar la TableView con los contactos ordenados
    tableView.getItems().setAll(sortedContacts);
}

private Comparator<Person> getComparator(String selectedOption) {
    switch (selectedOption) {
        
        case "Cumpleaños":
             
          return Comparator.comparing(Person::getBirthay, Comparator.nullsLast(Comparator.naturalOrder()));
        case "Apellido y Nombre Ascendente":
           return this::compares;
        case "Tipo de Contacto Ascendente":
           return Comparator.comparing(Person::getTipo);
        case "Tipo de Contacto Descendente":
           return Comparator.comparing(Person::getTipo).reversed();
        case "País Ascendente":
           return Comparator.comparing(Person::getCountry);
        case "País Descendente":
           return Comparator.comparing(Person::getCountry).reversed();
        case "Cantidad de Atributos Ascendente":
           return Comparator.comparingInt(person -> person.countNonNullElements());
        case "Empresa Ascendente":
            return this::compaD;
 
        default:
            
            return Comparator.comparing(Person::getName);
    }
    
}
public int compares(Person persona1, Person persona2) {
        if ("Persona".equals(persona1.getTipo()) && "Persona".equals(persona2.getTipo())) {
            
            return Comparator.comparing(Person::getLastname).thenComparing(Person::getName).compare(persona1, persona2);
        } else {
            
            return 0;
        }
    }
public int compaD(Person persona1, Person persona2) {
    if ("Empresa".equals(persona1.getTipo()) && "Empresa".equals(persona2.getTipo())) {
        
        return Comparator.comparing(Person::getName).compare(persona1, persona2);
    } else {
        
        return 0;
    }
}
 @FXML
    private void handleFilterChange(Queue<Person> contactos) {
        String filtro = searchTextField.getText().trim().toLowerCase();

        Comparator<Person> comparator = Comparator.comparing(Person::getName)
            .thenComparing(Person::getTipo)
            .thenComparing(Person::getCountry);

    // Filtrar la cola original y mostrar el resultado en la TableView
    ArrayList<String> conti=new ArrayList<>();
    if(filtro.contains("-")){
        String[] po=filtro.split("-");
        for(String s: po){
            conti.add(s);
        }
        
    }
    conti.add(filtro);
    Queue<Person> contactosFiltrados = filterContacts(contactos, filtro, comparator,conti);
    tableView.getItems().setAll(contactosFiltrados);
}

   private Queue<Person> filterContacts(Queue<Person> contactos, String filtro, Comparator<Person> comparator,ArrayList<String> conte) {
       
          
       
    return contactos.stream()
            .filter(person -> person.getName().toLowerCase().contains(filtro) ||
                               person.getTipo().toLowerCase().contains(filtro) ||
                               person.getCountry().toLowerCase().contains(filtro) ||
                               person.getTags().stream().anyMatch(etiqueta -> conte.contains(etiqueta)))
            .collect(Collectors.toCollection(() -> new PriorityQueue<>(comparator)));
}

   /* @Override
    public void handle(KeyEvent t) {
        if (t.getCode() == KeyCode.UP) {
        
        navigateForward();
    } else if (t.getCode() == KeyCode.DOWN) {
        // Lógica para navegar hacia abajo en la lista
        navigateBackward();
    }
    }*/
}

