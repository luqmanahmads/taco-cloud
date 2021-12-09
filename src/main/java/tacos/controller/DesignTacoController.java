package tacos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	
	public DesignTacoController(
			IngredientRepository ingredientRepo,
			TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	@ModelAttribute("taco")
	public Taco taco() {
		return new Taco();
	}
	
	@ModelAttribute("order")
	public Order order() {
		return new Order();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		ingredients = (List<Ingredient>) ingredientRepo.findAll();
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			filterByType(ingredients, type));
		}
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(
			@Valid Taco taco,
			Errors errors,
			@ModelAttribute Order order) {
		
		if((errors.hasErrors())) {
			return "design";
		}
		
		List<Taco> tacoList = new ArrayList<Taco>();
		Taco savedTaco = tacoRepo.save(taco);
		tacoList.add(savedTaco);
		order.setTacos(tacoList);
		
		log.info("Processing design: "+taco.getName());
		
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
