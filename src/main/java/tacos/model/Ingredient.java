package tacos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {
	
	@Id
	private String id;
	private String name;
	private Type type;
	
	public Ingredient() {
		super();
		this.id = null;
		this.name = null;
		this.type = null;
	}

	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}


	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
		
}
