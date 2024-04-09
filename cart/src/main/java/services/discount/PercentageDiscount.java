package services.discount;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PercentageDiscount implements DiscountService {

    private double percentage;

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice - (totalPrice * percentage / 100);
    }

}

