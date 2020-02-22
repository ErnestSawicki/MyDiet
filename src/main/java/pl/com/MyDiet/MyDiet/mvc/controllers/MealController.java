package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.services.IngredientService;
import pl.com.MyDiet.MyDiet.services.MealService;

import java.security.Principal;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/createMeal")
public class MealController {

    private final MealService mealService;
   private final IngredientRepository ingredientRepository;
   private final MealTypeRepository mealTypeRepository;


    @Autowired
    public MealController(MealService mealService, IngredientRepository ingredientRepository, MealTypeRepository mealTypeRepository) {
        this.mealService = mealService;
        this.ingredientRepository = ingredientRepository;
        this.mealTypeRepository = mealTypeRepository;
    }


    @GetMapping
    public String getCreateMealPage(Model model) {
        model.addAttribute("availableIngredients", ingredientRepository.findAll());//mealService.getAllIngredients());
        model.addAttribute("mealCreateDTO", new MealCreateDTO());
        List<MealType> mealTypes = mealTypeRepository.findAll();
        model.addAttribute("mealTypes", mealTypes);
        return "createMeal";
    }

    @PostMapping(params = {"add"})
    public String addIngredient(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                                Model model) {
        mealCreateDTO = mealService.rebuildFormWhenAddIngredient(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredients(mealCreateDTO));
        return "createMeal";
    }

    @PostMapping(params = {"ingredientToRemove"})
    public String rebuildFormWhenDelete(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                                        Model model) {
        mealCreateDTO = mealService.rebuildFormWhenDeletedIngredient(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredients(mealCreateDTO));
        return "createMeal";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                          Model model,
                          Principal principal) {
        if (mealService.saveIngredient(mealCreateDTO, principal.getName())) {
            return "home-page";
        } else {
            model.addAttribute("availableIngredients", mealService.getIngredients(mealCreateDTO));
            return "createMeal";
        }
    }
}
