package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
@Slf4j
@Getter @Setter
public class User extends EntityBase {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "role")
    private String role;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Meal> ownedMeals = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<ShopList> shopLists;
}
