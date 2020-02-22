package pl.com.MyDiet.MyDiet.data.model;


import javax.persistence.*;

@Entity
@Table(name = "ingredient_to_buy")
public class IngredientToBuy extends EntityBase {

    @Column(name = "ingredient_amount")
    Long ingredientAmount;

    // Relation part//

    @OneToOne(fetch = FetchType.LAZY)
    Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ShopList shopList;
}
