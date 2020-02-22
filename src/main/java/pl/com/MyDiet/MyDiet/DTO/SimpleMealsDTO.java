package pl.com.MyDiet.MyDiet.DTO;

import lombok.Data;

@Data
public class SimpleMealsDTO {
    Long id;
    Long preparationTime;
    String name;
    Long calories;
}
