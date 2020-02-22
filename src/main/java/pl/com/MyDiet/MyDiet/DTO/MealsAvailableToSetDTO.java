package pl.com.MyDiet.MyDiet.DTO;

import lombok.Data;
import pl.com.MyDiet.MyDiet.data.model.Meal;

import java.util.List;

@Data
public class MealsAvailableToSetDTO {
    List<SimpleMealsDTO> breakfast;
    List<SimpleMealsDTO> secondBreakfast;
    List<SimpleMealsDTO> dinner;
    List<SimpleMealsDTO> tea;
    List<SimpleMealsDTO> supper;
}
