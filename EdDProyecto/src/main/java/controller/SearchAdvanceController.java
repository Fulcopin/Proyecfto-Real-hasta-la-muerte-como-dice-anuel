/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ec.edu.espol.eddproyecto.clases.*;

import java.net.URL;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author fulco
 */
public class SearchAdvanceController  {
    @FXML
    private Button search;
    @FXML
    private TextField countryField;

    @FXML
    private TextField nombreField;

    @FXML
    private ListView<Person> resultListView;

    @FXML
    private TextField tipoField;
    
     @FXML
    private DatePicker birthDatePicker;

  
    @FXML
    private TextField countryField1;

    @FXML
    private TextField countryField2;

    @FXML
    private TextField countryField21;


    @FXML
    private TextField nombreField1;
     

    @FXML
    private TextField tipoField1;

    @FXML
    private TextField tipoField2;

    @FXML
    private TextField tipoField21;
   
    User actual;
    public void initializeContacts(User p) {
        
        actual=p;
       
      
        Queue<Person> contactQueue = new PriorityQueue<>((a1, a2) -> a1.getName().compareTo(a2.getName())
        );
         contactQueue.addAll(actual.getAllContact());
        resultListView.getItems().setAll(contactQueue);
        // Agregar elementos de LinkedList a PriorityQueue
        search.setOnAction(event -> {
            System.out.println("Botón de búsqueda presionado");
            handleFilterChange(contactQueue);
            
        });
    }
       
     
       
   
    
    

    @FXML
    private void handleFilterChange(Queue<Person> contactos) {
        String filtrocoun = countryField.getText().trim().toLowerCase();
        String filtrono = nombreField.getText().trim().toLowerCase();
        String filtupo = tipoField.getText().trim();
        String filtadres=countryField1.getText().trim().toLowerCase();
        String filtnumeT=countryField2.getText().trim().toLowerCase();
        String filtnumCon=nombreField1.getText().trim().toLowerCase();
        String filtdireTr=countryField21.getText().trim().toLowerCase();
        String filtemail = tipoField1.getText().trim();
        String filtcorrTt = tipoField21.getText().trim();
        String filtapel = tipoField2.getText().trim();
        Comparator<Person> comparator = Comparator.comparing(Person::getName)
            .thenComparing(Person::getTipo)
            .thenComparing(Person::getCountry).thenComparing(Person::getEmail).thenComparing(Person::getLastname).thenComparing(Person::getWorkEmail).thenComparing(Person::getContactNumber)
         ;

    // Filtrar la cola original y mostrar el resultado en la TableView
    /*ArrayList<String> conti=new ArrayList<>();
    if(filtro.contains("-")){
        String[] po=filtro.split("-");
        for(String s: po){
            conti.add(s);
        }
        
    }
    conti.add(filtro);*/
    Queue<Person> contactosFiltrados = filterContacts(contactos, filtrono,filtupo,filtrocoun,filtemail,filtadres,filtdireTr,filtnumCon,filtnumeT,filtcorrTt,filtapel, comparator);
    resultListView.getItems().setAll(contactosFiltrados);
}

   
private Queue<Person> filterContacts(Queue<Person> contactos, String nombreFiltro, String tipoFiltro, String countryFiltro, String emailFiltro, String addresFiltro, String wordadreFiltro, String numberFiltro, String worknumberFiltro, String workemaFiltro, String apellidoFiltro, Comparator<Person> comparator) {
    return contactos.stream()
            .filter(person ->
                    (nombreFiltro == null || person.getName().toLowerCase().contains(nombreFiltro.toLowerCase())) &&
                    (tipoFiltro == null || person.getTipo().toLowerCase().contains(tipoFiltro.toLowerCase())) &&
                    (countryFiltro == null || person.getCountry().toLowerCase().contains(countryFiltro.toLowerCase())) &&
                    (emailFiltro == null || person.getEmail().toLowerCase().contains(emailFiltro.toLowerCase())) &&
                    (addresFiltro == null || person.getAddress().contains(addresFiltro.toLowerCase())) &&
                    (wordadreFiltro == null || person.getWorkAddress().contains(wordadreFiltro.toLowerCase())) &&
                    (numberFiltro == null || person.getContactNumber().toLowerCase().contains(numberFiltro.toLowerCase())) &&
                    (worknumberFiltro == null || person.getWorkNumber().toLowerCase().contains(worknumberFiltro.toLowerCase())) &&
                    (workemaFiltro == null || person.getWorkEmail().toLowerCase().contains(workemaFiltro.toLowerCase())) &&
                    (apellidoFiltro == null || person.getLastname().toLowerCase().contains(apellidoFiltro.toLowerCase()))
                    
            )
            .collect(Collectors.toCollection(() -> new PriorityQueue<>(comparator)));
}
}
