package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.DTO.DietDetailsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.config.converters.LocalDataConverter;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.model.UserCalendar;
import pl.com.MyDiet.MyDiet.data.repositories.*;
import pl.com.MyDiet.MyDiet.services.DailySetService;
import pl.com.MyDiet.MyDiet.services.DietService;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class DietServiceDefault implements DietService {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DailySetRepository dailySetRepository;
    private final DietRepository dietRepository;
    private final DailySetService dailySetService;
    private final UserCalendarRepository userCalendarRepository;

    @Autowired
    public DietServiceDefault(UserRepository userRepository, MealRepository mealRepository, DailySetRepository dailySetRepository, DietRepository dietRepository, DailySetService dailySetService, UserCalendarRepository userCalendarRepository) {
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.dailySetRepository = dailySetRepository;
        this.dietRepository = dietRepository;
        this.dailySetService = dailySetService;
        this.userCalendarRepository = userCalendarRepository;
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

    @Override
    public void assignUserDietFromDate(String username, LocalDate startDate, Long dietId) {
        Diet diet = dietRepository.getOne(dietId);
        for(int i =0; i < diet.getDailySet().size(); i++){
            UserCalendar userCalendar = new UserCalendar();
            userCalendar.setUser(userRepository.findUserByUsername(username));
            userCalendar.setDailySet(diet.getDailySet().get(i));
            userCalendar.setDate(startDate);
            startDate = startDate.plusDays(1L);
            userCalendarRepository.save(userCalendar);
        }
    }
}
