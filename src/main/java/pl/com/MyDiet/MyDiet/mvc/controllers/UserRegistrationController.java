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


@Controller
@Slf4j
@RequestMapping("/userRegistration")
public class UserRegistrationController  {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserRegistrationPage(Model model){
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "user-register";
    }

    @PostMapping
    public String registerUser(@Valid UserRegistrationDTO userDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "user-register";
        userService.registerUser(userDTO);
        return "redirect:/";
    }

    @GetMapping("/modifyProfile")
    public String getModifyProfilePage(Model model, Principal principal){
        model.addAttribute("userData", userService.getUserDetails(SecurityUtils.getUsername()));
        return "/modifyUserProfile";
    }

    @PostMapping("/modifyProfile")
    public String modifyProfile(UserRegistrationDTO userDTO){
        userService.updateProfile(userDTO);
        return "redirect:/userRegistration/modifyProfile";
    }
}
