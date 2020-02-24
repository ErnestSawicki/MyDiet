package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

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
    private List<PartOfMeal> partsOfMealIngredientIdNameAmount = new ArrayList<>();
    private Long mealTypeToRemove;
    private MealTypeInner mealTypeToAdd;
    private List <MealTypeInner> mealTypeNameMealId =new ArrayList<>();



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

    public void saveMealTypeInner() {
        if (this.mealTypeToAdd != null
                && !mealTypeNameMealId.contains(mealTypeToAdd))
            mealTypeNameMealId.add(mealTypeToAdd);
    }

    public void removerMealTypeInner() {
        if (this.mealTypeToRemove != null)
            mealTypeNameMealId.removeIf(p -> (p.id).equals(mealTypeToRemove));
    }



    @Data
    @AllArgsConstructor()
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

    @Data
    @AllArgsConstructor()
    @NoArgsConstructor
    public static class MealTypeInner {
        private Long id;
        private MealTypeEnumeration mealTypeName;


        public static MealTypeInner valueOf(String value) {
            String[] values = value.split(";");
            return new MealTypeInner(Long.valueOf(values[1]), Enum.valueOf(MealTypeEnumeration.class, values[0]));

        }
    }

}
