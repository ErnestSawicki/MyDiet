package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.MyDiet.MyDiet.data.model.Sex;
import pl.com.MyDiet.MyDiet.data.model.User;
import pl.com.MyDiet.MyDiet.data.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;

@Slf4j
public class UserInitializer {


    public void createSampleUsers(UserRepository userRepository, PasswordEncoder passwordEncoder){
        log.info("UserInitializer: User sample data started ...");
        Faker faker = new Faker();

        for(int i =0; i < 20; i++){

            User user = new User();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String username = lastName + firstName.charAt(0);

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setSex(Sex.values()[new Random().nextInt(2)]);
            user.setEmail(faker.internet().emailAddress());
            user.setBirthDate(randomDateOfBirth());
            user.setRole("USER");
            user.setActive(true);
            user.setPassword(passwordEncoder.encode("pass"));

            userRepository.save(user);
        }
        log.info("UserInitializer: ... users created");
    }

    private LocalDate randomDateOfBirth(){
        LocalDate start = LocalDate.of(1950, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        return start.plusDays(new Random().nextInt((int) days + 1));
    }
}
