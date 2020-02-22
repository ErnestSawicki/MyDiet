package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.services.DailySetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DailySetServiceServiceDefault implements DailySetService {
    private final MealRepository mealRepository;
    private final MealTypeRepository mealTypeRepository;


    @Autowired
    public DailySetServiceServiceDefault(MealRepository mealRepository, MealTypeRepository mealTypeRepository) {
        this.mealRepository = mealRepository;
        this.mealTypeRepository = mealTypeRepository;
    }


    @Override
    public MealsAvailableToSetDTO getAvailableMeats(Long mealsAmount) {

        MealsAvailableToSetDTO mealsAvailableToSetDTO = new MealsAvailableToSetDTO();
        log.info("DailySetServiceDefault-BREAKFAST meals: {}",mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.BREAKFAST)).toString());
        mealsAvailableToSetDTO.setBreakfast(convertToDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.BREAKFAST))));

        log.info("DailySetServiceDefault-DINNER meals: {}",mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.DINNER)).toString());
        mealsAvailableToSetDTO.setDinner(convertToDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.DINNER))));

        log.info("DailySetServiceDefault-SUPPER meals: {}",mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SUPPER)).toString());
        mealsAvailableToSetDTO.setSupper(convertToDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SUPPER))));
        if (mealsAmount == 5) {

            log.info("DailySetServiceDefault-SECOND_BREAKFAST meals: {}",mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SECOND_BREAKFAST)).toString());
            mealsAvailableToSetDTO.setSecondBreakfast(convertToDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SECOND_BREAKFAST))));

            log.info("DailySetServiceDefault-TEA meals: {}",mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.TEA)).toString());
            mealsAvailableToSetDTO.setTea(convertToDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.TEA))));
        }
        log.info("DailySetServiceDefault mealsAvailableToSetDTO: breakfast size: {}, dinner size {}, supper size {}",
                mealsAvailableToSetDTO.getBreakfast().size(),
                mealsAvailableToSetDTO.getDinner().size(),
                mealsAvailableToSetDTO.getSupper().size());
        return mealsAvailableToSetDTO;
    }

    private List<SimpleMealsDTO> convertToDTO(List<Meal> meals) {

        /*if (meals == null || !meals.get(0).getClass().equals(Meal.class))
            return new ArrayList<>();*/
        log.info("DailySetServiceDefault-convertToDto: Incoming meals {}", meals.toString());
        return meals.stream().filter(Objects::nonNull)
                .map(p -> {
                    SimpleMealsDTO simpleMealsDTO = new SimpleMealsDTO();
                    simpleMealsDTO.setId(p.getId());
                    simpleMealsDTO.setName(p.getName());
                    simpleMealsDTO.setPreparationTime(p.getPreparationTime());
                    simpleMealsDTO.setCalories(p.getCalories());
                    log.info("DailySetServiceDefault-convertToDto: ... converted to DTO");
                    return simpleMealsDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO) {
        Long calories = 0L;
        List<Long> collect = dailyMealSetDTO.getSimpleMealsDTO().stream().map(SimpleMealsDTO::getCalories).collect(Collectors.toList());
        for (Long l : collect) {
            calories += l;
        }
        dailyMealSetDTO.setCalories(calories);
        return dailyMealSetDTO;
    }


    @Override
    @Transactional
    public boolean save(DailyMealSetDTO dailyMealSetDTO, String user) {
        /*if (dailyMealSetDTO == null
                || dailyMealSetDTO.getSimpleMealsDTO().isEmpty()
                || dailyMealSetDTO.getMealAmount() == null
                || dailyMealSetDTO.getMealAmount() < 3
                || dailyMealSetDTO.getCalories() == null)
            //todo
            return false;
        log.info("condition pass");
        DailySet dailySet= new DailySet();

        log.info("Try to save meal= {}", meal.getName());
        System.out.println(meal.getPartsOfMeal().isEmpty());
        mealRepository.save(meal);
        log.info("Saved {}", meal.getName());*/
        return true;

    }
}
