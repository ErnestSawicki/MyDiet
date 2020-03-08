package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.DietDetailsDTO;
import pl.com.MyDiet.MyDiet.beans.DietConfigurator;
import pl.com.MyDiet.MyDiet.data.model.Diet;

import java.time.LocalDate;
import java.util.List;

public interface DietService {

    boolean save(DietConfigurator dietConfigurator, String username);

    DietDetailsDTO getDietDetails(Long dietId);

    List<Diet> getAllDiets();

    void assignUserDietFromDate(String username, LocalDate startDate, Long dietId);

}
