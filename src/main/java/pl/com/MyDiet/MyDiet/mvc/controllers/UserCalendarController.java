package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.MyDiet.MyDiet.data.model.calendar.CalendarProperties;
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
    public String getUserCalendar(Model model, @RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year){
        CalendarProperties calendarProperties = new CalendarProperties();
        if (month == null){
            month = LocalDate.now().getMonthValue();
        }
        if (year == null){
            year = LocalDate.now().getYear();
        }
        calendarProperties.setCalendarPropertiesForMonth(year, month);
        model.addAttribute("viewMonth", month);
        model.addAttribute("viewYear", year);
        model.addAttribute("calendarProperties", calendarProperties);
        return "calendar/my-calendar";
    }

    @GetMapping("/dayDetails")
    public String chooseDay(){
        return "calendar/chooseDay";
    }

    @GetMapping("/myCalendar/monthChange")
    public String changeMonth(Model model,
                              @RequestParam String monthChange,
                              @RequestParam Integer viewMonth,
                              @RequestParam Integer viewYear){
        if (monthChange.equals("prevMonth")){
            viewMonth--;
        }
        if (monthChange.equals("nextMonth")){
            viewMonth++;
        }
        CalendarProperties calendarProperties = new CalendarProperties();
        calendarProperties.setCalendarPropertiesForMonth(viewYear, viewMonth);
        model.addAttribute("viewMonth", viewMonth);
        model.addAttribute("viewYear", viewYear);
        model.addAttribute("calendarProperties", calendarProperties);
        return "calendar/my-calendar";
    }

    @PostMapping("/dayDetails")
    public String getDayDetails(Model model, @RequestParam String date){
        log.debug("UserCalendarController-dayDetails: date={}", date);
        LocalDate localDate = LocalDate.parse(date);
        model.addAttribute("date", localDate);
        model.addAttribute("dailySetForDate", userCalendarService.getDailySetForSpecificDay(localDate));
        return "calendar/day-details";
    }

}
