package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "meal_ingredient")
public class MealIngredient extends EntityBase {

    @Column(name = "amount")
    private Integer amount;

    @OneToOne
    private Ingredient ingredient;
}
