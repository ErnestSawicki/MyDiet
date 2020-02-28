package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleDailySetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.MealTime;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.*;
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
    private final MealTimeRepository mealTimeRepository;


    @Autowired
    public DailySetServiceDefault(MealRepository mealRepository, MealTypeRepository mealTypeRepository, UserRepository userRepository, DailySetRepository dailySetRepository, MealTimeRepository mealTimeRepository) {
        this.mealRepository = mealRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.userRepository = userRepository;
        this.dailySetRepository = dailySetRepository;
        this.mealTimeRepository = mealTimeRepository;
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
    public DailyMealSetDTO getDailySetAsDailyMealSetDTO(Long dailySetId) {
        return convertDailySetToDailyMealSetDTO(getOneDailyMealById(dailySetId));
    }

    private DailyMealSetDTO convertDailySetToDailyMealSetDTO(DailySet dailySet) {
        DailyMealSetDTO dailyMealSetDTO = new DailyMealSetDTO();
        dailyMealSetDTO.setCaloriesPicked(dailySet.getCalories());
        dailyMealSetDTO.setCreatorUser(dailySet.getCreatorUser());
        dailyMealSetDTO.setMealAmount(dailySet.getMealAmount());
        dailyMealSetDTO.setMeals(dailySet.getMealTime().stream().map(p -> {
            SimpleMealsDTO simpleMealsDTO = new SimpleMealsDTO();
            simpleMealsDTO.setCalories(p.getMeal().getCalories());
            simpleMealsDTO.setName(p.getMeal().getName());
            simpleMealsDTO.setId(p.getMeal().getId());
            simpleMealsDTO.setMealType(p.getMealTypeName());
            return simpleMealsDTO;
        }).collect(Collectors.toList()));
        return dailyMealSetDTO;
    }


    @Override
    public DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO) {
        dailyMealSetDTO.setUpValuesCaloriesAndMealListQueue();
        return dailyMealSetDTO;
    }

    @Override
    @Transactional
    public boolean save(DailyMealSetDTO dailyMealSetDTO, String username) {
        log.debug("DailySetServiceDefault-save: started ...");
        dailyMealSetDTO.setUpValuesCaloriesAndMealListQueue();
        DailySet dailySet = dailyMealSetDTO.copyProperties(dailyMealSetDTO, userRepository, username, mealRepository);
        dailySetRepository.save(dailySet);
        log.debug("DailySetServiceDefault-save: dailySet {}", dailySet);
        log.debug("DailySetServiceDefault-save: ... finished");
        return true;
    }

    @Override
    public List<DailySet> getAllDailySet() {
        return dailySetRepository.findAll();
    }

    @Override
    public List<DailySet> getAllUserDailySet(User user) {
        return dailySetRepository.findAllByCreatorUser(user);
    }

    @Override
    public List<SimpleDailySetDTO> getAllDailySetDTOToDisplay() {
        return dailySetRepository.findAll().stream().filter(Objects::nonNull).map(this::convertDailySetToSimpleDailySetDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<SimpleDailySetDTO> getUserDailySetDTOToDisplay(User user) {
        return dailySetRepository.findAllByCreatorUser(user).stream().filter(Objects::nonNull).map(this::convertDailySetToSimpleDailySetDTO
        ).collect(Collectors.toList());
    }


    @Override
    public boolean checkUserIsDailySetOwner(Long dailySetId, User user) {
        return !dailySetRepository.existsById(dailySetId) && dailySetRepository.getOne(dailySetId).getCreatorUser() == user;
    }

    @Override
    public DailySet getOneDailyMealById(Long dailySetId) {
        return dailySetRepository.getOne(dailySetId);
    }

    @Transactional
    @Override
    public boolean modify(DailyMealSetDTO dailySetDTO, String username) {
        log.debug("DailySetServiceDefault-modify: started ...");
        dailySetDTO.setUpValuesCaloriesAndMealListQueue();
        DailySet dailySet = dailySetDTO.copyProperties(dailySetDTO, userRepository, username, mealRepository);
        mealTimeRepository.findAllByDailySet(dailySetRepository.getOne(dailySetDTO.getId())).forEach(mealTimeRepository::delete);
        dailySetRepository.save(dailySet);

        log.debug("DailySetServiceDefault-modify: dailySet {}", dailySet);
        log.debug("DailySetServiceDefault-modify: ... finished");
        return true;
    }

    private SimpleDailySetDTO.MealForDailyDTO convertMealToMealForDailyDTO(MealTime mealTime) {
        SimpleDailySetDTO.MealForDailyDTO mealForDailyDTO = new SimpleDailySetDTO.MealForDailyDTO();
        Meal meal = mealRepository.getOne(mealTime.getMeal().getId());
        mealForDailyDTO.setId(meal.getId());
        mealForDailyDTO.setMealFileId(meal.getMealFileId());
        mealForDailyDTO.setMealFile(meal.getMealFile());
        mealForDailyDTO.setCalories(meal.getCalories());
        mealForDailyDTO.setCreatorUser(meal.getCreatorUser().getUsername());
        mealForDailyDTO.setName(meal.getName());
        mealForDailyDTO.setPreparationTime(meal.getPreparationTime());
        mealForDailyDTO.setRecipe(meal.getRecipe());
        mealForDailyDTO.setMealTypeEnumeration(mealTime.getMealTypeName());
        log.debug("convertMealToMealForDailyDTO: dailySets name {}", mealForDailyDTO.getName());
        return mealForDailyDTO;
    }

    private SimpleDailySetDTO convertDailySetToSimpleDailySetDTO(DailySet dailySet) {
        SimpleDailySetDTO simpleDailySetDTO = new SimpleDailySetDTO();
        simpleDailySetDTO.setId(dailySet.getId());
        simpleDailySetDTO.setCalories(dailySet.getCalories());
        simpleDailySetDTO.setCalories(dailySet.getCalories());
        simpleDailySetDTO.setMealAmount(dailySet.getMealAmount());
        simpleDailySetDTO.setCreatorUserId(dailySet.getCreatorUser().getId());
        simpleDailySetDTO.setCreatorUserName(dailySet.getCreatorUser().getUsername());
        simpleDailySetDTO.setMealForDailyDTO(dailySet.getMealTime().stream().filter(Objects::nonNull).map(p -> mealTimeRepository.getOne(p.getId())).map(this::convertMealToMealForDailyDTO
        ).collect(Collectors.toList()));
        log.debug("convertDailySetToSimpleDailySetDTO: Get path/daily-set: daily set meal size = {}", dailySet.getMealTime().size());
        return simpleDailySetDTO;
    }


}
