package pl.com.MyDiet.MyDiet.mvc.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.beans.SecurityUtils;
import pl.com.MyDiet.MyDiet.services.DailySetService;
import pl.com.MyDiet.MyDiet.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.file.AccessDeniedException;


@Controller
@RequestMapping("/daily-set")
@Slf4j
public class DailySetController {
    private final DailySetService dailySetService;
    private final DietConfigurator dietConfigurator;
    private final UserService userService;

    @Autowired
    public DailySetController(DailySetService dailySetService, DietConfigurator dietConfigurator, UserService userService) {
        this.dailySetService = dailySetService;
        this.dietConfigurator = dietConfigurator;
        this.userService = userService;
    }


    //      Display Daily Set Method        ///

    @GetMapping()
    public String displayAllCreatedDailySet(Model model) {
        model.addAttribute("loggedUser", SecurityUtils.getUsername());
        model.addAttribute("createdDailySet", dailySetService.getAllDailySetDTOToDisplay());
        return "dailySet/daily-set-display";
    }

    @GetMapping("/my-daily-set")
    public String displayAllUserCreatedDailySet(Model model) {
        model.addAttribute("createdDailySet", dailySetService.getUserDailySetDTOToDisplay(userService.getUserDetails(SecurityUtils.getUsername())));
        return "dailySet/daily-set-display";
    }


    //      Modify Daily Set Method        ///

    @GetMapping("/modify/{id:[1-9][0-9]*}/{operation:delete|modify}")
    public String prepareModifyDailySetPage(@PathVariable("id") Long dailySetId, @PathVariable("operation") String operation, Model model, HttpServletResponse response) throws IOException {
        if (!dailySetService.checkUserIsDailySetOwner(dailySetId, SecurityUtils.getUsername())) {
            throw new AccessDeniedException("403 returned");
        }
        DailyMealSetDTO dailySetToModify = dailySetService.getDailySetAsDailyMealSetDTO(dailySetId);
        if (operation.equals("delete")) {
            model.addAttribute("dailySetDTO", dailySetService.getDailySetAsDailyMealSetDTO(dailySetId));
            return "dailySet/daily-set-delete-confirm";
        } else {
            model.addAttribute("dailySetDTO", dailySetToModify);
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetToModify.getMealAmount()));
            return "dailySet/daily-set-modify";
        }
    }


    @PostMapping("/modify/delete")
    public String deleteDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO) {
        //todo
        return "redirect:/daily-set";
    }


    @PostMapping("/modify")
    public String modifyDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model) {
        dailySetDTO.setUpValuesCaloriesAndMealListQueue();
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySet/daily-set-modify";
    }


    @PostMapping(path = "/modify", params = {"filter"})
    public String modifyDailySetPagesReload(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                            Model model) {
        model.addAttribute("redirected");

        model.addAttribute("dietDay");

        dailySetDTO.setUpValuesCaloriesAndMealListQueue();
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page with amount {} , meal size {}", dailySetDTO.getMealAmount(), dailySetDTO.getMeals().size());
        return "dailySet/daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"modifyExistingMealList"})
    public String modifyMealListWhenSetIsModify(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model,
                                                @RequestParam(defaultValue = "false") Boolean redirected,
                                                @RequestParam(required = false) Integer dietDay) {

        dailySetDTO.setUpValuesCaloriesAndMealListQueue();
        dailySetDTO.setMealPicked(false);
        log.debug("DailySetController-modifyClearList: user try modify his meal pick list with amount {}", dailySetDTO.getMealAmount());
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySet/daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"resetAll"})
    public String clearModifyForm(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO) {
        dailySetDTO.setCaloriesPicked(null);
        log.debug("DailySetController-clearForm: user cleared form -redirect to createDailySet");
        return "dailySet/daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"approvalModification"})
    public String processModify(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                Model model) {
        if (dailySetService.modify(dailySetDTO, SecurityUtils.getUsername())) {
            return "home-page";
        } else {
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "dailySet/daily-set-display";
        }
    }


    //      Create Daily Set Method        ///


    @GetMapping("/create")
    public String createDailySetPages(Model model) {
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());
        return "dailySet/daily-set-create";
    }


    @PostMapping(path = "/create", params = {"filter"})
    public String createDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                      Model model,
                                      @RequestParam(defaultValue = "false") Boolean redirected,
                                      @RequestParam(required = false) Integer dietDay) {

        if (!redirected) {
            model.addAttribute("redirected", false);
        }
        model.addAttribute("redirected", redirected);
        if (dietDay != null) {
            model.addAttribute("dietDay", dietDay);
        }
        log.info("DailySetController redirect to page with amount {}", dailySetDTO.getMealAmount());
        dailySetDTO.setUpValuesCaloriesAndMealListQueue();

        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page with amount {} , meal size {}", dailySetDTO.getMealAmount(), dailySetDTO.getMeals().size());
        return "dailySet/daily-set-create";
    }

    @PostMapping(path = "/create", params = {"modifyMealList"})
    public String modifyMealList(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model,
                                 @RequestParam(defaultValue = "false") Boolean redirected,
                                 @RequestParam(required = false) Integer dietDay) {
        if (!redirected) {
            model.addAttribute("redirected", false);
        }
        model.addAttribute("redirected", redirected);
        if (dietDay != null) {
            model.addAttribute("dietDay", dietDay);
        }
        log.info("DailySetController redirect to page with amount {}", dailySetDTO.getMealAmount());
        dailySetDTO.setMealPicked(false);

        dailySetDTO.setUpValuesCaloriesAndMealListQueue();
        log.debug("DailySetController-modifyClearList: user try modify his meal pick list with amount {}", dailySetDTO.getMealAmount());
        dailySetDTO.getMeals().forEach(p -> log.debug("id {} name {}", p.getId(), p.getName()));
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySet/daily-set-create";
    }

    @PostMapping(path = "/create", params = {"clear"})
    public String clearForm() {
        log.debug("DailySetController-clearForm: user cleared form -redirect to createDailySet");
        return "redirect:/daily-set/create";
    }

    @PostMapping(path = "/create", params = {"send"})
    public String process(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                          Model model) {
        if (dailySetService.save(dailySetDTO, SecurityUtils.getUsername())) {
            return "home-page";
        } else {
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "dailySet/daily-set-create";
        }
    }


    //      Create Daily Set Method from Diet Creation      //

    @GetMapping("/create//forDiet")
    public String getDailySetForDiet(Model model, @RequestParam Integer dietDay) {
        log.debug("DailySetController-createdForDiet: dietDay:{}", dietDay);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());

        model.addAttribute("redirected", true);
        model.addAttribute("dietDay", dietDay);
        return "dailySet/daily-set-create";
    }

    @PostMapping(path = "/create", params = {"createdForDiet"})
    public String createDailySetForDiet(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailyMealSetDTO,
                                        @RequestParam Integer dietDay) {
        log.debug("DailySetController-createdForDiet: dietDay:{}", dietDay);
        log.debug("DailySetController-createdForDiet: dietConfigurationDailySetMap:{}", dietConfigurator.getDailySetDTOMap());
        dietConfigurator.getDailySetDTOMap().put(dietDay, dailyMealSetDTO);
        return "redirect:/diet/createDiet";
    }
}
