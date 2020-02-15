package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Meal")
@Getter @Setter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "name")
    private String name;

    @Column(name = "ower_id")
    private Long ownerId;

    @Column(name = "reciple")
    private String recipe;

    @Column(name = "preparation_time")
    private Long preparationTime;


}
