package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.MyDiet.MyDiet.DTO.UserRegistrationDTO;
import pl.com.MyDiet.MyDiet.data.model.Sex;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("/userRegistration")
public class UserRegistrationController  {

    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getUserRegistrationPage(){
        return "/WEB-INF/views/userRegistration-page.jsp";
    }

    @PostMapping
    public String registerUser(@Valid UserRegistrationDTO userDTO){
        User registeredUser = new User();
        registeredUser.setActive(true);
        registeredUser.setRole("USER");
        BeanUtils.copyProperties(userDTO, registeredUser);
        registeredUser.setBirthDate(LocalDate.parse(userDTO.getBirthDate()));
        registeredUser.setSex(Sex.valueOf(userDTO.getSex()));
        userRepository.save(registeredUser);
        return "redirect:/";
    }
}
