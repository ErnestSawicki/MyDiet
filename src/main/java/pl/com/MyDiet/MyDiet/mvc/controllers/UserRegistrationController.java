package pl.com.MyDiet.MyDiet.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.MyDiet.MyDiet.DTO.UserRegistrationDTO;
import pl.com.MyDiet.MyDiet.beans.SecurityUtils;

import pl.com.MyDiet.MyDiet.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;


@Controller
@Slf4j
@RequestMapping("/userRegistration")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserRegistrationPage(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "user/user-register";
    }

    @PostMapping
    public String registerUser(@Valid UserRegistrationDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/user-register";
        if (userService.usernameIsTaken(userDTO.getUsername())) {
            bindingResult.rejectValue("username",null,"This user name is already taken");
            userService.registerUser(userDTO);
            return "user/user-register";
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword",null, "Password and  confirm password must be the same");
           return "user/user-register";
        }
        return "redirect:/";
    }

    @GetMapping("/modifyProfile")
    public String getModifyProfilePage(Model model, Principal principal) {
        model.addAttribute("userData", userService.getUserDetails(SecurityUtils.getUsername()));
        return "user/modifyUserProfile";
    }

    @PostMapping("/modifyProfile")
    public String modifyProfile(@Valid UserRegistrationDTO userDTO, BindingResult bindingResult) {
        if (userService.usernameIsTaken(userDTO.getUsername())) {

            bindingResult.rejectValue("username",null,"This user name is already taken");
            userService.registerUser(userDTO);
            return "user/user-register";
        }
        if (userDTO.getPassword()!=null &&!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword",null, "Password and  confirm password must be the same");
            return "user/user-register";
        }
        if (userDTO.getBirthDate()!=null && userDTO.getBirthDate().isBefore(LocalDate.now())) {
            bindingResult.rejectValue("birthDate",null, "Wrong birthday date. You should was born in past");
            return "user/user-register";
        }


        userService.updateProfile(userDTO);
        return "redirect:/userRegistration/modifyProfile";
    }
}
