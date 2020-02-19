package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.services.IngredientCategoryService;
import pl.com.MyDiet.MyDiet.services.IngredientService;


@Controller
@Slf4j
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientCategoryService ingredientCategoryService;

    @Autowired
    public IngredientController(IngredientService ingredientService, IngredientCategoryService ingredientCategoryService) {
        this.ingredientService = ingredientService;
        this.ingredientCategoryService = ingredientCategoryService;
    }

    @GetMapping
    public String addIngredientPages(Model model) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        model.addAttribute("ingredientDTO", ingredientDTO);
        model.addAttribute("availableCategory", ingredientCategoryService.getAllIngredientCategoryDTO());
        return "addIngredient";
    }

    @PostMapping(params = {"add"})
    public String rebuildFormWhenAdd(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenAddCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "addIngredient";
    }

    @PostMapping(params = {"categoryToRemove"})
    public String rebuildFormWhenDelete(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenDeletedCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "addIngredient";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        boolean save = ingredientService.saveIngredient(ingredientDTO);
        System.out.println(save);
        if (save){
            return "index";
        }
        else {
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
            return "addIngredient";}
    }


}
