package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.mvc.controllers.MealController;

import java.util.Set;

public interface MealService {
     rebuildFormWhenAddIngredient(IngredientDTO ingredientDTO);
    IngredientDTO rebuildFormWhenDeletedIngredient(IngredientDTO ingredientDTO);
    Set<IngredientCategoryDTO> getIngredientCategories(IngredientDTO ingredientDTO);
    boolean saveIngredient(IngredientDTO ingredientDTO);
}
