package pl.com.MyDiet.MyDiet.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.DietRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.DietService;

@Service
public class DietServiceDefault implements DietService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DailySetRepository dailySetRepository;
    private final DietRepository dietRepository;

    @Autowired
    public DietServiceDefault(UserRepository userRepository, MealRepository mealRepository, DailySetRepository dailySetRepository, DietRepository dietRepository) {
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.dailySetRepository = dailySetRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    public boolean save(DietConfigurator dietConfigurator, String username) {
        Diet diet = dietConfigurator.copyProperties(userRepository, username, mealRepository, dailySetRepository);
        dietRepository.save(diet);
        dietConfigurator.clearDietConfiguration();
        return true;
    }
}
