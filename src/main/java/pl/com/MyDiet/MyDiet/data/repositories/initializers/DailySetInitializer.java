//package pl.com.MyDiet.MyDiet.data.repositories.initializers;
//
//import lombok.extern.slf4j.Slf4j;
//import pl.com.MyDiet.MyDiet.data.model.DailySet;
//import pl.com.MyDiet.MyDiet.data.repositories.DailySetRepository;
//import pl.com.MyDiet.MyDiet.data.repositories.MealRepository;
//import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
//
//import java.util.Random;
//
//@Slf4j
//public class DailySetInitializer {
//
//    public void createDailySetSamples(DailySetRepository dailySetRepository, MealRepository mealRepository, UserRepository userRepository){
//        for (int i = 0; i < 10; i++) {
//            DailySet dailySet = new DailySet();
//            dailySet.setMealAmount(3L);
//            dailySet.getSetMeals().add(mealRepository.findAll().get(new Random().nextInt(20)));
//            dailySet.getSetMeals().add(mealRepository.findAll().get(new Random().nextInt(20)));
//            dailySet.getSetMeals().add(mealRepository.findAll().get(new Random().nextInt(20)));
//            dailySet.setCreatorUser(userRepository.findAll().get(new Random().nextInt(8)));
//            dailySet.setCalories(3000L);
//            dailySetRepository.save(dailySet);
//        }
//    }
//}
