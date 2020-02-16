package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;


public interface IngredientCategoryRepository  extends JpaRepository<IngredientCategory, Long> {

}
