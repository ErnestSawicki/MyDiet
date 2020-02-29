package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import com.github.javafaker.Faker;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.Diet;
import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
import pl.com.MyDiet.MyDiet.data.repositories.DietRepository;

import java.util.Random;

public class DietInitializer {

    public void createSampleDiets(DietRepository dietRepository,
                                  DailySetRepository dailySetRepository){
        Faker faker = new Faker();

        for (int i =0; i<10; i++){
            Diet diet = new Diet();
            diet.setDietName(faker.funnyName().name());
            diet.setDuration(3L);
            diet.setDescription(faker.harryPotter().spell());
            for(int j = 0; j <3; j++){
                DailySet dailySet = dailySetRepository.getOne(1L + (long) (Math.random() * (10L - 1L)));
                diet.getDailySet().add(dailySet);
                dailySet.getDiets().add(diet);
            }
            dietRepository.save(diet);
        }

    }
}
