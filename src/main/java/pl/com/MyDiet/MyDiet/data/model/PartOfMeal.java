package pl.com.MyDiet.MyDiet.data.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "parts_of_meal")
@Getter
@Setter
public class PartOfMeal extends EntityBase {

    @Column(name = "amount")
    private Long amount;

    // Relation part//

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id")
    private Meal meal;

}
