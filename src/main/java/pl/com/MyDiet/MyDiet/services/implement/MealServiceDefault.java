package pl.com.MyDiet.MyDiet.services.implement;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;

import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleIngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.PartOfMeal;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;
import pl.com.MyDiet.MyDiet.data.repositories.*;
import pl.com.MyDiet.MyDiet.services.IngredientService;
import pl.com.MyDiet.MyDiet.services.MealService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealServiceDefault implements MealService {

    private final MealRepository mealRepository;
    private final IngredientService ingredientService;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final MealTypeRepository mealTypeRepository;
    private final FileEntityRepository fileEntityRepository;


    @Autowired
    public MealServiceDefault(MealRepository mealRepository, IngredientService ingredientService, UserRepository userRepository, IngredientRepository ingredientRepository, MealTypeRepository mealTypeRepository, FileEntityRepository fileEntityRepository) {
        this.mealRepository = mealRepository;
        this.ingredientService = ingredientService;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.fileEntityRepository = fileEntityRepository;
    }

    @Override
    public MealCreateDTO rebuildFormWhenAddIngredient(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.savePartOfMeal();
        mealCreateDTO.setCalories(countCalories(mealCreateDTO));
        return mealCreateDTO;
    }

    @Override
    public MealCreateDTO rebuildFormWhenAddMealType(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.saveMealTypeInner();
        return mealCreateDTO;
    }

    @Override
    public MealCreateDTO rebuildFormWhenDeletedIngredient(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.removerPartOfMeal();
        mealCreateDTO.setCalories(countCalories(mealCreateDTO));
        return mealCreateDTO;
    }

    @Override
    public MealCreateDTO rebuildFormWhenDeletedMealType(MealCreateDTO mealCreateDTO) {
        mealCreateDTO.removerMealTypeInner();
        return mealCreateDTO;
    }

    @Override
    public List<MealType> getAllMealType() {
        return mealTypeRepository.findAll();
    }

    @Override
    public List<MealType> getMealType(MealCreateDTO mealCreateDTO) {
        List<MealType> allMealType = getAllMealType();
        List<Long> collect = mealCreateDTO.getMealTypeNameMealId().stream().map(MealCreateDTO.MealTypeInner::getId).collect(Collectors.toList());
        allMealType.removeIf(p -> collect.contains(p.getId()));
        return allMealType;
    }

    @Override
    public List<SimpleIngredientDTO> getAllIngredientsDTO() {
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

    @Override
    public Meal getMealById(Long mealId) {
        return mealRepository.getOne(mealId);
    }

    @Override
    public List<SimpleIngredientDTO> getIngredientsDTO(MealCreateDTO mealCreateDTO) {
        List<SimpleIngredientDTO> availableIngredients = getAllIngredientsDTO();
        List<Long> collect = mealCreateDTO.getPartsOfMealIngredientIdNameAmount().stream().map(MealCreateDTO.PartOfMeal::getIngredientId).collect(Collectors.toList());
        availableIngredients.removeIf(p -> collect.contains(p.getId()));
        return availableIngredients;
    }


    @Override
    @Transactional
    public boolean saveMeal(MealCreateDTO mealCreateDTO, String principal) {
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
        log.debug("MealServiceDefault: mealCreateDTO");
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


        mealCreateDTO.getMealTypeNameMealId().forEach(p -> {
            log.debug("MealServiceDefault: mealType from repository={}", mealTypeRepository.getOne(p.getId()).getMealTypeName());
            MealType mealType = mealTypeRepository.getOne(p.getId());
            meal.getMealTypes().add(mealType);
            mealType.getMeals().add(meal);
        });

        meal.setMealFile(mealCreateDTO.getMealFile());

        log.debug("MealServiceDefault: meal={}", meal.toString());

        log.debug("DailySetServiceDefault-save: try save meal {}", meal.getName());
        mealRepository.save(meal);
        log.info("DailySetServiceDefault-save: save meal {} succes", mealRepository.findById(meal.getId()).toString());
        return true;
    }

    @Override
    public List<Meal> getAllMeals() {
        log.debug("MealServiceDefault-getAllMeals: Started...");
        List<Meal> meals = mealRepository.findAll();
        log.debug("MealServiceDefault-getAllMeals: ... finished all meals from repository");
        return meals;
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
