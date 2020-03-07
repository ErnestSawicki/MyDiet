package pl.com.MyDiet.MyDiet.services;

import org.springframework.web.multipart.MultipartFile;
import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleIngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;

import java.io.IOException;
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

    Meal getMealById(Long mealId);

    boolean saveMeal(MealCreateDTO mealCreateDTO, String principal);

    List<Meal> getAllMeals();
}
