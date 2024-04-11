package nxt.cart.services.cart;

import nxt.cart.data.dto.Cart;

import java.util.Map;


public interface CartService {

    double calculateTotal(Cart cart);

    Cart getCart();
}
