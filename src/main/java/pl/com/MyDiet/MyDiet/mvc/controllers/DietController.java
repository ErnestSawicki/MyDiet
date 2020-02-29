package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.MyDiet.MyDiet.DTO.DietDTO;
import pl.com.MyDiet.MyDiet.DTO.DietDetailsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.repositories.DietRepository;
import pl.com.MyDiet.MyDiet.services.DietService;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/createDiet")
@Transactional
public class DietController {


    private final DietConfigurator dietConfigurator;
    private final DietService dietService;

    @Autowired
    public DietController(DietConfigurator dietConfigurator, DietService dietService) {
        this.dietConfigurator = dietConfigurator;
        this.dietService = dietService;
    }

    @GetMapping
    public String getDietPage(Model model) {
        model.addAttribute("dietConfigurator", dietConfigurator);
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());
        return "/diet-create";
    }

    @PostMapping(params = {"filter"})
    public String getDietPageWithFilter(Model model, DietDTO dietDTO) {
        dietDTO.copyPropertiesDietDTO(dietConfigurator);
        model.addAttribute("dietConfigurator", dietConfigurator);
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());
        return "/diet-create";
    }

    @PostMapping(params = {"createDailySet"})
    public String getCreateDailySet() {
        return "redirect:/createDailySet/forDiet";
    }

    @PostMapping(params = {"findDailySet"})
    public String findDailySet() {
        return "redirect:/createDailySet";
    }

    @PostMapping(params = {"create"})
    public String createDiet(Principal principal) {
        log.debug("DietController-create: Start to create diet ...");
        if (dietService.save(dietConfigurator, principal.getName())) {
            log.debug("DietController-create: ... diet created");
            return "redirect:/";
        }
        log.debug("DietController-create: ... diet NOT created");
        return "redirect:/createDiet";
    }

    @GetMapping("/dietDetails")
    public String dietDetailsPage(@RequestParam Long dietId, Model model) {

        DietDetailsDTO dietDetails = dietService.getDietDetails(dietId);
        model.addAttribute("dietDetails", dietDetails);

        return "diet-Details";
    }



    @GetMapping("/assignDiet")
    public String assignDietPage() {
        return "diet-assign";
    }

    @PostMapping("/assignDiet")
    public String assignDiet(){

        return "redirect:/";
    }
}
