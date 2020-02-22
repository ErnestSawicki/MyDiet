package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import com.github.javafaker.Faker;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;
import pl.com.MyDiet.MyDiet.data.repositories.*;

import java.util.Random;

public class MealInitializer {

    public void createSampleMeal(MealRepository mealRepository,
                                 UserRepository userRepository,
                                 MealTypeRepository mealTypeRepository,
                                 PartOfMealRepository partOfMealRepository,
                                 IngredientRepository ingredientRepository) {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            Meal meal = new Meal();
            meal.setRecipe(faker.harryPotter().quote());
            meal.setName(faker.food().dish());
            meal.setPreparationTime(1L + (long) (Math.random() * (120L - 1L)));
            meal.setCalories(1L + (long) (Math.random() * (350L - 1L)));
            meal.setCreatorUser(userRepository.findAll().get(new Random().nextInt(9)));

            for (int j = 0; j < new Random().nextInt(3); j++) {
                MealType mealType = mealTypeRepository.findAll().get(new Random().nextInt(3));
                meal.getMealTypes().add(mealType);
                mealType.getMeals().add(meal);
            }

            for (int j = 0; j < new Random().nextInt(7); j++) {
                PartOfMeal partOfMeal = new PartOfMeal();
                partOfMeal.setAmount(1L + (long) (Math.random() * (300L - 1L)));
                partOfMeal.setIngredient(ingredientRepository.findAll().get(new Random().nextInt(15)));
                meal.getPartsOfMeal().add(partOfMeal);
                partOfMealRepository.save(partOfMeal);
            }

            mealRepository.save(meal);
        }
    }
}
