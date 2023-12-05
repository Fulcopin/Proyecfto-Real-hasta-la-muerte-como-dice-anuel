/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.eddproyecto.clases;

import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author JorgeHN
 */
public class Person extends Contact{
    
    String lastname;
    String workNumber;
    String workEmail;
    ArrayList<String> workAddress;
    LocalDate birthay;
    String tipo;
    
    public Person(String name, String lastname,String country, String contactNumber, String workNumber, String email, String workEmail, LinkedList<Photo> photos, ArrayList<String> address, ArrayList<String> workAddress) {
        super(name,country, contactNumber, email, photos, address);
        this.lastname =  lastname;
        this.workNumber = workNumber;
        this.workEmail = workEmail;
        this.workAddress = workAddress;
    }
    
    
    public Person(String name, String lastname,String country, String contactNumber,LinkedList<Photo> photos, String workNumber, String email, String workEmail, ArrayList<String> address, ArrayList<String> workAddress,LocalDate birthay) {
        super(name,country, contactNumber, email,photos, address);
        this.lastname =  lastname;
        this.workNumber = workNumber;
        this.workEmail = workEmail;
        this.workAddress = workAddress;
        this.birthay=birthay;
       this.tipo="Persona";
    }

     public Person(String name, String contactNumber,String country, String email, LinkedList<Photo> photos, ArrayList<String> address) {
        super(name,country, contactNumber, email, photos, address);
        this.tipo="Empresa";
    }

    public String getName() {
        return name;
    }
    public String getTipo() {
        return tipo;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public ArrayList<String> getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(ArrayList<String> workAddress) {
        this.workAddress = workAddress;
    }

    public LocalDate getBirthay() {
        return birthay;
    }

    public void setBirthay(LocalDate birthay) {
        this.birthay = birthay;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LinkedList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(LinkedList<Photo> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }
     public int countNonNullElements() {
        int count = 0;
                //name,country, contactNumber, email, photos, address
        if(tipo.equals("Empresa")){
          if (name != null) count++;
          if (country != null) count++;
          if (contactNumber != null) count++;
          if (email != null) count++;
          for(Photo fo:photos){
           count++;
          }
          for(String di:address){
           count++;
          }
        return count;
        }
        /*String lastname;
    String workNumber;
    String workEmail;
    ArrayList<String> workAddress;
    LocalDate birthay;
    String tipo;*/
        if (name != null) count++;
          if (country != null) count++;
          if (contactNumber != null) count++;
          if (email != null) count++;
          for(Photo fo:photos){
           count++;
          }
          for(String dir:address){
           count++;
          }
        if (lastname != null) count++;
        if(workEmail!= null) count++;
        for(String di:workAddress){
           count++;
          }
        if(birthay!=null) count++;
       
    
        return count;
     }

    
    public ArrayList<String> getTags() {
        return tags;
    }

    /*
    @Override
    public String toString() {
    return "Person{" + "name=" + name + "lastname=" + lastname + ", workNumber=" + workNumber + ", contactNumber=" + contactNumber + ", email=" + email + ", workEmail=" + workEmail + ", photos=" + photos.toString() + ", address=" + address.toString() + ", workAddress=" + workAddress.toString() + '}';
    }
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        if(tipo.equals("Persona")){
        return name +" "+ country+" "+" "+contactNumber+" "+email+" " + lastname+" "+" "+workAddress+" "+birthay;
        }
        return name + " " + country+" "+" "+contactNumber+" "+email;
    }
    
}
