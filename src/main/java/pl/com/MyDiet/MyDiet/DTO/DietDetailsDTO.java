package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.List;

@Getter @Setter @ToString
public class DietDetailsDTO {

    private String dietName;
    private String description;
    private Long duration;
    private User creatorUser;
    private List<SimpleDailySetDTO> dailySets;
}
