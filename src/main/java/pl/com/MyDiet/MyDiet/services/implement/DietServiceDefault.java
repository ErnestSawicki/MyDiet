package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.DTO.DietDetailsDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleDailySetDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.DietRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.DailySetService;
import pl.com.MyDiet.MyDiet.services.DietService;

import java.util.List;

@Service
@Slf4j
public class DietServiceDefault implements DietService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DailySetRepository dailySetRepository;
    private final DietRepository dietRepository;
    private final DailySetService dailySetService;

    @Autowired
    public DietServiceDefault(UserRepository userRepository, MealRepository mealRepository, DailySetRepository dailySetRepository, DietRepository dietRepository, DailySetService dailySetService) {
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.dailySetRepository = dailySetRepository;
        this.dietRepository = dietRepository;
        this.dailySetService = dailySetService;
    }

    @Override
    public boolean save(DietConfigurator dietConfigurator, String username) {
        Diet diet = dietConfigurator.copyProperties(userRepository, username, mealRepository, dailySetRepository);
        dietRepository.save(diet);
        dietConfigurator.clearDietConfiguration();
        return true;
    }

    @Override
    public DietDetailsDTO getDietDetails(Long dietId) {
        DietDetailsDTO dietDetailsDTO = new DietDetailsDTO();
        log.debug("DietService-getDietDetails: dietId={}", dietId);
        Diet diet = dietRepository.getOne(dietId);
        log.debug("DietService-getDietDetails: diet={}", diet.toString());
        dietDetailsDTO.setCreatorUser(diet.getCreatorUser());
        dietDetailsDTO.setDescription(diet.getDescription());
        dietDetailsDTO.setDietName(diet.getDietName());
        dietDetailsDTO.setDuration(diet.getDuration());
        dietDetailsDTO.setDailySets(dailySetService.getAllDailySetsDTOToDisplay(diet));
        log.debug("DietService-getDietDetails: dietDetailsDTO={}", dietDetailsDTO);
        return dietDetailsDTO;
    }

    @Override
    public List<Diet> getAllDiets() {
        log.debug("DietService-getAllDiets: started...");
        List<Diet> diets = dietRepository.findAll();
        log.debug("DietService-getAllDiets: ... finished");
        return diets;
    }
}
