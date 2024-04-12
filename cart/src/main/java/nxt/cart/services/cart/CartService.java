package nxt.cart.services.cart;

import nxt.cart.data.dto.Cart;


public interface CartService {

    double calculateTotal(Cart cart);

    Cart getCart();
}
