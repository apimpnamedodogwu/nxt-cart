package services.cart;

import data.dto.Cart;

public interface CartService {

    Cart getCart();

    double calculateTotal(Cart cart);
}
