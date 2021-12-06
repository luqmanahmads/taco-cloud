package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.model.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/current")
	public String orderForm(@ModelAttribute Order order) {
		return "order_form";
	}
	
	@PostMapping
	public String processOrder(Order order, Errors errors) {
		if((errors.hasErrors())) {
			return "order_form";
		}
		
		// Save order ..
		
		log.info("Order submitted: "+order);
		return "redirect:/";
	}
}
