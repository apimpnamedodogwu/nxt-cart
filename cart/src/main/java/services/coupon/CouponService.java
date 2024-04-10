package services.coupon;

import data.dto.Cart;
import data.models.Coupon;
import data.models.Discount;
import data.models.Rule;
import data.repositories.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import services.cart.CartService;
import services.discount.FixedAmount;
import services.discount.GreaterDiscount;
import services.discount.PercentageDiscount;
import services.exceptions.CouponRuleException;
import services.rule.MinimumCartItemsRule;
import services.rule.MinimumCartValueRule;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CouponService {

    private final CartService cartService;
    private final CouponRepository couponRepository;

    private final FixedAmount fixedAmountDiscountService;
    private final PercentageDiscount percentageDiscountService;
    private final GreaterDiscount greaterDiscountService;


    public Cart applyCoupon(String couponCode) throws CouponRuleException {

        Optional<Coupon> coupon = couponRepository.findByCouponCode(String.valueOf(couponCode));
        Cart cart = cartService.getCart();

        if (coupon.isPresent()) {
            for (Rule rule : coupon.get().getRules()) {
                switch (rule.getType()) {
                    case MIN_CART_ITEMS -> {
                        if (!new MinimumCartValueRule((int) rule.getValue()).ruleIsValid(cart)) {
                            throw new CouponRuleException("Coupon rules not met");
                        }
                    }

                    case MIN_CART_VALUE -> {
                        if (!new MinimumCartItemsRule((int) rule.getValue()).ruleIsValid(cart)) {
                            throw new CouponRuleException("Coupon rules not met");
                        }
                    }
//                    default -> throw new IllegalArgumentException("Blah");
                }
            }

            Discount discount = coupon.get().getDiscounts().get(0);
            double total = cartService.calculateTotal(cart);
            double calculatedDiscount = calculateDiscount(discount, total);
            cart.setDiscount(calculatedDiscount);
            cart.setDiscountedTotal(total - calculatedDiscount);
        }

        return cart;

    }

    private double calculateDiscount(Discount discount, double total) {
        return switch (discount.getDiscountType()) {
            case FIXED -> fixedAmountDiscountService.calculateDiscount(total);
            case PERCENTAGE -> percentageDiscountService.calculateDiscount(total);
            case GREATER -> greaterDiscountService.calculateDiscount(total);
        };
    }

}