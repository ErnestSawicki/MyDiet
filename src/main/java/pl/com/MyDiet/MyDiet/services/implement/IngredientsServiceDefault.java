package pl.com.MyDiet.MyDiet.services.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.services.IngredientCategoryService;
import pl.com.MyDiet.MyDiet.services.IngredientService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IngredientsServiceDefault implements IngredientService {

    private final IngredientCategoryService ingredientCategoryService;
    private final IngredientRepository ingredientRepository;
    private final ModelMapper mapper;

    @Autowired
    public IngredientsServiceDefault(IngredientCategoryService ingredientCategoryService, IngredientRepository ingredientRepository, ModelMapper mapper) {
        this.ingredientCategoryService = ingredientCategoryService;
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

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
        Set<IngredientCategoryDTO> allCategories = ingredientCategoryService.getAllIngredientCategoryDTO();
        Set<Long> usedCategories = ingredientDTO.getIngredientCategoriesIdAndName().stream()
                .map(IngredientDTO.IngredientCategoriesIdAndName::getId)
                .collect(Collectors.toSet());
        allCategories.removeIf(p -> usedCategories.contains(p.getId()));
        return allCategories;
    }

    @Transactional
    @Override
    public boolean saveIngredient(IngredientDTO ingredientDTO) {
        System.out.println(ingredientDTO);
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
                .map(p -> ingredientCategoryService.findById(p.getId())
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
