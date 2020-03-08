package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.data.model.DailySet;

import java.time.LocalDate;

public interface UserCalendarService {

    DailySet getDailySetForSpecificDay(LocalDate date);


}
