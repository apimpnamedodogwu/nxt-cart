package nxt.cart.data.models;

import nxt.cart.data.models.enums.RuleType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table
@Data
public class Rule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RuleType type;

    @Column(name = "rule_value")
    private double value;

    @ManyToOne
    private Coupon coupon;
}
