package pl.com.MyDiet.MyDiet.services;


import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;


import java.util.Optional;
import java.util.Set;

public interface IngredientCategoryService {
    Set<IngredientCategoryDTO> getAllIngredientCategoryDTO();
    Optional<IngredientCategory> findById (Long id);
    boolean save (IngredientDTO ingredientDTO, String nameCategory);
    boolean delete (Long id, Long moderatorId);
    boolean modify (Long id, Long moderatorId);
}
