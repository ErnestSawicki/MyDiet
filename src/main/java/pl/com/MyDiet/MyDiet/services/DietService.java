package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.DietDetailsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;

public interface DietService {

    boolean save(DietConfigurator dietConfigurator, String username);

    DietDetailsDTO getDietDetails(Long dietId);

}
