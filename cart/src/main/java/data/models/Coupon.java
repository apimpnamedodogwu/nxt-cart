package data.models;

import data.models.enums.Coupons;
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

    @OneToMany
    private List<Rule> rules;

    @OneToMany
    private List<Discount> discounts;
}
