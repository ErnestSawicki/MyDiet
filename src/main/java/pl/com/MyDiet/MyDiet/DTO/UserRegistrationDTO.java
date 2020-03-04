package pl.com.MyDiet.MyDiet.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import pl.com.MyDiet.MyDiet.config.passwordConfig.ValidPassword;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.model.enumeration.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Slf4j
public class UserRegistrationDTO {

@Size(min = 3, max = 20)
    private String username;

    @Size(min = 3, max = 20)
    private String firstName;

    @Size(min = 3, max = 20)
    private String lastName;

    @ValidPassword
    private String password;

    private String confirmPassword;

    private Integer height;

    private Double weight;

    private LocalDate birthDate;

    private String sex;

    @Email(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
    private String email;

    public User copyProperties(){
        User registeredUser = new User();
        registeredUser.setUsername(username);
        registeredUser.setFirstName(firstName);
        registeredUser.setLastName(lastName);
        log.debug("UserRegistrationDTO: birth date {}", birthDate);
        registeredUser.setBirthDate(birthDate);
        registeredUser.setSex(Sex.valueOf(sex));
        registeredUser.setPassword(password);

        return registeredUser;
    }

}
