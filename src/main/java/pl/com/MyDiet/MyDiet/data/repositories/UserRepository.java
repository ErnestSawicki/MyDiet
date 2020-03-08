package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
}
