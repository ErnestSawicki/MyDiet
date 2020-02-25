package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealTime;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.DailySetService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DailySetServiceDefault implements DailySetService {
    private final MealRepository mealRepository;
    private final MealTypeRepository mealTypeRepository;
    private final UserRepository userRepository;
    private final DailySetRepository dailySetRepository;


    @Autowired
    public DailySetServiceDefault(MealRepository mealRepository, MealTypeRepository mealTypeRepository, UserRepository userRepository, DailySetRepository dailySetRepository) {
        this.mealRepository = mealRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.userRepository = userRepository;
        this.dailySetRepository = dailySetRepository;
    }


    @Override
    public MealsAvailableToSetDTO getAvailableMeats(Long mealsAmount) {

        MealsAvailableToSetDTO mealsAvailableToSetDTO = new MealsAvailableToSetDTO();
        log.info("DailySetServiceDefault-BREAKFAST meals: {}", mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.BREAKFAST)).toString());
        mealsAvailableToSetDTO.setBreakfast(convertMealsToSimpleMealsDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.BREAKFAST))));

        log.info("DailySetServiceDefault-DINNER meals: {}", mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.DINNER)).toString());
        mealsAvailableToSetDTO.setDinner(convertMealsToSimpleMealsDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.DINNER))));

        log.info("DailySetServiceDefault-SUPPER meals: {}", mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SUPPER)).toString());
        mealsAvailableToSetDTO.setSupper(convertMealsToSimpleMealsDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SUPPER))));
        if (mealsAmount == 5) {

            log.info("DailySetServiceDefault-SECOND_BREAKFAST meals: {}", mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SECOND_BREAKFAST)).toString());
            mealsAvailableToSetDTO.setSecondBreakfast(convertMealsToSimpleMealsDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.SECOND_BREAKFAST))));

            log.info("DailySetServiceDefault-TEA meals: {}", mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.TEA)).toString());
            mealsAvailableToSetDTO.setTea(convertMealsToSimpleMealsDTO(mealRepository.findAllByMealTypes(mealTypeRepository.findByMealTypeName(MealTypeEnumeration.TEA))));
        }
        log.info("DailySetServiceDefault mealsAvailableToSetDTO: breakfast size: {}, dinner size {}, supper size {}",
                mealsAvailableToSetDTO.getBreakfast().size(),
                mealsAvailableToSetDTO.getDinner().size(),
                mealsAvailableToSetDTO.getSupper().size());
        return mealsAvailableToSetDTO;
    }

    private List<SimpleMealsDTO> convertMealsToSimpleMealsDTO(List<Meal> meals) {

        log.debug("DailySetServiceDefault-convertToDto: Incoming meals {}", meals.toString());
        return meals.stream().filter(Objects::nonNull)
                .map(p -> {
                    SimpleMealsDTO simpleMealsDTO = new SimpleMealsDTO();
                    simpleMealsDTO.setId(p.getId());
                    simpleMealsDTO.setName(p.getName());
                    simpleMealsDTO.setCalories(p.getCalories());
                    log.debug("DailySetServiceDefault-convertToDto: ... converted to DTO");
                    return simpleMealsDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO) {
        dailyMealSetDTO.countCalories();
        return dailyMealSetDTO;
    }

    @Override
    @Transactional
    public boolean save(DailyMealSetDTO dailyMealSetDTO, String username) {
        /*if (dailyMealSetDTO == null
                || dailyMealSetDTO.getSimpleMealsDTO().isEmpty()
                || dailyMealSetDTO.getMealAmount() == null
                || dailyMealSetDTO.getMealAmount() < 3
                || dailyMealSetDTO.getCalories() == null){
            return false;
        }*/

        log.debug("DailySetServiceDefault-save: started ...");
        DailySet dailySet = new DailySet();

        dailySet.setCalories(dailyMealSetDTO.getCaloriesPicked());
        dailySet.setCreatorUser(userRepository.findUserByUsername(username));
        dailySet.setMealAmount(dailyMealSetDTO.getMealAmount());

        log.debug("DailySetServiceDefault-save: simpleMealsDTO size - req. min 3 actual size = {}", dailyMealSetDTO.getMeals().size());
        log.debug("DailySetServiceDefault-save: simpleMealsDTO size - req. min 3 actual size = {}", dailyMealSetDTO.getMeals().size());

        List<MealTime> mealTimes = dailyMealSetDTO.getMeals().stream().map(p -> {
            MealTime mealTime = new MealTime();
            mealTime.setDailySet(dailySet);
            mealTime.setMeal(mealRepository.getOne(p.getId()));
            mealTime.setMealTypeName(p.getMealType());
            return mealTime;
        }).collect(Collectors.toList());
dailySet.setMealTime(mealTimes);
        dailySetRepository.save(dailySet);
//mealTimes.stream().forEach();
        log.debug("DailySetServiceDefault-save: dailySet {}", dailySet);
        log.debug("DailySetServiceDefault-save: ... finished");
        return true;

    }
}
