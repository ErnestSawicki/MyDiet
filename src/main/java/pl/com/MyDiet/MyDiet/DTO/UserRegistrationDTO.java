package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private Integer height;

    private Integer weight;

    private String birthDate;

    private String sex;

    private String email;

}
