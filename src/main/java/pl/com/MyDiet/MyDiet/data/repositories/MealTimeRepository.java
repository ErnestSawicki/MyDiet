package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.MealTime;

import java.util.List;

public interface MealTimeRepository extends JpaRepository<MealTime, Long> {
    List<MealTime> findAllByDailySet(DailySet dailySet);
}
