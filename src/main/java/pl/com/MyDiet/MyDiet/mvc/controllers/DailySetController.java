package pl.com.MyDiet.MyDiet.mvc.controllers;


import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.com.MyDiet.MyDiet.DTO.DailyMealSetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleDailySetDTO;
import pl.com.MyDiet.MyDiet.DTO.SimpleMealsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.beans.SecurityUtils;
import pl.com.MyDiet.MyDiet.services.DailySetService;
import pl.com.MyDiet.MyDiet.services.UserService;

import java.util.List;


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

        List<SimpleDailySetDTO> allDailySetDTOToDisplay = dailySetService.getAllDailySetDTOToDisplay();

        model.addAttribute("createdDailySet" , allDailySetDTOToDisplay);
        return "daily-set-display";
    }

    @GetMapping("/my-daily-set")
    public String displayAllUserCreatedDailySet(Model model) {
        model.addAttribute("createdDailySet", dailySetService.getUserDailySetDTOToDisplay(userService.getUserDetails(SecurityUtils.getUsername())));
        return "daily-set-display";
    }


    //      Modify Daily Set Method        ///


    @GetMapping("/modify/{id:[1-9][0-9]*}/{operation:delete|modify}")
    public String prepareModifyDailySetPage(@PathVariable("id") Long dailySetId, @PathVariable("operation") String operation, Model model) {
        //  if (dailySetService.checkUserIsDailySetOwner(dailySetId, userService.getUserDetails(SecurityUtils.getUsername()))) {
        if (operation.equals("delete")) {
            DailyMealSetDTO dailySetToDelete = dailySetService.getDailySetAsDailyMealSetDTO(dailySetId);
            dailySetToDelete.setId(dailySetId);
            model.addAttribute("dailySetDTO", dailySetToDelete);
            return "confirm-delete-daily-Set";
        } else {
            DailyMealSetDTO dailySetToModify = dailySetService.getDailySetAsDailyMealSetDTO(dailySetId);
            dailySetToModify.setId(dailySetId);
            model.addAttribute("dailySetDTO", dailySetToModify);
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetToModify.getMealAmount()));
            return "daily-set-modify";
        }
    }


    @PostMapping("/modify/delete")
    public String deleteDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model) {
        //todo
        return "daily-set-modify";
    }


    @PostMapping("/modify")
    public String modifyDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model) {
        dailySetService.reloadPageWithSetVariable(dailySetDTO);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "daily-set-modify";
    }


    @PostMapping(path = "/modify", params = {"filter"})
    public String modifyDailySetPagesReload(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                            Model model) {
        model.addAttribute("redirected");

        model.addAttribute("dietDay");

        dailySetDTO = dailySetService.reloadPageWithSetVariable(dailySetDTO);
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page with amount {} , meal size {}", dailySetDTO.getMealAmount(), dailySetDTO.getMeals().size());
        return "daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"modifyExistingMealList"})
    public String modifyMealListWhenSetIsModify(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model,
                                                @RequestParam(defaultValue = "false") Boolean redirected,
                                                @RequestParam(required = false) Integer dietDay) {

        dailySetService.reloadPageWithSetVariable(dailySetDTO);
        dailySetDTO.setMealPicked(false);
        log.debug("DailySetController-modifyClearList: user try modify his meal pick list with amount {}", dailySetDTO.getMealAmount());
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"resetAll"})
    public String clearModifyForm(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO) {
        dailySetDTO.setCaloriesPicked(null);
        log.debug("DailySetController-clearForm: user cleared form -redirect to createDailySet");
        return "daily-set-modify";
    }

    @PostMapping(path = "/modify", params = {"approvalModification"})
    public String processModify(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                Model model) {
        if (dailySetService.modify(dailySetDTO, SecurityUtils.getUsername())) {
            return "home-page";
        } else {
            model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
            return "daily-set-display";
        }
    }


    //      Create Daily Set Method        ///


    @GetMapping("/create")
    public String createDailySetPages(Model model) {
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(3L));
        model.addAttribute("dailySetDTO", new DailyMealSetDTO());
        return "dailySetCreate";
    }


    @PostMapping(path = "/create", params = {"filter"})
    public String createDailySetPages(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO,
                                      Model model,
                                      @RequestParam(defaultValue = "false") Boolean redirected,
                                      @RequestParam(required = false) Integer dietDay) {

        if (redirected.equals("false")) {
            model.addAttribute("redirected", false);
        }
        model.addAttribute("redirected", redirected);
        if (dietDay != null) {
            model.addAttribute("dietDay", dietDay);
        }
        log.info("DailySetController redirect to page with amount {}", dailySetDTO.getMealAmount());
        dailySetDTO = dailySetService.reloadPageWithSetVariable(dailySetDTO);
        dailySetDTO.getMeals().stream().forEach(p-> log.debug("createDailySetPages id {} name {}", p.getId(), p.getName()));
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        log.info("DailySetController redirect to page with amount {} , meal size {}", dailySetDTO.getMealAmount(), dailySetDTO.getMeals().size());
        return "dailySetCreate";
    }

    @PostMapping(path = "/create", params = {"modifyMealList"})
    public String modifyMealList(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailySetDTO, Model model,
                                 @RequestParam(defaultValue = "false") Boolean redirected,
                                 @RequestParam(required = false) Integer dietDay) {
        if (redirected.equals("false")) {
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
        dailySetDTO.getMeals().stream().forEach(p-> log.debug("id {} name {}", p.getId(), p.getName()));
        model.addAttribute("availableMeats", dailySetService.getAvailableMeats(dailySetDTO.getMealAmount()));
        return "dailySetCreate";
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
            return "dailySetCreate";
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
        return "dailySetCreate";
    }

    @PostMapping(path = "/create", params = {"createdForDiet"})
    public String createDailySetForDiet(@ModelAttribute("dailySetDTO") DailyMealSetDTO dailyMealSetDTO,
                                        @RequestParam Integer dietDay) {
        log.debug("DailySetController-createdForDiet: dietDay:{}", dietDay);
        log.debug("DailySetController-createdForDiet: dietConfigurationDailySetMap:{}", dietConfigurator.getDailySetDTOMap());
        dietConfigurator.getDailySetDTOMap().put(dietDay, dailyMealSetDTO);
        return "redirect:/createDiet";
    }
}
