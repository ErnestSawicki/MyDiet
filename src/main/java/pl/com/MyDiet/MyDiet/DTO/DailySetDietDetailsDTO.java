package pl.com.MyDiet.MyDiet.DTO;

import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.List;

public class DailySetDietDetailsDTO {
    private Long calories;
    private Long mealAmount;
    private User creatorUser;
    private List<MealTimeDietDetailsDTO> mealTimeDietDetailsDTOs;
}
