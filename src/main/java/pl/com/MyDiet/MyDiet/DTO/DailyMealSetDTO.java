package pl.com.MyDiet.MyDiet.DTO;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.MealTime;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
public class DailyMealSetDTO {

    private Long mealAmount;
    private Long caloriesPicked;
    private Long calories = 0L;
    private User creatorUser;
    private boolean mealPicked = false;
    private List<SimpleMealsDTO> meals = new ArrayList<>();



    public void setUpValuesCaloriesAndMealListQueue() {
        countCalories();
        if (meals.size() == 5) {
            setUpMealsListQueue();
        }
    }

    private void countCalories() {
        Long result = 0L;
        List<Long> collect = meals.stream().map(SimpleMealsDTO::getCalories).collect(Collectors.toList());
        for (Long l : collect) {
            result += l;
        }
        this.calories = result;
    }

    private void setUpMealsListQueue() {
        Collections.swap(this.meals, 1, 2);
        Collections.swap(this.meals, 3, 4);
        Collections.swap(this.meals, 2, 3);
    }


    public DailySet copyProperties(DailyMealSetDTO dailyMealSetDTO,
                                   UserRepository userRepository,
                                   String username,
                                   MealRepository mealRepository) {
        log.debug("DailyMealSetDTO-copyProperties: Copy properties started... ");
        log.debug("DailyMealSetDTO-copyProperties: dailyMealSetDTO.toString()={}", dailyMealSetDTO.toString());
        DailySet dailySet = new DailySet();
        //dailySet setup by fields from dailyMealSetDTO
        dailySet.setCalories(dailyMealSetDTO.getCaloriesPicked());
        dailySet.setCreatorUser(userRepository.findUserByUsername(username));
        dailySet.setMealAmount(dailyMealSetDTO.getMealAmount());
        List<MealTime> mealTimes = dailyMealSetDTO.getMeals().stream().map(p -> {
            MealTime mealTime = new MealTime();
            mealTime.setDailySet(dailySet);
            mealTime.setMeal(mealRepository.getOne(p.getId()));
            mealTime.setMealTypeName(p.getMealType());
            return mealTime;
        }).collect(Collectors.toList());
        dailySet.setMealTime(mealTimes);

        log.debug("DailyMealSetDTO-copyProperties: ... copy properties finished");
        return dailySet;
    }


    @Override
    public String toString() {
        return "DailyMealSetDTO{" +
                "mealAmount=" + mealAmount +
                ", calories=" + calories +
                ", creatorUser=" + creatorUser +
                ", simpleMealsDTO=" + calories +
                '}';
    }
}
