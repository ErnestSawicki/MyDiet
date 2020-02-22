package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diets")
@Getter @Setter
public class Diet extends EntityBase {

    @Column(name = "diet_name")
    private String dietName;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "start_day")
    private LocalDate startDay;

    @Column(name = "period_in_days")
    private Long periodDays;

    @Column(name = "description")
    private String description;


    // Relation part//

    @ManyToOne(fetch = FetchType.LAZY)
    private User creatorUser;

    @ManyToMany(mappedBy = "diets", fetch = FetchType.LAZY)
    private List<DailySet> dailySet;

    @OneToMany(mappedBy = "ownedDiet", fetch = FetchType.LAZY)
    private List<User> dietOwner;

}
