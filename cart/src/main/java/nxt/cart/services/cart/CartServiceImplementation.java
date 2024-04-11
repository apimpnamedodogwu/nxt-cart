package nxt.cart.services.cart;

import nxt.cart.data.dto.Cart;
import nxt.cart.data.dto.Item;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            new Item("Product 3", 80.0)
    );

    public Map<String, Double> getCartDetails() {
        Map<String, Double> details = new HashMap<>();
        details.put("Product1", 85.0);
        details.put("Product2", 15.0);
        details.put("Product3", 100.0);


        double total = details.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        details.put("Total", total);
        details.put("Discount", 0.0);
        return details;
    }
}
