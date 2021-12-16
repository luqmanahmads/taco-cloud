package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>, PagingAndSortingRepository<Ingredient, String>{

}
