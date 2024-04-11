package nxt.cart;

import nxt.cart.data.dto.Cart;
import nxt.cart.CartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nxt.cart.services.cart.CartService;
import nxt.cart.services.coupon.CouponService;
import nxt.cart.services.exceptions.CouponRuleException;

@SpringBootTest(classes = CartApplication.class)
public class CouponTest {

    @Autowired
    CartService cartService;

    @Autowired
    CouponService couponService;

    @Test
    public void testThatCartListCanBeGotten() {
        Cart cart = cartService.getCart();
        Assertions.assertEquals(3, cart.getItems().size());
        System.out.println(cart);
    }



}
