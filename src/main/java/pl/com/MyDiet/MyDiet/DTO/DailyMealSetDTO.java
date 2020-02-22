package pl.com.MyDiet.MyDiet.DTO;


import lombok.Data;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.User;


import java.util.ArrayList;
import java.util.List;

@Data
public class DailyMealSetDTO {

    Long amongOfMeals;
    Long calories;
    User creatorUser;
    List<SimpleMealsDTO> simpleMealsDTO = new ArrayList<>();
}
