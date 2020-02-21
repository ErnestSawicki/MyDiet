package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;

public interface PartOfMealRepository extends JpaRepository<PartOfMeal, Long>{
}
