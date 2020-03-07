package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user_calendar")
@Getter @Setter
public class UserCalendar extends EntityBase {

    @ManyToOne
    private User user;

    private LocalDate date;

    @ManyToOne
    private DailySet dailySet;
}
