package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User ownerId;

    @Column(name = "reciple")
    private String recipe;

    @Column(name = "preparation_time")
    private Long preparationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
