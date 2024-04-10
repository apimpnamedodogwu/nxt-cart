package services.cart;

import data.dto.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartService {

    Cart getCart();

    double calculateTotal(Cart cart);
}
