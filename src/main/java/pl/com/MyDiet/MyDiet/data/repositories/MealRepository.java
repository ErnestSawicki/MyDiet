package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long>{

    boolean existsByName(String name);

    List<Meal> findAllByMealTypes(MealType mealType);

    Optional<Meal> findById(Long id);

}
