package nxt.cart.data.models;

import nxt.cart.data.models.enums.Coupons;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Coupons couponCode;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Rule> rules;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Discount> discounts;
}
