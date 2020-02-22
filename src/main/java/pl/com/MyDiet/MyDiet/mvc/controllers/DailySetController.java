package pl.com.MyDiet.MyDiet.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.services.DailySetService;

import java.security.Principal;


@Controller
@RequestMapping("/createDailySet")
public class DailySetController {

    private final DailySetService dailySetService;

    @Autowired
    public DailySetController( DailySetService dailySetService) {
        this.dailySetService = dailySetService;
    }

    @GetMapping
    public String createDailySetPages(Model model) {
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());
        return "dailySetCreate";
    }

    @PostMapping(params={"filter"})
    public String createDailySetPages(@ModelAttribute ("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model){
        dailySetDTO= dailySetService.reloadPageWithSetVariable(dailySetDTO);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySetCreate";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("mealCreateDTO") DailyMealSetDTO dailySetDTO, Principal principal, Model model){
        if (dailySetService.save(dailySetDTO, principal.getName())) {
            return "home-page";
        } else {
            model.addAttribute("availableIngredients", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "dailySetCreate";
        }
    }

}
