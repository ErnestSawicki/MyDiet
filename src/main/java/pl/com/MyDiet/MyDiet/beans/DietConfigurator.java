package pl.com.MyDiet.MyDiet.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import java.util.*;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter @Setter
@Slf4j
public class DietConfigurator {

    private String dietName;
    private String dietDescription;
    private Long duration;
    private Map<Integer, DailyMealSetDTO> dailySetDTOMap = new HashMap<>();

    public void clearDietConfiguration(){
        this.dietName = null;
        this.dietDescription = null;
        this.duration = null;
        this.dailySetDTOMap = Collections.emptyMap();
    }

    public Diet copyProperties(UserRepository userRepository,
                               String username,
                               MealRepository mealRepository,
                               DailySetRepository dailySetRepository){
        log.debug("DietConfigurator-copyProperties: copyProperties started ...");
        Diet diet = new Diet();
        User creator = userRepository.findUserByUsername(username);
        diet.setCreatorUser(creator);
        diet.setDietName(dietName);
        diet.setDescription(dietDescription);
        diet.setDuration(duration);
        log.debug("DietConfigurator-copyProperties: ... translate dailyMealSetDTO to dailySet for diet ...");
        dailySetDTOMap.forEach((key, dailyMealSetDTO) -> {
            DailySet dailySet = dailyMealSetDTO.copyProperties(dailyMealSetDTO, userRepository, username, mealRepository);
            dailySet.getDiets().add(diet);
            dailySetRepository.save(dailySet);
            diet.getDailySet().add(dailySet);
        });
        log.debug("DietConfigurator-copyProperties: ... copyProperties finished");
        return diet;
    }
}
