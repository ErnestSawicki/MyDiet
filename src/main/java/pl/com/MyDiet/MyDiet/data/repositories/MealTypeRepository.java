package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

public interface MealTypeRepository extends JpaRepository<MealType, Long> {

    MealType findByMealTypeName(MealTypeEnumeration mealTypeName);
}
