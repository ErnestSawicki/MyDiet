package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Meal")
@Getter @Setter
public class Meal extends EntityBase {

    @Column(name = "calories")
    private Long calories;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "reciple")
    private String recipe;

    @Column(name = "preparation_time")
    private Long preparationTime;


}
