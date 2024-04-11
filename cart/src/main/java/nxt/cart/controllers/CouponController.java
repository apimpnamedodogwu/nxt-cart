package nxt.cart.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nxt.cart.data.dto.CouponResponse;
import nxt.cart.services.coupon.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/nxt-cart")
@Slf4j
@AllArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupon")
    public CouponResponse getCouponPrice(@RequestParam String couponCode) {
        return couponService.applyCoupon(couponCode);
    }
}
