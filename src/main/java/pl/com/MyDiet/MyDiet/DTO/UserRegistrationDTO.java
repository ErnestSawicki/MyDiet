package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.com.MyDiet.MyDiet.config.passwordConfig.ValidPassword;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

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

    public User copyProperties(){
        User registeredUser = new User();
        registeredUser.setUsername(username);
        registeredUser.setFirstName(firstName);
        registeredUser.setLastName(lastName);
        registeredUser.setBirthDate(LocalDate.parse(birthDate));
        registeredUser.setSex(Sex.valueOf(sex));
        registeredUser.setPassword(password);

        return registeredUser;
    }

}
