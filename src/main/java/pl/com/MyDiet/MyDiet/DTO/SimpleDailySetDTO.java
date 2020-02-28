package pl.com.MyDiet.MyDiet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.MealTypeEnumeration;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleDailySetDTO {

    private Long id;
    private LocalDate chosenDayForThisSet;
    private Long creatorUserId;
    private String creatorUserName;
    private Long mealAmount;
    private Long calories;
    private List<MealForDailyDTO> mealForDailyDTO = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MealForDailyDTO {
        private Long id;

        private Long calories;

        private String name;

        private String recipe;

        private Long preparationTime;

        private String creatorUser;

        private FileEntity mealFile;

        private Long mealFileId;
        private MealTypeEnumeration mealTypeEnumeration;


    }
}
