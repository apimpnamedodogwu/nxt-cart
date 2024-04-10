import data.dto.Cart;
import nxt.cart.CartApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import services.cart.CartService;
import services.coupon.CouponService;
import services.exceptions.CouponRuleException;

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

    @Test
    public void testThatCouponCanBeApplied() throws CouponRuleException {
        Cart cart1 = couponService.applyCoupon("FIXED1O");
        System.out.println(cart1);
    }

}
