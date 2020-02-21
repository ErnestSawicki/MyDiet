package pl.com.MyDiet.MyDiet.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleIngredientDTO {
    private Long id;
    private String name;
    private Long caloriesPer100gram;
    private List<IngredientCategoryDTO> ingredientCategories =new ArrayList<>();
}
