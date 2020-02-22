package pl.com.MyDiet.MyDiet.services.implement;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;

import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleIngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.IngredientService;
import pl.com.MyDiet.MyDiet.services.MealService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealServiceDefault implements MealService {

    private final MealRepository mealRepository;
    private final IngredientService ingredientService;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;


    @Autowired
    public MealServiceDefault(MealRepository mealRepository, IngredientService ingredientService, UserRepository userRepository, IngredientRepository ingredientRepository) {
        this.mealRepository = mealRepository;
        this.ingredientService = ingredientService;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public MealCreateDTO rebuildFormWhenAddIngredient(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.savePartOfMeal();
        mealCreateDTO.setCalories(countCalories(mealCreateDTO));
        return mealCreateDTO;
    }

    @Override
    public MealCreateDTO rebuildFormWhenDeletedIngredient(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.removerPartOfMeal();
        mealCreateDTO.setCalories(countCalories(mealCreateDTO));
        return mealCreateDTO;
    }

    @Override
    public List<SimpleIngredientDTO> getIngredients(MealCreateDTO mealCreateDTO) {
        List<SimpleIngredientDTO> availableIngredients = getAllIngredients();
        List<Long> collect = mealCreateDTO.getPartsOfMealIngredientIdNameAmount().stream().map(MealCreateDTO.PartOfMeal::getIngredientId).collect(Collectors.toList());
        availableIngredients.removeIf(p -> collect.contains(p.getId()));
        return availableIngredients;
    }


    @Override
    @Transactional
    public boolean saveIngredient(MealCreateDTO mealCreateDTO, String principal) {
        if (mealCreateDTO == null
                || mealCreateDTO.getName() == null
                || mealCreateDTO.getMealDescription() == null
                || mealCreateDTO.getMealDescription().trim().isEmpty()
                || mealCreateDTO.getPreparationTimeInMinutes() == null
                || mealCreateDTO.getPreparationTimeInMinutes() < 0
                || mealCreateDTO.getPartsOfMealIngredientIdNameAmount().isEmpty()
                || mealRepository.existsByName(mealCreateDTO.getName())) {
            return false;
        }
        log.info("condition pass");
        Meal meal = new Meal();

        meal.setName(mealCreateDTO.getName().trim().toLowerCase());
        meal.setCreatorUser(userRepository.findUserByUsername(principal));
        meal.setCalories(mealCreateDTO.getCalories());
        meal.setPreparationTime(mealCreateDTO.getPreparationTimeInMinutes());
        meal.setRecipe(mealCreateDTO.getMealDescription());
        meal.setPartsOfMeal(mealCreateDTO.getPartsOfMealIngredientIdNameAmount().stream().map(p -> {
            PartOfMeal partOfMeal = new PartOfMeal();
            partOfMeal.setMeal(meal);
            partOfMeal.setAmount(p.getIngredientAmount());
            partOfMeal.setIngredient(ingredientRepository.getOne(p.getIngredientId()));
            return partOfMeal;
        }).collect(Collectors.toList()));


        log.info("Try to save meal= {}", meal.getName());
        System.out.println(meal.getPartsOfMeal().isEmpty());
        mealRepository.save(meal);
        log.info("Saved {}", meal.getName());
        return true;
    }

    @Override
    public List<SimpleIngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(p -> {
                    SimpleIngredientDTO simpleIngredientDTO = new SimpleIngredientDTO();
                    simpleIngredientDTO.setId(p.getId());
                    simpleIngredientDTO.setName(p.getName());
                    simpleIngredientDTO.setCaloriesPer100gram(p.getCaloriesPer100gram());
                    simpleIngredientDTO.setIngredientCategories(p.getIngredientCategories().stream()
                            .map(s -> {
                                IngredientCategoryDTO ingredientCategoryDTO = new IngredientCategoryDTO();
                                ingredientCategoryDTO.setId(s.getId());
                                ingredientCategoryDTO.setName(s.getName());
                                return ingredientCategoryDTO;
                            }).collect(Collectors.toList()));
                    return simpleIngredientDTO;
                }).collect(Collectors.toList());

    }

    private Long countCalories(MealCreateDTO mealCreateDTO) {

        List<Long> calories = mealCreateDTO.getPartsOfMealIngredientIdNameAmount().stream()
                .map(p -> {
                    Ingredient ingredient = ingredientRepository.findById(p.getIngredientId()).orElse(null);
                    if (ingredient != null) {
                        return ingredient.getCaloriesPer100gram() * p.getIngredientAmount() / 100;
                    } else {
                        return 0L;
                    }
                }).collect(Collectors.toList());

        Long result = 0L;
        for (Long l : calories) {
            result = result + l;
        }
        return result;

    }
}
