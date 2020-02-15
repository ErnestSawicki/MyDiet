package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shop_lists")
@Getter @Setter
public class ShopList extends EntityBase{

    @Column(name = "shop_day", nullable = false)
    private LocalDate shopDay;

//    @Column(name = "product_to_buy")
//    private

    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;

}
