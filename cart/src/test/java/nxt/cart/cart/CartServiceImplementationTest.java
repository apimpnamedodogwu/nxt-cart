package nxt.cart.cart;

import junit.framework.Assert;
import nxt.cart.data.dto.Cart;
import nxt.cart.CartApplication;
import nxt.cart.services.cart.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nxt.cart.services.coupon.CouponService;
import nxt.cart.services.exceptions.CouponRuleException;

import static junit.framework.TestCase.assertTrue;

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
    public void testThatCouponCanBeApplied() {
        Cart cart = cartService.getCart();
        double appliedCoupon = couponService.applyCoupon("FIXED10");
        Assertions.assertTrue(cart.getTotal() > 50);
        Assertions.assertTrue(cart.getItems().size() > 1);
        Assert.assertEquals(appliedCoupon, 140);
//        Assertions.assertTrue(cart.getDiscount() == 140);

    }
}