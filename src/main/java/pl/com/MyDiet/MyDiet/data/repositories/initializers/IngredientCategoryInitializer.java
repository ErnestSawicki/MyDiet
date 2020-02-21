package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;

@Slf4j
public class IngredientCategoryInitializer {

    public void createSampleIngredientCategory(IngredientCategoryRepository ingredientCategoryRepository){
        log.info("IngredientCategoryInitializer: IngredientCategory sample data started ...");
        createIngredientCategory("nabiał", ingredientCategoryRepository);
        createIngredientCategory("mięso", ingredientCategoryRepository);
        createIngredientCategory("warzywa", ingredientCategoryRepository);
        createIngredientCategory("napoje", ingredientCategoryRepository);
        createIngredientCategory("owoce", ingredientCategoryRepository);
        createIngredientCategory("strączkowe", ingredientCategoryRepository);
        createIngredientCategory("przyprawy", ingredientCategoryRepository);
        createIngredientCategory("sosy", ingredientCategoryRepository);
        log.info("IngredientCategoryInitializer: ... IngredientCategory data finished");

    }

    private void createIngredientCategory(String name, IngredientCategoryRepository repository){
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        repository.save(ingredientCategory);
    }

}
