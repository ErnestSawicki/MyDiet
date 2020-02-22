package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shop_lists")
@Getter
@Setter
public class ShopList extends EntityBase {

    @Column(name = "shop_day", nullable = false)
    private LocalDate shopDay;

    // Relation part//

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User owner;

    @OneToMany(mappedBy = "shopList")
    List<IngredientToBuy> ingredientsToBuy;
}
