package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.List;

public interface DailySetService {

    MealsAvailableToSetDTO getAvailableMeats(Long mealsAmount);
    DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO);
    boolean save(DailyMealSetDTO dailyMealSetDTO, String user);
    List<DailySet> getAllDailySet();

    List<DailySet> getAllUserDailySet(User user);
}
