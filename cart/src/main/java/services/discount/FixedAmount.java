package services.discount;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FixedAmount implements DiscountService {

    private double fixedAmount;

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice - fixedAmount;
    }
}
