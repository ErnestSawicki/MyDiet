package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    IngredientDTO rebuildFormWhenAddCategory(IngredientDTO ingredientDTO);

    IngredientDTO rebuildFormWhenDeletedCategory(IngredientDTO ingredientDTO);

    Set<IngredientCategoryDTO> getIngredientCategories(IngredientDTO ingredientDTO);


    List<IngredientDTO> getIngredientsDTO(MealCreateDTO mealCreateDTO);

    boolean saveIngredient(IngredientDTO ingredientDTO);

    //    boolean deleteIngredient(IngredientDTO ingredientDTO);
//    boolean modifyIngredient(IngredientDTO ingredientDTO);
    List<IngredientDTO> getAllIngredientsDTO();
}
