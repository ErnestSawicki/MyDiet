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
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.services.DietService;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static pl.com.MyDiet.MyDiet.beans.SecurityUtils.getUsername;

@Controller
@Slf4j
@RequestMapping("/diet")
@Transactional
public class DietController {


    private final DietConfigurator dietConfigurator;
    private final DietService dietService;

    @Autowired
    public DietController(DietConfigurator dietConfigurator, DietService dietService) {
        this.dietConfigurator = dietConfigurator;
        this.dietService = dietService;
    }

    @GetMapping("/createDiet")
    public String getDietPage(Model model) {
        model.addAttribute("dietConfigurator", dietConfigurator);
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());
        return "diet/diet-create";
    }

    @PostMapping(path = "/createDiet", params = {"filter"})
    public String getDietPageWithFilter(Model model, DietDTO dietDTO) {
        dietDTO.copyPropertiesDietDTO(dietConfigurator);
        model.addAttribute("dietConfigurator", dietConfigurator);
        model.addAttribute("dailyMealSetsDTO", dietConfigurator.getDailySetDTOMap());
        return "diet/diet-create";
    }

    @PostMapping(params = {"createDailySet"})
    public String getCreateDailySet() {
        return "redirect:/createDailySet/forDiet";
    }

    @PostMapping(params = {"findDailySet"})
    public String findDailySet() {
        return "redirect:/createDailySet";
    }

    @PostMapping(path = "/createDiet" ,params = {"create"})
    public String createDiet(Principal principal) {
        log.debug("DietController-create: Start to create diet ...");
        if (dietService.save(dietConfigurator, principal.getName())) {
            log.debug("DietController-create: ... diet created");
            return "redirect:/";
        }
        log.debug("DietController-create: ... diet NOT created");
        return "redirect:/createDiet";
    }

    @GetMapping("/diets")
    public String dietDetailsPage(Model model) {
        List<Diet> diets = dietService.getAllDiets();
        model.addAttribute("diets", diets);
        return "diet/diet-allDiets";
    }

    @GetMapping("/dietDetails")
    public String dietDetailsPage(@RequestParam Long dietId, Model model) {
        DietDetailsDTO dietDetails = dietService.getDietDetails(dietId);
        model.addAttribute("dietDetails", dietDetails);
        return "diet/diet-Details";
    }

    @GetMapping("/assignDiet")
    public String assignDietPage(Model model) {
        model.addAttribute("diets", dietService.getAllDiets());
        return "diet/diet-assign";
    }

    @PostMapping("/assignDiet")
    public String assignDiet(@RequestParam String startDate, @RequestParam Long dietId) throws ParseException {
        log.debug("DietController-assignDiet: startDate: {}, dietId: {}", startDate, dietId);
        LocalDate date = LocalDate.parse(startDate);
        log.debug("DietController-assignDiet: dietAssignment started ...");
        dietService.assignUserDietFromDate(getUsername() ,date, dietId);
        log.debug("DietController-assignDiet: ... dietAssignment finished");
        return "redirect:/";
    }
}
