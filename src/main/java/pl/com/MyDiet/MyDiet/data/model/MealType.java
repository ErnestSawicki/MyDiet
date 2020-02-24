package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meal_types")
@Getter
@Setter
public class MealType extends EntityBase {

    @Column(name = "meal_type_name")
    @Enumerated(EnumType.STRING)
    private MealTypeEnumeration mealTypeName;


    // Relation part//

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "meal_meal_type",
            joinColumns = @JoinColumn(name = "meal_type_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> meals = new ArrayList<>();

}
