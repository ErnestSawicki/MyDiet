package pl.com.MyDiet.MyDiet.data.model.calendar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter @ToString
public class CalendarProperties {
    private Integer firstDayOfMonth;
    private Map<Integer, String> daysOfMonth = new HashMap<>();

    public void setCalendarPropertiesForMonth(Integer year, Integer month){
        LocalDate chosenMonth =  LocalDate.of(year, month, 1);
        this.firstDayOfMonth = chosenMonth.getDayOfWeek().getValue();
        if (daysOfMonth.isEmpty()){
            for(int i = 1; i <= chosenMonth.lengthOfMonth(); i++){
                daysOfMonth.put(chosenMonth.getDayOfMonth(), chosenMonth.toString());
                chosenMonth = chosenMonth.plusDays(1L);
            }
        }
    }
}
