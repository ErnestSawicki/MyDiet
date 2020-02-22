package pl.com.MyDiet.MyDiet.data.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_to_buy")
@Getter @Setter
public class IngredientToBuy extends EntityBase {

    @Column(name = "ingredient_amount")
    private Long ingredientAmount;

    // Relation part//

    @OneToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ShopList shopList;
}
