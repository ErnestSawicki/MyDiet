package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class MealCreateDTO {
    private Long MealId;
    private Long calories;
    private String name;
    private User owner;
    private Long ingredientToAddAmount;
    private String mealDescription;
    private Long preparationTimeInMinutes;
    private PartOfMeal ingredientToAdd;
    private Long ingredientToRemove;
    List<PartOfMeal> partsOfMealIngredientIdNameAmount = new ArrayList<>();


    public void savePartOfMeal() {
        if (this.ingredientToAdd != null
                && !partsOfMealIngredientIdNameAmount.contains(ingredientToAdd))
            ingredientToAdd.setIngredientAmount(ingredientToAddAmount);
            partsOfMealIngredientIdNameAmount.add(ingredientToAdd);
    }

    public void removerPartOfMeal() {
        if (this.ingredientToRemove != null)
            partsOfMealIngredientIdNameAmount.removeIf(p -> (p.ingredientId).equals(ingredientToRemove));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PartOfMeal {
        private Long ingredientId;
        private String name;
        private Long ingredientAmount;

        public static PartOfMeal valueOf(String value) {
            String[] values = value.split(";");
            return new PartOfMeal(Long.valueOf(values[0]),values[1], Long.valueOf(values[2]));

        }
    }
}
