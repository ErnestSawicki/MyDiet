package pl.com.MyDiet.MyDiet.services;

import org.springframework.transaction.annotation.Transactional;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;

public interface DailySetService {

    MealsAvailableToSetDTO getAvailableMeats(Long mealsAmount);
    DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO);
    boolean save(DailyMealSetDTO dailyMealSetDTO, String user);
}
