package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;

@Getter
@Setter
public class DietDTO {

    private String dietName;
    private String description;
    private Long duration;

    public void copyPropertiesDietDTO(DietConfigurator dietConfigurator){
        dietConfigurator.setDietName(dietName);
        dietConfigurator.setDietDescription(description);
        dietConfigurator.setDuration(duration);
        for (Integer i = 0; i < duration; i++) {
            DailyMealSetDTO dailyMealSetDTO = new DailyMealSetDTO();
            dietConfigurator.getDailySetDTOMap().put(i, dailyMealSetDTO);
        }
    }
}
