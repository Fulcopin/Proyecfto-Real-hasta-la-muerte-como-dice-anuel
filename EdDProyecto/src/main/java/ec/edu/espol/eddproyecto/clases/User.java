package ec.edu.espol.eddproyecto.clases;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable, Comparable<User> {

	private int id;
	private String username;
	private String password;
        private LinkedList<Person> arrContact;  
	
	public boolean logout = false;

	
	public User(int id, String username, String password) {

		this.id = id;
		this.username = username;
		this.password = password;
                this.arrContact=new LinkedList<Person>();
		
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
        public LinkedList<Person> getAllContact() {
		return arrContact;
	}

	
	

	public void addNewContact(Person c) {
		arrContact.add(c);
	}

	
         public Person getContact(int index) {
		return arrContact.get(index);
	}

	
	public String toString() {
		return username + ";" + password + ";id:" + id;
	}

        
        @Override
        public int compareTo(User otherUser) {
            return Integer.compare(this.id, otherUser.id);
        }
	
}