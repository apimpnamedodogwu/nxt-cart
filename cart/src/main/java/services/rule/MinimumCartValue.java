package services.rule;

import data.dto.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MinimumCartValue implements Rule{

    private double minimumValue;

    @Override
    public boolean ruleIsValid(Cart cart) {
        return cart.getTotal() >= minimumValue;
    }
}
