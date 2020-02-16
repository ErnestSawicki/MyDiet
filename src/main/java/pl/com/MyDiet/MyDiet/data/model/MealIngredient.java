package pl.com.MyDiet.MyDiet.data.model;

import javax.persistence.*;

@Entity
@Table(name = "meal_ingredient")
public class MealIngredient extends EntityBase {

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    private Meal meal;

    @OneToOne
    private Ingredient ingredient;
}
