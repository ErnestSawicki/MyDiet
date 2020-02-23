package pl.com.MyDiet.MyDiet.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.com.MyDiet.MyDiet.data.model.DailySet;
import pl.com.MyDiet.MyDiet.data.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter @Setter
public class DietConfigurator {

    private String dietName;
    private String dietDescription;
    private Long duration;
    private List<DailySet> dailySetList = new ArrayList<>();

    public void clearDietConfiguration(){
        this.dietName = null;
        this.dietDescription = null;
        this.duration = null;
        this.dailySetList = Collections.emptyList();
    }
}
