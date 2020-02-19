package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

@Data
public class IngredientDTO {
    private Long ingredientId;
    private String ingredientName;
    @NumberFormat
    private Long caloriesPer100g;
    private Long categoryToRemove;
    private IngredientCategoriesIdAndName categoryToAdd;
    private List<IngredientCategoriesIdAndName> ingredientCategoriesIdAndName =new ArrayList<>();


    public void saveCategory() {
        if (this.categoryToAdd != null && !ingredientCategoriesIdAndName.contains(categoryToAdd)) {
            ingredientCategoriesIdAndName.add(this.categoryToAdd);
        }
    }
    public void removeCategory() {
        if (categoryToRemove != null) {
            ingredientCategoriesIdAndName.removeIf(p -> (p.getId()).equals(categoryToRemove));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientCategoriesIdAndName {
        private Long id;
        private String name;

        public static IngredientCategoriesIdAndName valueOf(String value) {
            String[] values = value.split(";");
            return new IngredientCategoriesIdAndName(Long.valueOf(values[0]), values[1]);
        }
    }

}

