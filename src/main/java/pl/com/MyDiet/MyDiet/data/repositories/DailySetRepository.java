package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.List;

public interface DailySetRepository extends JpaRepository<DailySet, Long> {
    List<DailySet> findAllByCreatorUser(User user);
    List<DailySet> findAllByDiets(Diet diet);
}
