package pl.com.MyDiet.MyDiet.DTO;


import lombok.Data;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.User;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DailyMealSetDTO {

    private Long mealAmount;
    private Long caloriesPicked;
    private Long calories=0L;
    private User creatorUser;
    private boolean mealPicked=false;
    private List<SimpleMealsDTO> meals = new ArrayList<>();


    public void countCalories(){
        Long result =0L;
        List<Long> collect = meals.stream().map(SimpleMealsDTO::getCalories).collect(Collectors.toList());
        for (Long l :collect) {
            result+=l;
        }
        this.calories=result;
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
