package tacos.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tacos.model.Ingredient;

@Controller
public class HomeController {
	
	private RestTemplate restTemplate;
	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home() {
		
		restTemplate = new RestTemplate();
		String ingredientId = "COTO";
		
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder
					.fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
					.build(urlVariables);
		
		log.debug(url.getPath());
		
		
		Ingredient ing = restTemplate.getForObject(
				url, 
				Ingredient.class);
		
		log.debug(ing.getName());
		
		
		return "home";
	}

}
