package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.com.MyDiet.MyDiet.DTO.MealCreateDTO;
import pl.com.MyDiet.MyDiet.data.model.Meal;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;
import pl.com.MyDiet.MyDiet.data.repositories.FileEntityRepository;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.services.MealService;


import java.io.IOException;
import java.security.Principal;
import java.util.*;


@Controller
@Slf4j
@RequestMapping("/createMeal")
public class MealController {

    private final MealService mealService;
    private final IngredientRepository ingredientRepository;
    private final MealTypeRepository mealTypeRepository;
    private final FileEntityRepository fileEntityRepository;


    @Autowired
    public MealController(MealService mealService, IngredientRepository ingredientRepository, MealTypeRepository mealTypeRepository, FileEntityRepository fileEntityRepository) {
        this.mealService = mealService;
        this.ingredientRepository = ingredientRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.fileEntityRepository = fileEntityRepository;
    }


    @GetMapping
    public String getCreateMealPage(Model model) {
        MealCreateDTO mealCreateDTO = new MealCreateDTO();
        model.addAttribute("availableIngredients", ingredientRepository.findAll()); //mealService.getAllIngredients());
        model.addAttribute("mealCreateDTO", mealCreateDTO);
        model.addAttribute("mealTypes", mealTypeRepository.findAll());
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        return "meal/createMeal";
    }

    @PostMapping(params = {"addIngredient"})
    public String addIngredient(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                                Model model) {
        mealCreateDTO = mealService.rebuildFormWhenAddIngredient(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
        model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        return "meal/createMeal";
    }

    @PostMapping(params = {"ingredientToRemove"})
    public String removeIngredient(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                                   Model model) {
        mealCreateDTO = mealService.rebuildFormWhenDeletedIngredient(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
        model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        return "meal/createMeal";
    }

    @PostMapping(params = {"addMealType"})
    public String addMealType(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                              Model model) {
        mealCreateDTO = mealService.rebuildFormWhenAddMealType(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
        model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        return "meal/createMeal";
    }

    @PostMapping(params = {"mealTypeToRemove"})
    public String removeMealType(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                                 Model model) {
        mealCreateDTO = mealService.rebuildFormWhenDeletedMealType(mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
        model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        return "meal/createMeal";
    }

    @PostMapping(params = {"send"})
    public String process(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO,
                          Model model,
                          Principal principal) {
        log.debug("MealController-saveMeal: mealCreateDTO is like: {}", mealCreateDTO.toString());
        if (mealService.saveMeal(mealCreateDTO, principal.getName())) {
            return "home-page";
        } else {
            model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
            model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
            return "meal/createMeal";
        }
    }

    @PostMapping(params = {"upload"})
    public String uploadProfileFile(@ModelAttribute("mealCreateDTO") MealCreateDTO mealCreateDTO, @RequestParam MultipartFile file, Model model) throws IOException {
        log.debug("MealController-uploadFile: Meal picture upload started ...");
        FileEntity mealFileEntity = new FileEntity();
        mealFileEntity.setContentType(file.getContentType());
        mealFileEntity.setFileName(file.getOriginalFilename());
        mealFileEntity.setData(file.getBytes());
        fileEntityRepository.save(mealFileEntity);
        log.debug("MealController-uploadFile: mealFileEntity-status: {}", mealFileEntity.toString());
        mealCreateDTO.setMealFile(mealFileEntity);
        log.debug("MealController-uploadFile: mealCreateDTO-status: {}", mealCreateDTO.toString());
        model.addAttribute("mealCreateDTO", mealCreateDTO);
        model.addAttribute("availableIngredients", mealService.getIngredientsDTO(mealCreateDTO));
        model.addAttribute("mealTypes", mealService.getMealType(mealCreateDTO));
        model.addAttribute("hasMealPicture", hasMealPicture(mealCreateDTO));
        log.debug("MealController-uploadFile: mealCreateDTO-inModel: {}", model.getAttribute("mealCreateDTO").toString());
        log.debug("MealController-uploadFile: ...upload finished");

        return "meal/createMeal";
    }

    @GetMapping("/meal-file")
    public ResponseEntity<Resource> getMealPicture(@RequestParam Long mealFileId){
        log.debug("MealController-mealFile: Input mealFileId: {}", mealFileId);
        FileEntity mealFile = fileEntityRepository.findAllById(mealFileId).get(0);
        log.debug("MealController-mealFile: Download of meal picture started: ...");
        if (mealFile != null){
            log.debug("MealController-mealFile: ... file returned");
            return buildMealFileResponse(mealFile);
        } else {
            log.debug("MealController-mealFile: ... fine NOT returned - no file exist for meal");
            return buildNoMealFileResponse();
        }
    }

    @GetMapping("/modifyMeal")
    public String getModifyMealPage(Model model, @RequestParam Long mealId){
        if (mealService.getMealById(mealId).getMealFile() != null) {
            model.addAttribute("hasMealPicture", true);
        } else {
            model.addAttribute("hasMealPicture", false);
        }
        model.addAttribute("mealToModify", mealService.getMealById(mealId));
        return "meal/modifyMeal";
    }

    @PostMapping("/modifyMeal")
    public String modifyMeal(){
        return "redirect:/createMeal/modifyMeal";
    }

    @GetMapping("/viewMeal")
    public String getViewMealPage(Model model, @RequestParam Long mealId){
        if (mealService.getMealById(mealId).getMealFile() != null) {
            model.addAttribute("hasMealPicture", true);
        } else {
            model.addAttribute("hasMealPicture", false);
        }
        Meal meal = mealService.getMealById(mealId);
        log.debug("MealController-viewMeal: meal={}", meal.toString());
        model.addAttribute("partsOfMeal", meal.getPartsOfMeal());
        Set<String> mealTypesNames = new HashSet<>();
        meal.getMealTypes().forEach( p -> {
            log.debug("MealController-viewMeal: typeNames as strings: {}", p.getMealTypeName());
            mealTypesNames.add(p.getMealTypeName().name());
        });
        model.addAttribute("mealTypes", mealTypesNames);
        log.debug("MealController-viewMeal: mealTypes are: {}", mealTypesNames);
        model.addAttribute("mealDetails", meal);
        return "meal/mealDetails";
    }

    @GetMapping(path = "/meals")
    public String getAllMeals(Model model){
        model.addAttribute("meals", mealService.getAllMeals());
        return "meal/meals";
    }

    private ResponseEntity<Resource> buildNoMealFileResponse() { return ResponseEntity.noContent().build();}

    private ResponseEntity<Resource> buildMealFileResponse(FileEntity mealFile){
        ByteArrayResource data = getMealFileData(mealFile);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(mealFile.getContentType()))
                .header("Content-Disposition", String.format("filename=%s", mealFile.getFileName())).body(data);
    }

    private ByteArrayResource getMealFileData(FileEntity mealFile){
        return new ByteArrayResource(mealFile.getData());
    }

    private boolean hasMealPicture(MealCreateDTO mealCreateDTO){
        return mealCreateDTO.getMealFile() != null;
    }

    private boolean isValidProfileFile(FileEntity mealPicture){
        if (mealPicture.getContentType() == null) return false;
        if (mealPicture.getFileName() == null || mealPicture.getFileName().isEmpty()) return false;
        return mealPicture.getData() != null;
    }
}
