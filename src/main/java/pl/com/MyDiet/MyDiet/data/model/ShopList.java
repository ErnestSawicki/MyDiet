package pl.com.MyDiet.MyDiet.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shop_lists")
@Getter @Setter
public class ShopList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_day", nullable = false)
    private LocalDate shopDay;

//    @Column(name = "product_to_buy")
//    private

    @Column(name = "ovner", nullable = false)
    private Long owner;

}
