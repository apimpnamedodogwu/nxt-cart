package nxt.cart.controllers;

import nxt.cart.data.dto.Cart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nxt.cart.services.cart.CartService;

@RestController
@RequestMapping("api/v1/nxt-cart")
@Slf4j
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public Cart getCart() {
        return cartService.getCart();
    }
}
