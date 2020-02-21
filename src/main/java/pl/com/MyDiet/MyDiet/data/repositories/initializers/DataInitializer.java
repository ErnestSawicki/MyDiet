package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.*;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientRepository ingredientRepository;
    private final MealRepository mealRepository;
    private final PartOfMealRepository partOfMealRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(IngredientCategoryRepository ingredientCategoryRepository,
                           IngredientRepository ingredientRepository,
                           MealRepository mealRepository,
                           PartOfMealRepository partOfMealRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.partOfMealRepository = partOfMealRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("DataInitializer: Sample data insert started....");
            UserInitializer userInitializer = new UserInitializer();
            userInitializer.createSampleUsers(userRepository, passwordEncoder);
        log.info("DataInitializer: ... sample data finished");
    }
}