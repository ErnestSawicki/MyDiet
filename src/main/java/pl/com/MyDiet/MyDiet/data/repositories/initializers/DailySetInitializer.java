package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.MealTime;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTimeRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import java.util.Random;

@Slf4j
public class DailySetInitializer {

    public void createDailySetSamples(DailySetRepository dailySetRepository, MealRepository mealRepository, UserRepository userRepository, MealTimeRepository mealTimeRepository) {
        for (int i = 0; i < 10; i++) {
            DailySet dailySet = new DailySet();
            if (new Random().nextBoolean()) {
                dailySet.setMealAmount(3L);
                for (int k = 0; k < 3; k++) {
                    MealTime mealTime = new MealTime();
                    mealTime.setMeal(mealRepository.findAll().get(new Random().nextInt(20)));
                    if (k == 0)
                        mealTime.setMealTypeName(MealTypeEnumeration.BREAKFAST);
                    if (k == 1)
                        mealTime.setMealTypeName(MealTypeEnumeration.DINNER);
                    if (k == 2)
                        mealTime.setMealTypeName(MealTypeEnumeration.SUPPER);
                    mealTime.setDailySet(dailySet);
                    dailySet.getMealTime().add(mealTime);
                }
            }else {
                dailySet.setMealAmount(5L);

                for (int k = 0; k < 5; k++) {
                    MealTime mealTime = new MealTime();
                    mealTime.setMeal(mealRepository.findAll().get(new Random().nextInt(20)));
                    if (k == 0)
                        mealTime.setMealTypeName(MealTypeEnumeration.BREAKFAST);
                    if (k == 1)
                        mealTime.setMealTypeName(MealTypeEnumeration.DINNER);
                    if (k == 2)
                        mealTime.setMealTypeName(MealTypeEnumeration.SUPPER);
                    if (k == 3)
                        mealTime.setMealTypeName(MealTypeEnumeration.SECOND_BREAKFAST);
                    if (k == 4)
                        mealTime.setMealTypeName(MealTypeEnumeration.TEA);

                    mealTime.setDailySet(dailySet);
                    dailySet.getMealTime().add(mealTime);
                }
            }
            dailySet.setCreatorUser(userRepository.findAll().get(new Random().nextInt(8)));
            dailySet.setCalories(3000L);
            dailySetRepository.save(dailySet);
        }
    }
}
