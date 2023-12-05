package ec.edu.espol.eddproyecto.clases;

import java.io.Serializable;

import ec.edu.espol.eddproyecto.clases.*;

public class Contact implements Serializable{
    
    String name;
    String contactNumber;
    String email;
    String country;
    LinkedList<Photo> photos;
    ArrayList<String> address;
    ArrayList<String> tags;


    public Contact(String name,String country, String contactNumber, String email, LinkedList<Photo> photos, ArrayList<String> address) {
        this.name = name;
        this.contactNumber =  contactNumber;
        this.email =  email;
        this.photos = new LinkedList<Photo>();
        this.address = address;
        this.country=country;
        this.tags=new ArrayList<String>();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 

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
    public Photo getPhoto(int indexOf) {
		return photos.get(indexOf);
	}

    //    @Override
//    public String toString() {
//        return "{" + name + ", " + lastname + ", " + id + '}';
//    }   

}
