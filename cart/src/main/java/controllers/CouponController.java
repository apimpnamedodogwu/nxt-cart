package controllers;

import data.dto.Cart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.coupon.CouponService;
import services.exceptions.CouponRuleException;

@RestController
@RequestMapping("api/v1/nxt-cart")
@Slf4j
@AllArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupon")
    public double getCouponPrice(@RequestParam String couponCode) throws CouponRuleException {
        Cart cart = couponService.applyCoupon(couponCode);
        return cart.getDiscountedTotal();
    }
}
