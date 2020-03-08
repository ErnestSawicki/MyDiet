package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.UserCalendar;

import java.time.LocalDate;

public interface UserCalendarRepository extends JpaRepository<UserCalendar, Long> {

    UserCalendar findByUserAndDate(User user, LocalDate date);
}
