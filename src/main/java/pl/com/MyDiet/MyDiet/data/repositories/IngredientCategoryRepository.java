package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;

import java.util.List;
import java.util.Set;


public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    List<IngredientCategory> findAll();

    Set<IngredientCategory> getById(Long id);

    boolean existsByName(String name);

    IngredientCategory findByName(String name);
}
