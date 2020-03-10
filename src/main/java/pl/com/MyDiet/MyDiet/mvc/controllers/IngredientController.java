package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.services.IngredientCategoryService;
import pl.com.MyDiet.MyDiet.services.IngredientService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
        return "ingredient/create-ingredient";
    }

    @PostMapping(params = {"back"})
    public String rebuildForm(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        model.addAttribute("categoryToAddBoolean", false);
        model.addAttribute("availableCategory", ingredientCategoryService.getAllIngredientCategoryDTO());
        return "ingredient/create-ingredient";
    }

    @PostMapping(params = {"add"})
    public String rebuildFormWhenAdd(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenAddCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "ingredient/create-ingredient";
    }

    @PostMapping(params = {"categoryToRemove"})
    public String rebuildFormWhenDelete(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        ingredientDTO = ingredientService.rebuildFormWhenDeletedCategory(ingredientDTO);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "ingredient/create-ingredient";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("ingredientDTO") @Valid IngredientDTO ingredientDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
            return "ingredient/create-ingredient";
        }

        if (!ingredientService.ingredientNameIsAvailable(ingredientDTO.getIngredientName())) {
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
            bindingResult.rejectValue("name", null, "This ingredient  already exist");
            return "ingredient/create-ingredient";
        }

        if (ingredientService.saveIngredient(ingredientDTO)) {
            return "home-page";
        } else {
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));

            return "ingredient/create-ingredient";
        }
    }

    @PostMapping(params = {"addNewCategory"})
    public String addCategory(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO, Model model) {
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        model.addAttribute("categoryToAddBoolean", true);
        model.addAttribute("ingredientCategoryDTO", new IngredientCategoryDTO());

        return "ingredient/create-ingredient";
    }

    @PostMapping(params = {"addCategory"})
    public String saveCategory(@ModelAttribute("ingredientDTO") IngredientDTO ingredientDTO,
                               @Valid IngredientCategoryDTO ingredientCategoryDTO,
                               BindingResult bindingResult, Model model) {
        model.addAttribute("categoryToAddBoolean", true);
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientCategoryDTO", ingredientCategoryDTO);
            return "ingredient/create-ingredient";
        }

        if (!ingredientCategoryService.ingredientCategoryNameIsAvailable(ingredientCategoryDTO.getName())) {
            bindingResult.rejectValue("name", null, "This ingredient category already exist");
            model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
            model.addAttribute("ingredientCategoryDTO", ingredientCategoryDTO);
            return "ingredient/create-ingredient";
        }

        if (ingredientCategoryService.save(ingredientDTO, ingredientCategoryDTO.getName())) {
            model.addAttribute("categoryToAddBoolean", false);
            ingredientDTO =ingredientService.rebuildFormWhenCreateNewCategory(ingredientDTO, ingredientCategoryDTO);
        } else {
            model.addAttribute("ingredientCategoryDTO", ingredientCategoryDTO);
        }
        model.addAttribute("availableCategory", ingredientService.getIngredientCategories(ingredientDTO));
        return "ingredient/create-ingredient";


    }

}
