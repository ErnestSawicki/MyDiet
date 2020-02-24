package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMealsDTO {

    private Long id;
    private String name;
    private Long calories;
    private MealTypeEnumeration mealType;

    public static  SimpleMealsDTO valueOf(String value) {
        String[] values = value.split(";");
        return new SimpleMealsDTO(Long.valueOf(values[0]), values[1], Long.valueOf(values[2]), MealTypeEnumeration.valueOf(values[3]));
    }
    @Override
    public String toString() {
        return "SimpleMealsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
