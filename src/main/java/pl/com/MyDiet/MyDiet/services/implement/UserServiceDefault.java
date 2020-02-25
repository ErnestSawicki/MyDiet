package pl.com.MyDiet.MyDiet.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.DTO.UserRegistrationDTO;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;
import pl.com.MyDiet.MyDiet.services.UserService;

@Service
public class UserServiceDefault implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceDefault(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean registerUser(UserRegistrationDTO userRegistrationDTO) {
        User registrationUser = userRegistrationDTO.copyProperties();
        registrationUser.setActive(true);
        registrationUser.setRole("USER");
        registrationUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        userRepository.save(registrationUser);
        return false;
    }
}
