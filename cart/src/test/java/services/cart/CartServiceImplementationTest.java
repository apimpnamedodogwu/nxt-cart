package services.cart;

import data.dto.Cart;
import nxt.cart.CartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import services.coupon.CouponService;
import services.exceptions.CouponRuleException;

@SpringBootTest(classes = CartApplication.class)
class CartServiceImplementationTest {

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

    @Test
    public void testThatCouponCanBeApplied() throws CouponRuleException {
        Cart cart = couponService.applyCoupon("FIXED1O");
        Assertions.assertTrue(cart.getTotal() > 50);
        Assertions.assertEquals(140, cart.getDiscount(), "That $10 of $150 is $140");
        Assertions.assertTrue(cart.getItems().size() > 1);
        System.out.println(cart);
    }

}