package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Getter @Setter
public class Ingredient extends EntityBase {

    @Column(name = "name")
    private String name;

    @Column(name = "calories_per_100g")
    private Long caloriesPer100gram;

    // Relation part//

    @ManyToMany
    @JoinTable(name = "ingredient_ingredient_category",
            joinColumns = @JoinColumn(name = "ingredients_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_category_id")
    )
    private List<IngredientCategory> ingredientCategories =new LinkedList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "meal_file_id")
    private FileEntity mealFile;

    @Column(name = "meal_file_id", insertable = false, updatable = false)
    private Long mealFileId;

}
