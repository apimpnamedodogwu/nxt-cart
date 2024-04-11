package nxt.cart.cart;

import nxt.cart.CartApplication;
import nxt.cart.data.dto.Cart;
import nxt.cart.data.dto.CouponResponse;
import nxt.cart.data.repositories.CouponRepository;
import nxt.cart.services.cart.CartService;
import nxt.cart.services.coupon.CouponService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        CouponResponse appliedCoupon = couponService.applyCoupon("FIXED10");
        Assertions.assertTrue(cart.getTotal() > 50);
        Assertions.assertTrue(cart.getItems().size() > 1);
        Assertions.assertEquals(140, appliedCoupon.getTotalAdjustedPrice());
//        Assertions.assertTrue(cart.getDiscount() == 140);

    }



}