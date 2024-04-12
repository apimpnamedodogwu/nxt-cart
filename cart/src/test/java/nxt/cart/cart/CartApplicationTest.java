package nxt.cart.cart;

import nxt.cart.CartApplication;
import nxt.cart.data.dto.Cart;
import nxt.cart.data.dto.CouponResponse;
import nxt.cart.data.dto.Item;
import nxt.cart.services.cart.CartService;
import nxt.cart.services.coupon.CouponService;
import nxt.cart.services.exceptions.CouponNotFoundException;
import nxt.cart.services.exceptions.CouponRuleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = CartApplication.class)
class CartApplicationTest {

    @Autowired
    CartService cartService;

    @Autowired
    CouponService couponService;


    @Test
    public void testThatCartListCanBeGotten() {
        Cart cart = cartService.getCart();
        Assertions.assertEquals(5, cart.getItems().size());
        System.out.println(cart);
    }

    @Test
    public void testThatCouponFIXED10CanBeApplied() throws CouponRuleException, CouponNotFoundException {
        Cart cart = cartService.getCart();
        CouponResponse appliedCoupon = couponService.applyCoupon("FIXED10");
        Assertions.assertTrue(cart.getTotal() > 50);
        Assertions.assertTrue(cart.getItems().size() > 1);
        Assertions.assertEquals(1340, appliedCoupon.getTotalAdjustedPrice());
        Assertions.assertEquals(10, appliedCoupon.getDiscountedAmount());
    }

    @Test
    public void testThatCouponPERCENT10CanBeApplied() throws CouponRuleException, CouponNotFoundException {
        Cart cart = cartService.getCart();
        CouponResponse appliedCoupon = couponService.applyCoupon("PERCENT10");
        Assertions.assertTrue(cart.getTotal() > 100);
        Assertions.assertTrue(cart.getItems().size() > 2);
        Assertions.assertEquals(1215, appliedCoupon.getTotalAdjustedPrice());
        Assertions.assertEquals(135, appliedCoupon.getDiscountedAmount());
    }

    @Test
    public void testThatCouponMIXED10CanBeApplied() throws CouponRuleException, CouponNotFoundException {
        Cart cart = cartService.getCart();
        CouponResponse appliedCoupon = couponService.applyCoupon("MIXED10");
        Assertions.assertTrue(cart.getTotal() > 200);
        Assertions.assertTrue(cart.getItems().size() > 3);
        Assertions.assertEquals(1340, appliedCoupon.getTotalAdjustedPrice());
        Assertions.assertEquals(10, appliedCoupon.getDiscountedAmount());
    }

    @Test
    public void testThatCouponREJECTED10CanBeApplied() throws CouponRuleException, CouponNotFoundException {
        Cart cart = cartService.getCart();
        CouponResponse appliedCoupon = couponService.applyCoupon("REJECTED10");
        Assertions.assertTrue(cart.getTotal() > 1000);
        Assertions.assertEquals(1215, appliedCoupon.getTotalAdjustedPrice());
        Assertions.assertEquals(135, appliedCoupon.getDiscountedAmount());
    }

    @Test
    public void testThatCouponNotFoundExceptionIsThrown() {
        Cart cart = new Cart();
        List<Item> items = new ArrayList<>();
        Item itemOne = new Item("CheckOne", 10);
        Item itemTwo = new Item("CheckTwo", 15);
        items.add(itemOne);
        items.add(itemTwo);
        cart.setItems(items);
        double total = cartService.calculateTotal(cart);
        cart.setTotal(total);
        assertThrows(CouponNotFoundException.class, () -> couponService.applyCoupon("FIXE0"));
    }

}