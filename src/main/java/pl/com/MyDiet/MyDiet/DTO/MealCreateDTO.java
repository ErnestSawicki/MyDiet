package pl.com.MyDiet.MyDiet.DTO;

import pl.com.MyDiet.MyDiet.data.model.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MealCreateDTO {

    private Long calories;
    private String name;
    private User owner;
    private String recipe;
    private Long preparationTime;
}
