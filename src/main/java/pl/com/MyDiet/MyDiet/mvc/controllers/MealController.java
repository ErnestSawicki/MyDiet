package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/createMeal")
public class MealController {

    @Data
    public class MealDTO{
        private String name;
        private String ingrToAdd;
        private Integer amount;
        private Map<String, Integer> addedIngredients = new HashMap<>();
    }

    private final IngredientRepository ingredientRepository;

    @Autowired
    public MealController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String getCreateMealPage(Model model){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("mealDTO", new MealDTO());
        return "createMeal";
    }

    @PostMapping(params = {"add"})
    public String addIngredient(MealDTO mealDTO, Model model){
        Map<String, Integer> addedIngredients = mealDTO.getAddedIngredients();
        addedIngredients.put(mealDTO.ingrToAdd, mealDTO.amount);
        model.addAttribute("addedIngredients", addedIngredients);
        return "createMeal";
    }

    @PostMapping(params = {"send"})
    public String createMeal(){
        return "redirect:/";
    }
}
