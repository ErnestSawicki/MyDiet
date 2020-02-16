package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.MealIngredient;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealIngredientRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/createMeal")
public class MealController {

    @Data
    public class MealDTO{
        private Long ingredientId;
        private Integer ingredientAmount;
        private String mealDescription;
        private List<MealIngredient> mealIngredients = new ArrayList<>();
        private String mealName;
    }

    private final IngredientRepository ingredientRepository;
    private final MealIngredientRepository mealIngredientRepository;

    @Autowired
    public MealController(IngredientRepository ingredientRepository, MealIngredientRepository mealIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.mealIngredientRepository = mealIngredientRepository;
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

        MealIngredient mealIngredient = new MealIngredient();
        mealIngredient.setAmount(mealDTO.getIngredientAmount());
        mealIngredient.setIngredient(ingredientRepository.getOne(mealDTO.ingredientId));
        mealIngredientRepository.save(mealIngredient);

        mealDTO.mealIngredients.add(mealIngredient);

        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("mealDTO", mealDTO);
        return "createMeal";
    }

    @PostMapping(params = {"send"})
    public String createMeal(){
        return "redirect:/";
    }
}
