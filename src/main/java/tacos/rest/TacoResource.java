package tacos.rest;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import tacos.model.Ingredient;
import tacos.model.Taco;

public class TacoResource extends RepresentationModel<TacoResource> {
	private Date createdAt;
	private String name;
	
	private List<Ingredient> ingredients;
	
	public TacoResource (Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = taco.getIngredients();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getName() {
		return name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	
}
