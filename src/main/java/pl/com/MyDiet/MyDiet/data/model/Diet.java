package pl.com.MyDiet.MyDiet.data.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diets")
public class Diet extends EntityBase {

    @Column(name = "diet_name")
    String dietName;

    @Column(name = "calories")
    Long calories;

    @Column(name = "start_day")
    LocalDate startDay;

    @Column(name = "period_in_days")
    Long periodDays;

    @Column(name = "description")
    String description;


    // Relation part//

    @ManyToOne(fetch = FetchType.LAZY)
    User creatorUser;

    @ManyToMany(mappedBy = "diets")
    List<DailySet> dailySet;

    @OneToMany(mappedBy = "ownedDiet")
    List<User> dietOwner;

}
