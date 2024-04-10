package services.rule;

import data.dto.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MinimumCartItemsRule implements Rule{

    private int minimumNumberOfItems;

    @Override
    public boolean ruleIsValid(Cart cart) {
        return cart.getItems().size() >= minimumNumberOfItems;
    }
}
