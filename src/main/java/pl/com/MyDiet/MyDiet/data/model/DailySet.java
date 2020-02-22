package pl.com.MyDiet.MyDiet.data.model;

import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily_sets")
public class DailySet extends EntityBase {

    @Column(name = "chosen_day_for_this_set")
    LocalDate chosenDayForThisSet;

    @Column(name = "calories")
    Long calories;

    @ManyToOne(fetch = FetchType.LAZY)
    User creatorUser;

    @ManyToMany
    @JoinTable(name = "diet_diet_set",
            joinColumns = @JoinColumn(name = "daily_set_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id"))
    List<Diet> dietsiet;

    @ManyToMany
    @JoinTable(name = "meal_diet_set",
            joinColumns = @JoinColumn(name = "daily_set_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    List<Meal> setMeals = new ArrayList<>();


}
