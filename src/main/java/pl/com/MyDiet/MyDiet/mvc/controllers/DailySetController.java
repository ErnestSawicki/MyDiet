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

    private final DietConfigurator dietConfigurator;

    @Autowired
    public DailySetController(DailySetService dailySetService, DietConfigurator dietConfigurator) {
        this.dailySetService = dailySetService;
        this.dietConfigurator = dietConfigurator;
    }

    @GetMapping
    public String createDailySetPages(Model model) {
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());
        return "dailySetCreate";
    }


    @PostMapping(params = {"filter"})
    public String createDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                      Model model,
                                      @RequestParam(defaultValue = "false") Boolean redirected,
                                      @RequestParam(required = false) Integer dietDay) {
        if (redirected.equals("false")) {
            model.addAttribute("redirected", false);
        }
        model.addAttribute("redirected", redirected);
        if (dietDay != null){
            model.addAttribute("dietDay", dietDay);
        }
        log.info("DailySetController redirect to page with amount {}" , dailySetDTO.getMealAmount());
        dailySetDTO = dailySetService.reloadPageWithSetVariable(dailySetDTO);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page with amount {}",  dailySetDTO.getMealAmount() );
        log.info("DailySetController redirect to page with meal size {}",  dailySetDTO.getMeals().size());
        return "dailySetCreate";
    }

    @PostMapping(params = {"modifyMealList"})
    public String modifyMealList(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model,
                                 @RequestParam(defaultValue = "false") Boolean redirected,
                                 @RequestParam(required = false) Integer dietDay) {
        if (redirected.equals("false")) {
            model.addAttribute("redirected", false);
        }
        model.addAttribute("redirected", redirected);
        if (dietDay != null){
            model.addAttribute("dietDay", dietDay);
        }
        dailySetDTO.setMealPicked(false);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySetCreate";
    }

        @PostMapping(params = {"clear"})
        public String clearForm() {
            return "redirect:/createDailySet";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                          Principal principal,
                          Model model) {
        if (dailySetService.save(dailySetDTO, principal.getName())) {
            return "home-page";
        } else {
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "dailySetCreate";
        }
    }

    @GetMapping("/forDiet")
    public String getDailySetForDiet(Model model, @RequestParam Integer dietDay) {
        log.debug("DailySetController-createdForDiet: dietDay:{}", dietDay);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());

        model.addAttribute("redirected", true);
        model.addAttribute("dietDay", dietDay);
        return "dailySetCreate";
    }

    @PostMapping(params = {"createdForDiet"})
    public String createDailySetForDiet(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailyMealSetDTO,
                                        @RequestParam Integer dietDay) {
        log.debug("DailySetController-createdForDiet: dietDay:{}", dietDay);
        log.debug("DailySetController-createdForDiet: dietConfigurationDailySetMap:{}", dietConfigurator.getDailySetDTOMap());
        dietConfigurator.getDailySetDTOMap().put(dietDay, dailyMealSetDTO);
        return "redirect:/createDiet";
    }
}
