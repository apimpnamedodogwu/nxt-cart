package services.discount;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GreaterDiscount implements DiscountService {

    private double percentage;
    private double fixedAmount;

    @Override
    public double calculateDiscount(double totalPrice) {
        double percentageDiscount = totalPrice * (percentage / 100);
        double fixedDiscount = totalPrice - fixedAmount;
        return Math.max(percentageDiscount, fixedDiscount);
    }
}
