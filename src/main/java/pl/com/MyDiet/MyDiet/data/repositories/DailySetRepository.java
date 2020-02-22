package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.DailySet;

public interface DailySetRepository extends JpaRepository<DailySet, Long> {
}
