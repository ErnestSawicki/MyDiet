package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class IngredientDTO {
    private Long ingredientId;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String ingredientName;
    @NumberFormat
    @Min(0)
    private Long caloriesPer100g;
    private Long categoryToRemove;
    private IngredientCategoriesIdAndName categoryToAdd;
    @NotNull
    @NotEmpty(message = "Ingredient must have any categories.")
    private List<IngredientCategoriesIdAndName> ingredientCategoriesIdAndName = new ArrayList<>();



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

