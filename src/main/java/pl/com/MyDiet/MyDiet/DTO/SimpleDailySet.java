package pl.com.MyDiet.MyDiet.DTO;

import pl.com.MyDiet.MyDiet.data.model.MealTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleDailySet {


    private LocalDate chosenDayForThisSet;


    private Long mealAmount;


    private Long calories;


    private List<MealTime> mealTime = new ArrayList<>();
}
