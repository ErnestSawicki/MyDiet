package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/createDiet")
public class DietController {

    private DailySetRepository dailySetRepository;

    @Autowired
    public DietController(DailySetRepository dailySetRepository) {
        this.dailySetRepository = dailySetRepository;
    }

    @GetMapping
    public String getDietPage(){
        return "/diet-create";
    }

    @PostMapping(params = {"filter"})
    public String getDietPageWithFilter(Model model, @RequestParam Integer duration){

        List<DailySet> dailySets = new ArrayList<>();

        for(int i =0; i < duration; i++){
            DailySet dailySet = new DailySet();
            dailySets.add(dailySet);
        }

        model.addAttribute("dailySets", dailySets);

        List<DailySet> dailySetInDB = dailySetRepository.findAll();
        log.debug("DietController: dailySets in DB, size={}, sets={}", dailySetInDB.size(), dailySetInDB);
        model.addAttribute("dailySetsInDB", dailySetInDB);

        return "/diet-create";
    }

    @PostMapping(params = {"createDailySet"})
    public String getCreateDailySet(){

        return "redirect:/createDailySet";
    }
}
