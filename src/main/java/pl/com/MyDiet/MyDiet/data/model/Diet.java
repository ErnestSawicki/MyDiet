package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diets")
@Getter @Setter
@ToString
public class Diet extends EntityBase {

    @Column(name = "diet_name")
    private String dietName;
    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "duration")
    private Long duration;

    // Relation part//
    @ManyToOne(fetch = FetchType.LAZY)
    private User creatorUser;

    @ManyToMany(mappedBy = "diets", fetch = FetchType.LAZY)
    private List<DailySet> dailySet = new ArrayList<>();

    @OneToMany(mappedBy = "ownedDiet", fetch = FetchType.LAZY)
    private List<User> dietOwner;

}