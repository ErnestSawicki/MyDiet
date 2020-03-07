package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.UserCalendar;

public interface UserCalendarRepository extends JpaRepository<UserCalendar, Long> {
}
