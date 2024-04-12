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

    @Column(name = "discount_value")
    private double value;

    private double percentage;

    private double fixedAmount;
}
