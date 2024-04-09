package services.coupon;

import data.dto.Cart;
import data.models.Coupon;
import data.models.Discount;
import data.models.Rule;
import data.models.enums.Coupons;
import data.models.enums.DiscountType;
import data.repositories.CouponRepository;
import org.springframework.stereotype.Service;
import services.cart.CartService;
import services.discount.DiscountService;
import services.discount.FixedAmount;
import services.discount.GreaterDiscount;
import services.discount.PercentageDiscount;
import services.rule.MinimumCartItems;
import services.rule.MinimumCartValue;

import java.util.Optional;

@Service
public class CouponService {

    private final CartService cartService;
    private final DiscountService discountService;
    private final CouponRepository couponRepository;

    public CouponService(CartService cartService, DiscountService discountService, CouponRepository couponRepository) {
        this.cartService = cartService;
        this.discountService = discountService;
        this.couponRepository = couponRepository;
    }

    public Cart applyCoupon(String code) {
        Optional<Coupons> couponOptional = Optional.of(Coupons.valueOf(code));
        Coupons couponCode = couponOptional.get();
        Coupon coupon = couponRepository.findByCouponCode(String.valueOf(couponCode));
        Cart cart = cartService.getCart();

        for (Rule rule : coupon.getRules()) {
            switch (rule.getType()) {
                case MIN_CART_ITEMS -> {
                    if (!new MinimumCartValue(rule.getValue()).ruleIsValid(cart)) {
                        throw new IllegalArgumentException("Coupon rules not met");
                    }
                }
//                break;
                case MIN_CART_VALUE -> {
                    if (!new MinimumCartItems((int) rule.getValue()).ruleIsValid(cart)) {
                        throw new IllegalArgumentException("Coupon rules not met");
                    }
                }
//                break;

                default -> throw new IllegalArgumentException("Blah");
            }


        }

//        Discount discount = coupon.getDiscounts().get(0);
//        double total = cartService.calculateTotal(cart);
////        double discountAmount = discount.calculateDiscount(total);
//        cart.setDiscount(discountAmount);
//        cart.setDiscountedTotal(total - discountAmount);
//        return cart;
    }

//    private DiscountService getDiscountService(DiscountType discountType, Discount discount) {
//        switch (discountType) {
//            case FIXED:
//                return new FixedAmount(discount.getValue()); // Use discount value
//            case PERCENTAGE:
//                return new PercentageDiscount(discount.getValue()); // Use discount value
//            case GREATER:
//                return new GreaterDiscount(discount.getValue(), discount.);
//            default:
//                throw new IllegalArgumentException("Unknown discount type");
//        }
//    }
}