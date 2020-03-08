package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.beans.SecurityUtils;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.UserCalendar;
import pl.com.MyDiet.MyDiet.data.repositories.UserCalendarRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.UserCalendarService;

import java.time.LocalDate;

@Service
@Slf4j
public class UserCalendarServiceDefault implements UserCalendarService {

    private final UserCalendarRepository userCalendarRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserCalendarServiceDefault(UserCalendarRepository userCalendarRepository,
                                      UserRepository userRepository) {
        this.userCalendarRepository = userCalendarRepository;
        this.userRepository = userRepository;
    }


    @Override
    public DailySet getDailySetForSpecificDay(LocalDate date) {
        log.debug("UserCalendarService-getDailySetForSpecificDay: process started ...");
        UserCalendar userCalendarForSpecificDay = userCalendarRepository.findByUserAndDate(userRepository.findUserByUsername(SecurityUtils.getUsername()), date);
        log.debug("UserCalendarService-getDailySetForSpecificDay: userCalendar is: {}", userCalendarForSpecificDay.toString());
        log.debug("UserCalendarService-getDailySetForSpecificDay: ... process finished");
        return userCalendarForSpecificDay.getDailySet();
    }
}
