package nxt.cart.data.models;

import nxt.cart.data.models.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table
@Data
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private double value;

//    @ManyToOne
//    private Coupon coupon;

    private double percentage;

    private double fixedAmount;
}
