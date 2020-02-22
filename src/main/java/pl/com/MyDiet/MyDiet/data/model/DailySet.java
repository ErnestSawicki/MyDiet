package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily_sets")
@Getter @Setter
public class DailySet extends EntityBase {

    @Column(name = "chosen_day_for_this_set")
    private LocalDate chosenDayForThisSet;

    @Column(name = "amount_of_meals")
    private Long amongOfMeals;
    @Column(name = "calories")
    private Long calories;

    // Relation part//

    @ManyToOne(fetch = FetchType.LAZY)
    private User creatorUser;

    @ManyToMany
    @JoinTable(name = "diet_diet_set",
            joinColumns = @JoinColumn(name = "daily_set_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id"))
    private List<Diet> diets;

    @ManyToMany
    @JoinTable(name = "meal_diet_set",
            joinColumns = @JoinColumn(name = "daily_set_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> setMeals = new ArrayList<>();


}