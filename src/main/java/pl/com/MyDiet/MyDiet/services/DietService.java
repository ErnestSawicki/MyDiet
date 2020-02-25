package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.beans.DietConfigurator;

public interface DietService {

    boolean save(DietConfigurator dietConfigurator, String username);

}
