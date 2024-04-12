package nxt.cart.services.cart;

import nxt.cart.data.dto.Cart;
import nxt.cart.data.dto.Item;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImplementation implements CartService{


    @Override
    public Cart getCart() {
        Cart cart = new Cart();
        cart.setItems(defaultValues);
        double total = calculateTotal(cart);
        cart.setTotal(total);
        return cart;
    }

    @Override
    public double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    private final List<Item> defaultValues = Arrays.asList(
            new Item("Product 1", 20.0),
            new Item("Product 2", 50.0),
            new Item("Product 3", 80.0),
            new Item("Product 4", 200.0),
            new Item("Product 5", 1000.0)
    );

}
