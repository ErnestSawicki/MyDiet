package pl.com.MyDiet.MyDiet.mvc.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.DailySetService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/createDailySet")
@Slf4j
public class DailySetController {

    private final DailySetService dailySetService;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DietConfigurator dietConfigurator;

    @Autowired
    public DailySetController(DailySetService dailySetService, MealRepository mealRepository, UserRepository userRepository, DietConfigurator dietConfigurator) {
        this.dailySetService = dailySetService;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.dietConfigurator = dietConfigurator;
    }

    @GetMapping
    public String createDailySetPages(Model model) {
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());
        return "dailySetCreate";
    }

    @PostMapping(params = {"filter"})
    public String createDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model) {
        dailySetDTO = dailySetService.reloadPageWithSetVariable(dailySetDTO);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page");
        return "dailySetCreate";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("mealCreateDTO") DailyMealSetDTO dailySetDTO,
                          Principal principal,
                          Model model) {
//        log.debug("DailySetController-@PostMapping(params=send): dailySetDTO ={}", dailySetDTO.toString());
//        log.debug("DailySetController-@PostMapping(params=send): breakfastId={}, secondBreakfastId={}, dinnerId={}, teaId={}, supperId={}",
//                breakfast, secondBreakfast, dinner, tea, supper);
//
//        SimpleMealsDTO simpleMealsDTO = new SimpleMealsDTO();
//        List<Meal> meals = new ArrayList<>();
//        if (breakfast != null) {
//            meals.add(mealRepository.getOne(breakfast));
//
//        }
//        if (secondBreakfast != null) {
//            meals.add(mealRepository.getOne(secondBreakfast));
//        }
//        if (dinner != null) {
//            meals.add(mealRepository.getOne(dinner));
//        }
//        if (tea != null) {
//            meals.add(mealRepository.getOne(tea));
//        }
//        if (supper != null) {
//            meals.add(mealRepository.getOne(supper));
//        }
//        meals.forEach(p ->{
//            SimpleMealsDTO simpleMealsDTO = new SimpleMealsDTO();
//            simpleMealsDTO.setId(p.getId());
//            simpleMealsDTO.setCalories(p.getCalories());
//            simpleMealsDTO.setName(p.getName());
//            simpleMealsDTO.setPreparationTime(p.getPreparationTime());
//            dailySetDTO.getListMeal().add(simpleMealsDTO);
//            p.setCreatorUser(userRepository.findUserByUsername(principal.getName()));
//        });
//        log.debug("DailySetController-@PostMapping(params=send): after simpleMealsDTO setUp dailySetDTO ={}", dailySetDTO.toString());
        if (dailySetService.save(dailySetDTO, principal.getName())) {
            return "home-page";
        } else {
            model.addAttribute("availableIngredients", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "dailySetCreate";
        }
    }

    @GetMapping("/forDiet")
    public String getDailySetForDiet(Model model){
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());

        model.addAttribute("redirected", true);
        return "dailySetCreate";
    }

    @PostMapping(params = {"createdForDiet"})
    public String createDailySetForDiet(){
        return "redirect:/createDiet";
    }
}
