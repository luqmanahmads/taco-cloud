package tacos.rest;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.model.Taco;
import tacos.repository.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

	private TacoRepository tacoRepo;
	private TacoResourceAssembler tacoResourceAssembler;
	
	public RecentTacosController(TacoRepository tacoRepo, TacoResourceAssembler tacoResourceAssembler) {
		this.tacoRepo = tacoRepo;
		this.tacoResourceAssembler = tacoResourceAssembler;
	}
	
	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	public ResponseEntity<CollectionModel<TacoResource>> recentTacos(){
		
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		
		CollectionModel<TacoResource> colDelRecentTacos = tacoResourceAssembler.toCollectionModel(tacos);
		return new ResponseEntity<>(colDelRecentTacos, HttpStatus.OK);
	}
}
