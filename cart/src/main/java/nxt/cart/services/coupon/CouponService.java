package nxt.cart.services.coupon;

import lombok.AllArgsConstructor;
import nxt.cart.data.dto.Cart;
import nxt.cart.data.dto.CouponResponse;
import nxt.cart.data.models.Coupon;
import nxt.cart.data.models.Discount;
import nxt.cart.data.models.Rule;
import nxt.cart.data.models.enums.Coupons;
import nxt.cart.data.repositories.CouponRepository;
import nxt.cart.services.cart.CartService;
import nxt.cart.services.exceptions.CouponNotFoundException;
import nxt.cart.services.exceptions.CouponRuleException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CouponService {

    private final CartService cartService;
    private final CouponRepository couponRepository;


    public CouponResponse applyCoupon(String couponCode) throws CouponRuleException, CouponNotFoundException {

        if (!isValidCouponCode(couponCode)) {
            throw new CouponNotFoundException("Invalid Coupon Code: " + couponCode);
        }

        Optional<Coupon> coupon = couponRepository.findByCouponCode(Coupons.valueOf(couponCode));
        Cart cart = cartService.getCart();
        CouponResponse couponResponse = new CouponResponse();

        if (coupon.isPresent()) {
            for (Rule rule : coupon.get().getRules()) {
                switch (rule.getType()) {
                    case MIN_CART_ITEMS -> {
                        if (cart.getItems().size() < rule.getValue()) {
                            couponResponse.setTotalAdjustedPrice(cart.getTotal());
                            couponResponse.setDiscountedAmount(cart.getDiscount());
                            throw new CouponRuleException("Minimum cart items rule not met for this coupon!");
                        }
                    }

                    case MIN_CART_VALUE -> {
                        if (cart.getTotal() < rule.getValue()) {
                            couponResponse.setTotalAdjustedPrice(cart.getTotal());
                            couponResponse.setDiscountedAmount(cart.getDiscount());
                            throw new CouponRuleException("Minimum cart value not met for this coupon!");
                        }
                    }
                }
            }

            for (Discount discount: coupon.get().getDiscounts()) {
                double calculatedDiscount = calculateDiscount(discount);
                couponResponse.setDiscountedAmount(cart.getTotal() - calculatedDiscount);
                cart.setTotal(calculatedDiscount);
                couponResponse.setTotalAdjustedPrice(cart.getTotal());
            }

        }

        return couponResponse;

    }

    private double calculateDiscount(Discount discount) {
        return switch (discount.getDiscountType()) {
            case FIXED -> calculateFixedDiscount(discount);
            case PERCENTAGE -> calculatePercentageDiscount(discount);
            case GREATER -> calculateGreaterDiscount(discount);
        };
    }

    private double calculateFixedDiscount(Discount discount) {
        return cartService.getCart().getTotal() - discount.getFixedAmount();
    }

    private double calculateGreaterDiscount(Discount discount) {
        double percentageDiscount = cartService.getCart().getTotal() * (discount.getPercentage() / 100);
        double fixedDiscount = cartService.getCart().getTotal() - discount.getFixedAmount();
        return Math.max(percentageDiscount, fixedDiscount);
    }

    private double calculatePercentageDiscount(Discount discount) {
        return cartService.getCart().getTotal() - (cartService.getCart().getTotal() * discount.getPercentage() / 100);
    }

    private boolean isValidCouponCode(String couponCode) {
        for (Coupons value : Coupons.values()) {
            if (value.name().equals(couponCode)) {
                return true;
            }
        }
        return false;
    }

}