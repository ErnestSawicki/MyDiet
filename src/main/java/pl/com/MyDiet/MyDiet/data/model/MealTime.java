package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "meal_time")
@Getter
public class MealTime extends EntityBase{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MealType mealType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Meal meal;
}
