package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleIngredientDTO;

import java.util.List;

public interface MealService {
    MealCreateDTO rebuildFormWhenAddIngredient(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenAddMealType(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenDeletedIngredient(MealCreateDTO mealCreateDTO);

    MealCreateDTO rebuildFormWhenDeletedMealType(MealCreateDTO mealCreateDTO);

    List<SimpleIngredientDTO> getIngredients(MealCreateDTO mealCreateDTO);
    List<SimpleIngredientDTO> getAllIngredients();

    boolean saveIngredient(MealCreateDTO mealCreateDTO, String principal);
}
