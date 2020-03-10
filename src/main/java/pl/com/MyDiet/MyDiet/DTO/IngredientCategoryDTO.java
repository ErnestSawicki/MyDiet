package pl.com.MyDiet.MyDiet.DTO;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class IngredientCategoryDTO {
    private Long id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
}
