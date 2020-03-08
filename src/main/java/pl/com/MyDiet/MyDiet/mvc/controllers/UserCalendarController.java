package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.MyDiet.MyDiet.services.UserCalendarService;

import java.time.LocalDate;

@Controller
@RequestMapping("/calendar")
@Slf4j
public class UserCalendarController {

    private final UserCalendarService userCalendarService;

    @Autowired
    public UserCalendarController(UserCalendarService userCalendarService) {
        this.userCalendarService = userCalendarService;
    }

    @GetMapping("/myCalendar")
    public String getUserCalendar(){
        return "calendar/mycalendar";
    }

    @GetMapping("/dayDetails")
    public String chooseDay(){
        return "calendar/chooseDay";
    }

    @PostMapping("/dayDetails")
    public String getDayDetails(Model model, @RequestParam String date){
        LocalDate localDate = LocalDate.parse(date);
        model.addAttribute("date", localDate);
        model.addAttribute("dailySetForDate", userCalendarService.getDailySetForSpecificDay(localDate));
        return "calendar/day-details";
    }

}
