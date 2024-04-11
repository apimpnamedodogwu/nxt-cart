package nxt.cart.data.dto;

import lombok.Data;

@Data
public class CouponResponse {

    private double totalAdjustedPrice;
    private double discountedAmount;
}
