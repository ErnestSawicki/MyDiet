package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{


    boolean existsByName(String name);
}
