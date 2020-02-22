package pl.com.MyDiet.MyDiet.DTO;

import lombok.Data;

@Data
public class SimpleMealsDTO {

    private Long id;
    private Long preparationTime;
    private String name;
    private Long calories;
}
