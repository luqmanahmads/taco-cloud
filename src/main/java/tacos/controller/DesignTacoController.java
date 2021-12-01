package tacos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;

@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		ingredients.add(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		ingredients.add(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
		ingredients.add(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
		ingredients.add(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		ingredients.add(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
		ingredients.add(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		ingredients.add(new Ingredient("CHED", "Cheddar", Type.CHEESE));
		ingredients.add(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		ingredients.add(new Ingredient("SLSA", "Salsa", Type.SAUCE));
		ingredients.add(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			filterByType(ingredients, type));
		}
		
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	@PostMapping
	public String processDesign() {
		// Save taco
		
		log.info("Processing design: ");
		
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		
		List<Ingredient> resultIng = new ArrayList<Ingredient>();
		for(Ingredient ing : ingredients) {
			if(ing.getType().equals(type)) {
				resultIng.add(ing);
			}
		}
		
		return resultIng;
	}
}
