package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;

@Slf4j
public class IngredientInitializer {

    public void createSampleIngredients(IngredientRepository ingredientRepository, IngredientCategoryRepository ingredientCategoryRepository) {

        IngredientCategory dairy = ingredientCategoryRepository.findByName("diary");
        createIngredient("milk", 85L, dairy, ingredientRepository);
        createIngredient("yogurt", 120L, dairy, ingredientRepository);
        createIngredient("cottage cheese", 110L, dairy, ingredientRepository);

        IngredientCategory meat = ingredientCategoryRepository.findByName("meat");
        createIngredient("chicken", 200L, meat, ingredientRepository);
        createIngredient("turkey", 190L, meat, ingredientRepository);
        createIngredient("beef", 260L, meat, ingredientRepository);
        createIngredient("lamb", 185L, meat, ingredientRepository);
        createIngredient("bacon", 264L, meat, ingredientRepository);

        IngredientCategory vegetables = ingredientCategoryRepository.findByName("vegetables");
        createIngredient("tomato", 50L, vegetables, ingredientRepository);
        createIngredient("lettuce", 30L, vegetables, ingredientRepository);
        createIngredient("cucumber", 45L, vegetables, ingredientRepository);

        IngredientCategory fruits = ingredientCategoryRepository.findByName("fruits");
        createIngredient("apple", 70L, fruits, ingredientRepository);
        createIngredient("pineapple", 60L, fruits, ingredientRepository);
        createIngredient("pear", 70L, fruits, ingredientRepository);
        createIngredient("orange", 100L, fruits, ingredientRepository);


    }


    private void createIngredient(String name, Long calories, IngredientCategory ingredientCategory, IngredientRepository repository) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setCaloriesPer100gram(calories);
        ingredient.getIngredientCategories().add(ingredientCategory);
        repository.save(ingredient);
    }
}
