package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.MealIngredient;

public interface MealIngredientRepository extends JpaRepository<MealIngredient, Long> {
}
