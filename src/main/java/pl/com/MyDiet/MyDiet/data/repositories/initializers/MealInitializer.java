package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import com.github.javafaker.Faker;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;
import pl.com.MyDiet.MyDiet.data.repositories.*;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MealInitializer {

    public void createSampleMeal(MealRepository mealRepository,
                                 UserRepository userRepository,
                                 MealTypeRepository mealTypeRepository,
                                 PartOfMealRepository partOfMealRepository,
                                 IngredientRepository ingredientRepository,
                                 FileEntityRepository fileEntityRepository) throws IOException {
        Faker faker = new Faker();

        Meal soup = new Meal();
        soup.setRecipe("Heat a large saucepan and dry-fry 2 tsp cumin seeds and a pinch of chilli flakes for 1 min, or until they start to jump around the pan and release their aromas.\n" +
                "\n" +
                "Scoop out about half with a spoon and set aside. Add 2 tbsp olive oil, 600g coarsely grated carrots, 140g split red lentils, 1l hot vegetable stock and 125ml milk to the pan and bring to the boil. \n" +
                "\n" +
                "Simmer for 15 mins until the lentils have swollen and softened.\n" +
                "\n" +
                "Whizz the soup with a stick blender or in a food processor until smooth (or leave it chunky if you prefer).\n" +
                "\n" +
                "Season to taste and finish with a dollop of plain yogurt and a sprinkling of the reserved toasted spices. Serve with warmed naan breads. - takien from https://www.bbcgoodfood.com/recipes/spiced-carrot-lentil-soup");
        soup.setName("Spiced carrot & lentil soup");
        soup.setPreparationTime(25L);
        soup.setCalories(238L);
        soup.setCreatorUser(userRepository.findAll().get(0));
        MealType soupType = mealTypeRepository.findAll().get(new Random().nextInt(5));
        soup.getMealTypes().add(soupType);
        soupType.getMeals().add(soup);
        for (int j = 0; j < new Random().nextInt(6); j++) {
            PartOfMeal partOfMeal = new PartOfMeal();
            partOfMeal.setAmount(1L + (long) (Math.random() * (300L - 1L)));
            partOfMeal.setIngredient(ingredientRepository.findAll().get(new Random().nextInt(15)));
            soup.getPartsOfMeal().add(partOfMeal);
            partOfMeal.setMeal(soup);
            partOfMealRepository.save(partOfMeal);
        }
        MultipartFile file = mealImage("src/main/webapp/images/testImages/meals/soup.PNG");
        FileEntity mealFileEntity = new FileEntity();
        mealFileEntity.setContentType(file.getContentType());
        mealFileEntity.setFileName(file.getOriginalFilename());
        mealFileEntity.setData(file.getBytes());
        fileEntityRepository.save(mealFileEntity);
        soup.setMealFile(mealFileEntity);
        mealRepository.save(soup);


        for (int i = 0; i < 50; i++) {
            Meal meal = new Meal();
            meal.setRecipe(faker.harryPotter().quote());
            meal.setName(faker.food().dish());
            meal.setPreparationTime(1L + (long) (Math.random() * (120L - 1L)));
            meal.setCalories(1L + (long) (Math.random() * (350L - 1L)));
            meal.setCreatorUser(userRepository.findAll().get(new Random().nextInt(9)));

            for (int j = 0; j < new Random().nextInt(3); j++) {
                MealType mealType = mealTypeRepository.findAll().get(new Random().nextInt(5));
                meal.getMealTypes().add(mealType);
                mealType.getMeals().add(meal);
            }

            for (int j = 0; j < new Random().nextInt(7); j++) {
                PartOfMeal partOfMeal = new PartOfMeal();
                partOfMeal.setAmount(1L + (long) (Math.random() * (300L - 1L)));
                partOfMeal.setIngredient(ingredientRepository.findAll().get(new Random().nextInt(15)));
                meal.getPartsOfMeal().add(partOfMeal);
                partOfMeal.setMeal(meal);
                partOfMealRepository.save(partOfMeal);
            }

            mealRepository.save(meal);
        }
    }

    private MultipartFile mealImage(String imgPath) throws IOException {
        Path path = Paths.get(imgPath);
        return new MockMultipartFile("file", "file", "text/plain", Files.readAllBytes(path));
    }
}
