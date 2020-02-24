package pl.com.MyDiet.MyDiet.DTO;


import lombok.Data;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.User;


import java.util.ArrayList;
import java.util.List;

@Data
public class DailyMealSetDTO {

    private Long mealAmount;
    private Long calories;
    private User creatorUser;

    private List<Meal> meals = new ArrayList<>();

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
