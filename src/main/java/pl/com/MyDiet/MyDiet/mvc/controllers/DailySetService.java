package pl.com.MyDiet.MyDiet.mvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;

@Service
public class DailySetService {
    private final MealRepository mealRepository;

    @Autowired
    public DailySetService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }





}
