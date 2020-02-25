package pl.com.MyDiet.MyDiet.services;

import pl.com.MyDiet.MyDiet.DTO.UserRegistrationDTO;
import pl.com.MyDiet.MyDiet.data.model.User;

public interface UserService {

    boolean registerUser(UserRegistrationDTO userRegistrationDTO);

    User getUserDetails(String username);

    boolean updateProfile(UserRegistrationDTO userRegistrationDTO);
}
