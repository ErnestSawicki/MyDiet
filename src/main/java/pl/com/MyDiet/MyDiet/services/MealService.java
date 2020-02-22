package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleIngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.MealType;

import java.util.List;

public interface MealService {
    MealCreateDTO rebuildFormWhenAddIngredient(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenAddMealType(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenDeletedIngredient(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenDeletedMealType(MealCreateDTO mealCreateDTO);

    List<SimpleIngredientDTO> getIngredientsDTO(MealCreateDTO mealCreateDTO);

    List<MealType> getAllMealType();

    List<MealType> getMealType(MealCreateDTO mealCreateDTO);

    List<SimpleIngredientDTO> getAllIngredientsDTO();

    boolean saveIngredient(MealCreateDTO mealCreateDTO, String principal);
}
