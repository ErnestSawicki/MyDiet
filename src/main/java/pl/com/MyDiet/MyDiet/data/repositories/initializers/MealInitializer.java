package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import com.github.javafaker.Faker;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MealInitializer {

    public void createSampleMeal(MealRepository mealRepository,
                                 UserRepository userRepository,
                                 MealTypeRepository mealTypeRepository,
                                 PartOfMealRepository partOfMealRepository,
                                 IngredientRepository ingredientRepository,
                                 FileEntityRepository fileEntityRepository) throws IOException {
        Faker faker = new Faker();


        //manual
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


        // here starts quick way
        Set<PartOfMeal> partOfMeals = new HashSet<>();
        partOfMeals.add(createPartOfMeal("milk whole", 250L, ingredientRepository, partOfMealRepository));
        partOfMeals.add(createPartOfMeal("honey", 20L, ingredientRepository, partOfMealRepository));
        partOfMeals.add(createPartOfMeal("porridge oats", 20L, ingredientRepository, partOfMealRepository));
        createMeal("oatmeal",
                "Combine oats, milk, water, salt, and cinnamon in a medium saucepan. Bring to a boil, then reduce heat to low.\n" +
                "Simmer uncovered for 3 to 5 minutes until thickened, stirring occasionally. Remove from heat and let cool slightly.\n" +
                "Divide equally between two bowls. Drizzle each serving with 1/2 teaspoon honey.",
                10L,
                userRepository.findUserByUsername("user"),
                mealTypeRepository.findByMealTypeName(MealTypeEnumeration.BREAKFAST),
                mealRepository, "src/main/webapp/images/testImages/meals/oatmeal.PNG",
                fileEntityRepository, partOfMeals);

        //randoms
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

    private void createMeal(String mealName, String recipe, Long preparationTime, User creator, MealType mealType, MealRepository mealRepository, String filePath , FileEntityRepository fileEntityRepository, Set<PartOfMeal> partOfMeals) throws IOException {
        Meal meal = new Meal();
        meal.setName(mealName);
        meal.setRecipe(recipe);
        meal.setPreparationTime(preparationTime);
        meal.setCalories(238L);
        meal.setCreatorUser(creator);
        meal.getMealTypes().add(mealType);
        mealType.getMeals().add(meal);

        partOfMeals.forEach(p -> {
            meal.getPartsOfMeal().add(p);
            p.setMeal(meal);
        });
        /*for (int j = 0; j < new Random().nextInt(6); j++) {
            PartOfMeal partOfMeal = new PartOfMeal();
            partOfMeal.setAmount(1L + (long) (Math.random() * (300L - 1L)));
            partOfMeal.setIngredient(ingredientRepository.findAll().get(new Random().nextInt(15)));
            meal.getPartsOfMeal().add(partOfMeal);
            partOfMeal.setMeal(meal);
            partOfMealRepository.save(partOfMeal);
        }*/
        MultipartFile file = mealImage(filePath);
        FileEntity mealFileEntity = new FileEntity();
        mealFileEntity.setContentType(file.getContentType());
        mealFileEntity.setFileName(file.getOriginalFilename());
        mealFileEntity.setData(file.getBytes());
        fileEntityRepository.save(mealFileEntity);
        meal.setMealFile(mealFileEntity);
        mealRepository.save(meal);
    }

    private MultipartFile mealImage(String imgPath) throws IOException {
        Path path = Paths.get(imgPath);
        return new MockMultipartFile("file", "file", "text/plain", Files.readAllBytes(path));
    }

    private PartOfMeal createPartOfMeal(String ingredientName, Long amount, IngredientRepository ingredientRepository, PartOfMealRepository partOfMealRepository){
        PartOfMeal partOfMeal = new PartOfMeal();
        partOfMeal.setIngredient(ingredientRepository.getByName(ingredientName));
        partOfMeal.setAmount(amount);
        partOfMealRepository.save(partOfMeal);
        return partOfMeal;
    }
}
