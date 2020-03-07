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
        return "ingredient/addIngredient";
    }

    @PostMapping(params = {"add"})
    public String rebuildFormWhenAdd(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenAddCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "ingredient/addIngredient";
    }

    @PostMapping(params = {"categoryToRemove"})
    public String rebuildFormWhenDelete(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenDeletedCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "ingredient/addIngredient";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        boolean save = ingredientService.saveIngredient(ingredientDTO);
        System.out.println(save);
        if (save){
            return "home-page";
        }
        else {
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
            return "ingredient/addIngredient";}
    }

    @PostMapping(params = {"addNewCategory"})
    public String addCategory(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model){
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        model.addAttribute("categoryToAdd", true);

         return "ingredient/addIngredient";
    }

    @PostMapping(params = {"addCategory"})
    public String saveCategory(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO,String categoryName, Model model){
        boolean save = ingredientCategoryService.save(ingredientDTO, categoryName);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        if (!save) {
            model.addAttribute("categoryToAdd", true);
            model.addAttribute("categoryName", categoryName);
        }
        return "ingredient/addIngredient";


    }

}
