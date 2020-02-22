package pl.com.MyDiet.MyDiet.data.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ingredient_category")
@Getter @Setter
public class IngredientCategory extends EntityBase {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "ingredientCategories")
    private List<Ingredient> ingredients;


}
