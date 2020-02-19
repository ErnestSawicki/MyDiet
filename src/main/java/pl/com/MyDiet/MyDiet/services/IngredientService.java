package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;


import java.util.Set;

public interface IngredientService {
    IngredientDTO rebuildFormWhenAddCategory(IngredientDTO ingredientDTO);
    IngredientDTO rebuildFormWhenDeletedCategory(IngredientDTO ingredientDTO);
    Set<IngredientCategoryDTO> getIngredientCategories(IngredientDTO ingredientDTO);
    boolean saveIngredient(IngredientDTO ingredientDTO);
//    boolean deleteIngredient(IngredientDTO ingredientDTO);
//    boolean modifyIngredient(IngredientDTO ingredientDTO);
}
