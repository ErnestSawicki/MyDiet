package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.MyDiet.MyDiet.data.model.MealType;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.repositories.MealTypeRepository;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

public class MealTypeInitializer {

    public void createSampleUsers(MealTypeRepository mealTypeRepository){
        createMealType(MealTypeEnumeration.BREAKFAST, mealTypeRepository);
        createMealType(MealTypeEnumeration.SECOND_BREAKFAST, mealTypeRepository);
        createMealType(MealTypeEnumeration.DINNER, mealTypeRepository);
        createMealType(MealTypeEnumeration.TEA, mealTypeRepository);
        createMealType(MealTypeEnumeration.SUPPER, mealTypeRepository);
    }

    private void createMealType(MealTypeEnumeration enumeration, MealTypeRepository mealTypeRepository){
        MealType mealType = new MealType();
        mealType.setMealTypeName(enumeration);
        mealTypeRepository.save(mealType);
    }
}
