package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.MealTime;

public interface MealTimeRepository extends JpaRepository<MealTime, Long> {
}
