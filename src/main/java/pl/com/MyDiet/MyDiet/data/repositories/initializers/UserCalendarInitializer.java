package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.UserCalendar;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserCalendarRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class UserCalendarInitializer {

    public void createCalendarSampleForUsers(UserCalendarRepository userCalendarRepository,
                                             UserRepository userRepository,
                                             DailySetRepository dailySetRepository){

        for (int i = 0; i < 50; i++){
            UserCalendar userCalendar = new UserCalendar();
            userCalendar.setUser(userRepository.findAll().get(new Random().nextInt(10)));
            userCalendar.setDailySet(dailySetRepository.findAll().get(new Random().nextInt(10)));
            userCalendar.setDate(randomDate());
            userCalendarRepository.save(userCalendar);
        }

    }

    private LocalDate randomDate(){
        LocalDate start = LocalDate.of(2020, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        return start.plusDays(new Random().nextInt((int) days + 1));
    }
}
