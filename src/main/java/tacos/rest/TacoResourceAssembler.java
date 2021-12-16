package tacos.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import tacos.controller.DesignTacoController;
import tacos.model.Taco;

@Component
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	private RepositoryEntityLinks entityLinks;
	
	@Autowired
	public TacoResourceAssembler(RepositoryEntityLinks entityLinks) {
		super(DesignTacoController.class, TacoResource.class);
		this.entityLinks = entityLinks;
	}
	
	@Override
	public TacoResource instantiateModel(Taco taco) {
		return new TacoResource(taco);
	}
	
	@Override
	public TacoResource toModel(Taco entity) {
		TacoResource tc = this.createModelWithId(entity.getId(), entity);
		tc.removeLinks();
		tc.add(entityLinks.linkToItemResource(Taco.class, entity.getId()));
		return tc;
	}

}
