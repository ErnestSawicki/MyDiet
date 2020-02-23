package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.data.repositories.*;

@Component
@Slf4j
@Transactional
@Profile("heroku")
public class DataInitializer implements CommandLineRunner {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientRepository ingredientRepository;
    private final MealRepository mealRepository;
    private final PartOfMealRepository partOfMealRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MealTypeRepository mealTypeRepository;
    private final DailySetRepository dailySetRepository;

    @Autowired
    public DataInitializer(IngredientCategoryRepository ingredientCategoryRepository,
                           IngredientRepository ingredientRepository,
                           MealRepository mealRepository,
                           PartOfMealRepository partOfMealRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           MealTypeRepository mealTypeRepository, DailySetRepository dailySetRepository) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.partOfMealRepository = partOfMealRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mealTypeRepository = mealTypeRepository;
        this.dailySetRepository = dailySetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("DataInitializer: Sample data insert started....");
        UserInitializer userInitializer = new UserInitializer();
        userInitializer.createSampleUsers(userRepository, passwordEncoder);

        IngredientCategoryInitializer ingredientCategoryInitializer = new IngredientCategoryInitializer();
        ingredientCategoryInitializer.createSampleIngredientCategory(ingredientCategoryRepository);

        IngredientInitializer ingredientInitializer = new IngredientInitializer();
        ingredientInitializer.createSampleIngredients(ingredientRepository, ingredientCategoryRepository);

        MealTypeInitializer mealTypeInitializer = new MealTypeInitializer();
        mealTypeInitializer.createSampleUsers(mealTypeRepository);

        MealInitializer mealInitializer = new MealInitializer();
        mealInitializer.createSampleMeal(mealRepository, userRepository, mealTypeRepository, partOfMealRepository, ingredientRepository);

        DailySetInitializer dailySetInitializer = new DailySetInitializer();
        dailySetInitializer.createDailySetSamples(dailySetRepository, mealRepository, userRepository);
        log.info("DataInitializer: ... sample data finished");


    }
}
