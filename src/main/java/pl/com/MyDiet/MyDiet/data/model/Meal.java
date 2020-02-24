package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Meal")
@Getter
@Setter
public class Meal extends EntityBase {

    @Column(name = "calories")
    private Long calories;

    @Column(name = "name")
    private String name;


    @Column(name = "reciple")
    private String recipe;

    @Column(name = "preparation_time")
    private Long preparationTime;

    // Relation part//

    @OneToMany(fetch = FetchType.LAZY, mappedBy ="meal" )
    private List<MealTime> mealTime;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PartOfMeal> partsOfMeal = new ArrayList<>();

    @ManyToMany(mappedBy = "meals", fetch = FetchType.LAZY)
    List<MealType> mealTypes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User creatorUser;

    @OneToMany(fetch = FetchType.LAZY)
    List<DailySet> dailySets = new ArrayList<>();

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                ", preparationTime=" + preparationTime +
                '}';
    }
}
