package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;
import pl.com.MyDiet.MyDiet.config.passwordConfig.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserRegistrationDTO {


    private String username;

    private String firstName;

    private String lastName;

    @ValidPassword
    private String password;

    private String confirmPassword;

    private Integer height;

    private Integer weight;

    private String birthDate;

    private String sex;

    @Email
    private String email;

}
