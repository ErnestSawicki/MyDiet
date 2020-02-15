package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Getter @Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories_per_100g")
    private Long caloriesPer100gram;

    @Column(name = "product_categories")
    private List<ProductCategory> productCategories;
}
