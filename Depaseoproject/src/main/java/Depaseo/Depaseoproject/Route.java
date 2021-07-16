package Depaseo.Depaseoproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String name;
	public String type; // Gastronomico, Historico, Arquitectónico, familiar, Ocio Nocturno...
	public int location; 
	//public HashMap<String, Route> routes; // este hashmap nos permitirá guardar objetos con las informaciones
                                          //de geolocalización y asociarlos a un tag.
	
	
	//Defining relationship type: Many to One
	@ManyToOne 
	@JoinColumn (name = "ID_User")
	private User user;
	
	//--------Constructor-------------
	
	public Route(String name, String type, int location, User user) {
		super();
		this.name = name;
		this.type = type;
		this.location = location;
		this.user = user;
	}	
			
	public Route() {
		super();}
	
		
	//-----Getters and Setters----------
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	/*public HashMap<String, Route> getRoutes() {
		return routes;
	}
	public void setRoutes(HashMap<String, Route> routes) {
		this.routes = routes;
	}*/
	
	//------ToString------------
	
	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", type=" + type + ", location=" + location + "]";
	    }
	}

