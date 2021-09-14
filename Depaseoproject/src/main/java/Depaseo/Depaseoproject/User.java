package Depaseo.Depaseoproject;

import java.util.ArrayList; // import object.Ruta;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity; // intitat aplication properties - conección con BD H2
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import java.util.HashMap; ¿?


@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String name;
	public String surname;
	public String userName;
	public String email;
	public String password;

	
//Defining relationship type: One to Many
	
	@OneToMany (mappedBy= "user", cascade = CascadeType.ALL)
	public List<Route> routes = new ArrayList<>();
	
	
	
//--------Constructor-------------
	
	public User(String name, String surname, String userName, String email, String password,
			ArrayList<Route> routes) {
		super();
		//this.id = id; innecesario porque el @id de JPA ya me lo crea
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}


	public User() {
		super();}



	public User(int id, String name, String userName, String email, String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}


// -----Getters and Setters----------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(ArrayList<Route> routes) {
		this.routes = routes;
	}
	
// ------ToString------------

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", userName=" + userName + ", email="
				+ email + ", password=" + password + ", routes=" + routes + "]";
		
	}
}

	
    
	
	
	
	
	

