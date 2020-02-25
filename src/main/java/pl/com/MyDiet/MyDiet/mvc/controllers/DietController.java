package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.DietDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.model.MealTime;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.DietRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/createDiet")
@Transactional
public class DietController {

    private final DailySetRepository dailySetRepository;
    private final DietRepository dietRepository;
    private final UserRepository userRepository;
    private final DietConfigurator dietConfigurator;
    private final MealRepository mealRepository;

    @Autowired
    public DietController(DailySetRepository dailySetRepository, DietRepository dietRepository, UserRepository userRepository, DietConfigurator dietConfigurator, MealRepository mealRepository) {
        this.dailySetRepository = dailySetRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.dietConfigurator = dietConfigurator;
        this.mealRepository = mealRepository;
    }

    @GetMapping
    public String getDietPage(Model model) {
        model.addAttribute("dietConfigurator", dietConfigurator);
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());
        return "/diet-create";
    }

    @PostMapping(params = {"filter"})
    public String getDietPageWithFilter(Model model, DietDTO dietDTO) {

        dietConfigurator.setDietName(dietDTO.getDietName());
        dietConfigurator.setDietDescription(dietDTO.getDescription());
        dietConfigurator.setDuration(dietDTO.getDuration());
        model.addAttribute("dietConfigurator", dietConfigurator);
        for (Integer i = 0; i < dietDTO.getDuration(); i++) {
            DailyMealSetDTO dailyMealSetDTO = new DailyMealSetDTO();
            dietConfigurator.getDailySetDTOMap().put(i , dailyMealSetDTO);
        }
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());

        return "/diet-create";
    }

    @PostMapping(params = {"createDailySet"})
    public String getCreateDailySet() {
        return "redirect:/createDailySet/forDiet";
    }

    @PostMapping(params = {"findDailySet"})
    public String findDailySet(){
        return "redirect:/createDailySet";
    }


    @PostMapping(params = {"create"})
    public String createDiet(Principal principal) {
        log.debug("DietController-create: Start to create diet ...");
        Diet diet = new Diet();
        User creator = userRepository.findUserByUsername(principal.getName());
        diet.setCreatorUser(creator);
        diet.setDietName(dietConfigurator.getDietName());
        diet.setDescription(dietConfigurator.getDietDescription());
        diet.setDuration(dietConfigurator.getDuration());
        log.debug("DietController-create: ... translate dailyMealSetDTO to dailySet for diet ...");
        dietConfigurator.getDailySetDTOMap().forEach((key, dailyMealSetDTO) -> {
            DailySet dailySet = new DailySet();
            dailySet.setCalories(dailyMealSetDTO.getCalories());
            dailySet.setCreatorUser(creator);
            dailySet.setMealAmount(dailyMealSetDTO.getMealAmount());
            List<MealTime> mealTimes = dailyMealSetDTO.getMeals().stream().map(p -> {
                MealTime mealTime = new MealTime();
                mealTime.setDailySet(dailySet);
                mealTime.setMeal(mealRepository.getOne(p.getId()));
                mealTime.setMealTypeName(p.getMealType());
                return mealTime;
            }).collect(Collectors.toList());
            dailySet.setMealTime(mealTimes);
            log.debug("DietController-create: ... dailySetIs = {}", dailySet);
            dailySet.getDiets().add(diet);
            dailySetRepository.save(dailySet);
            diet.getDailySet().add(dailySet);
        });
        dietRepository.save(diet);
        log.debug("DietController-create: ... diet created");
        return "redirect:/";
    }
}
