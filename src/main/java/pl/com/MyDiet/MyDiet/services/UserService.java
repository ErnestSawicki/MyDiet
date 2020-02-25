package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.UserRegistrationDTO;

public interface UserService {

    boolean registerUser(UserRegistrationDTO userRegistrationDTO);
}
