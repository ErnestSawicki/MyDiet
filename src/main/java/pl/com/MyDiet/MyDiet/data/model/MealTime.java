package pl.com.MyDiet.MyDiet.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;

import javax.persistence.*;

@Entity
@Table(name = "meal_time")
@Getter
@Setter
public class MealTime extends EntityBase {

    @Column(name = "meal_type_name")
    @Enumerated(EnumType.STRING)
    private MealTypeEnumeration mealTypeName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DailySet dailySet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Meal meal;

}
