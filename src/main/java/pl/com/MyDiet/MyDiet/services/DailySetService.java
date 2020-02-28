package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.MealsAvailableToSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleDailySetDTO;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.List;

public interface DailySetService {

    MealsAvailableToSetDTO getAvailableMeats(Long mealsAmount);
    DailyMealSetDTO getDailySetAsDailyMealSetDTO(Long dailySetId);

    DailyMealSetDTO reloadPageWithSetVariable(DailyMealSetDTO dailyMealSetDTO);
    boolean save(DailyMealSetDTO dailyMealSetDTO, String user);
    List<DailySet> getAllDailySet();
    List<DailySet> getAllUserDailySet(User user);
    List<SimpleDailySetDTO> getAllDailySetDTOToDisplay();
    List<SimpleDailySetDTO> getUserDailySetDTOToDisplay(User user);
    boolean checkUserIsDailySetOwner(Long dailySetId, User user);

    DailySet getOneDailyMealById(Long dailySetId);

    boolean modify(DailyMealSetDTO dailySetDTO, String username);
}
