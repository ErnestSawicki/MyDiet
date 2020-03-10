package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.services.IngredientCategoryService;
import pl.com.MyDiet.MyDiet.services.IngredientService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IngredientsServiceDefault implements IngredientService {

    private final IngredientCategoryService ingredientCategoryService;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientsServiceDefault(IngredientCategoryService ingredientCategoryService, IngredientRepository ingredientRepository) {
        this.ingredientCategoryService = ingredientCategoryService;
        this.ingredientRepository = ingredientRepository;

    }
    @Override
    public IngredientDTO rebuildFormWhenCreateNewCategory(IngredientDTO ingredientDTO, IngredientCategoryDTO ingredientCategoryDTO) {
        IngredientCategoryDTO categoryDTO = ingredientCategoryService.findIngredientCategoryDTOByName(ingredientCategoryDTO.getName());
        ingredientDTO.setCategoryToAdd(new IngredientDTO.IngredientCategoriesIdAndName(categoryDTO.getId(), categoryDTO.getName()));
        ingredientDTO.saveCategory();
        return ingredientDTO;
    }

    @Override
    public boolean ingredientNameIsAvailable(String name) {
        return !ingredientRepository.existsByName(name);
    }

    @Override
    public IngredientDTO rebuildFormWhenAddCategory(IngredientDTO ingredientDTO) {
        ingredientDTO.saveCategory();
        return ingredientDTO;
    }

    @Override
    public IngredientDTO rebuildFormWhenDeletedCategory(IngredientDTO ingredientDTO) {
        ingredientDTO.removeCategory();
        return ingredientDTO;
    }

    @Override
    public Set<IngredientCategoryDTO> getIngredientCategories(IngredientDTO ingredientDTO) {
        return ingredientCategoryService.getIngredientCategories(ingredientDTO);
    }


    @Override
    public List<IngredientDTO> getAllIngredientsDTO() {
        return ingredientRepository.findAll().stream()
                .map(p -> {
                    IngredientDTO ingredientDTO = new IngredientDTO();
                    ingredientDTO.setIngredientId(p.getId());
                    ingredientDTO.setIngredientName(p.getName());
                    ingredientDTO.setCaloriesPer100g(p.getCaloriesPer100gram());
                    ingredientDTO.setIngredientCategoriesIdAndName(p.getIngredientCategories().stream()
                            .map(s -> IngredientDTO.IngredientCategoriesIdAndName.valueOf(s.getId() + ";" + s.getName())
                            ).collect(Collectors.toList()));
                    return ingredientDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<IngredientDTO> getIngredientsDTO(MealCreateDTO mealCreateDTO) {
        List<IngredientDTO> ingredientDTOS = getAllIngredientsDTO();
        List<Long> usedIngredients = mealCreateDTO.getPartsOfMealIngredientIdNameAmount().stream().map(MealCreateDTO.PartOfMeal::getIngredientId).collect(Collectors.toList());
        ingredientDTOS.removeIf(p -> usedIngredients.contains(p.getIngredientId()));
        return ingredientDTOS;
    }

    @Transactional
    @Override
    public boolean saveIngredient(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null
                || ingredientDTO.getIngredientName() == null
                || ingredientDTO.getCaloriesPer100g() == null
                || ingredientDTO.getIngredientCategoriesIdAndName().isEmpty()
                || ingredientRepository.existsByName(ingredientDTO.getIngredientName())) {
            return false;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getIngredientName());
        ingredient.setCaloriesPer100gram(ingredientDTO.getCaloriesPer100g());
        List<IngredientCategory> ingredientCategories = ingredientDTO.getIngredientCategoriesIdAndName().stream()
                .map(p -> ingredientCategoryService.findIngredientCategoryById(p.getId())
                        .orElse(null))
                .collect(Collectors.toList());
        ingredient.setIngredientCategories(ingredientCategories);
        log.info("Try to save ingredient= {}", ingredient.getName());
        ingredientRepository.save(ingredient);
        log.info("Saved {}", ingredient.getName());
        return true;
    }

//    @Override
    // public boolean deleteIngredient(IngredientDTO ingredientDTO) {
//        return false; }

//    @Override
//    public boolean modifyIngredient(IngredientDTO id) {
//            if (ingredientDTO == null
//                    || ingredientDTO.getIngredientName() == null
//                    || ingredientDTO.getCaloriesPer100g() == null
//                    || ingredientDTO.getIngredientCategoriesIdAndName().isEmpty()
//                    || ingredientRepository.existsByName(ingredientDTO.getIngredientName())) {
//                return false;
//            }
//ingredientRepository.getOne(ingredientDTO)
//            log.info("Try to save ingredient= {}", ingredient.getName());
//            ingredientRepository.save(ingredient);
//            log.info("Saved {}", ingredient.getName());
//            return true;
//    }
}
