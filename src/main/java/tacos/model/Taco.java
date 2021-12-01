package tacos.model;

import java.util.List;

public class Taco {

	private String name;
	private List<String> ingredients;
	
	public Taco() {
		super();
		this.name = "";
		this.ingredients = null;
	}
	
	public Taco(String name, List<String> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}