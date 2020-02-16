package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Data
    public class IngredientDTO {
        private String ingredientName;
        private Long caloriesPer100g;
        private Long categoryToAdd;
        private List<IngredientCategory> addedIngredientCategories =new ArrayList<>();
    }

    private final IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    public IngredientController(IngredientCategoryRepository ingredientCategoryRepository) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
    }

    @GetMapping
    public String addIngredientPages(Model model) {
        model.addAttribute("ingredientCategories", ingredientCategoryRepository.findAll());
        model.addAttribute("ingredientDTO", new IngredientDTO());
        return "addIngredient";
    }

    @PostMapping(params = {"add"})
    public String rebuildForm(IngredientDTO ingredientDTO, Model model) {
        List<IngredientCategory> addedIngredientCategories = ingredientDTO.addedIngredientCategories;
        System.out.println(addedIngredientCategories);

        IngredientCategory ingredientCategory = (ingredientCategoryRepository.findById(ingredientDTO.categoryToAdd).orElse(null));
        addedIngredientCategories.add(ingredientCategory);
        ingredientDTO.setAddedIngredientCategories(addedIngredientCategories);
        model.addAttribute("ingredientCategories", ingredientCategoryRepository.findAll());
        model.addAttribute("ingredientDTO", ingredientDTO);
        return "addIngredient";
    }

    @PostMapping(params = {"send"})
    public String process(IngredientDTO ingredientDTO, Model model){
        return "";
    }

}
