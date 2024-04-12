package nxt.cart.config;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nxt.cart.data.models.Coupon;
import nxt.cart.data.models.Discount;
import nxt.cart.data.models.Rule;
import nxt.cart.data.models.enums.Coupons;
import nxt.cart.data.models.enums.DiscountType;
import nxt.cart.data.models.enums.RuleType;
import nxt.cart.data.repositories.CouponRepository;
import nxt.cart.data.repositories.DiscountRepository;
import nxt.cart.data.repositories.RuleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CouponDataInitializer implements ApplicationRunner {

    private final CouponRepository couponRepository;
    private final DiscountRepository discountRepository;
    private final RuleRepository ruleRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (couponRepository.count() == 0) {
            List<Coupon> coupons = new ArrayList<>();

            Coupon fixedCoupon = new Coupon();
            fixedCoupon.setCouponCode(Coupons.FIXED10);
            Rule fixedMinimumValue = new Rule();
            fixedMinimumValue.setType(RuleType.MIN_CART_VALUE);
            fixedMinimumValue.setValue(50);
            Rule fixedMinimumItems = new Rule();
            fixedMinimumItems.setType(RuleType.MIN_CART_ITEMS);
            fixedMinimumItems.setValue(1);
            fixedMinimumValue = ruleRepository.save(fixedMinimumValue);
            fixedMinimumItems = ruleRepository.save(fixedMinimumItems);

            fixedCoupon.getRules().add(fixedMinimumValue);
            fixedCoupon.getRules().add(fixedMinimumItems);

            Discount fixedDiscount = new Discount();
            fixedDiscount.setDiscountType(DiscountType.FIXED);
            fixedDiscount.setFixedAmount(10);
            fixedDiscount = discountRepository.save(fixedDiscount);
            fixedCoupon.getDiscounts().add(fixedDiscount);


            coupons.add(fixedCoupon);
            couponRepository.saveAll(coupons);


            Coupon percentCoupon = new Coupon();
            percentCoupon.setCouponCode(Coupons.PERCENT10);
            Rule percentMinimumValue = new Rule();
            percentMinimumValue.setType(RuleType.MIN_CART_VALUE);
            percentMinimumValue.setValue(100);
            Rule percentMinimumItems = new Rule();
            percentMinimumItems.setType(RuleType.MIN_CART_ITEMS);
            percentMinimumItems.setValue(2);
            percentMinimumValue = ruleRepository.save(percentMinimumValue);
            percentMinimumItems = ruleRepository.save(percentMinimumItems);

            fixedCoupon.getRules().add(percentMinimumValue);
            fixedCoupon.getRules().add(percentMinimumItems);

            Discount percentDiscount = new Discount();
            percentDiscount.setDiscountType(DiscountType.PERCENTAGE);
            percentDiscount.setPercentage(10);
            percentDiscount = discountRepository.save(percentDiscount);
            percentCoupon.getDiscounts().add(percentDiscount);

            coupons.add(percentCoupon);
            couponRepository.saveAll(coupons);


            Coupon mixedCoupon = new Coupon();
            mixedCoupon.setCouponCode(Coupons.MIXED10);
            Rule mixedMinimumValue = new Rule();
            mixedMinimumValue.setType(RuleType.MIN_CART_VALUE);
            mixedMinimumValue.setValue(200);
            Rule mixedMinimumItems = new Rule();
            mixedMinimumItems.setType(RuleType.MIN_CART_ITEMS);
            mixedMinimumItems.setValue(3);
            mixedMinimumValue = ruleRepository.save(mixedMinimumValue);
            mixedMinimumItems = ruleRepository.save(mixedMinimumItems);

            fixedCoupon.getRules().add(mixedMinimumValue);
            fixedCoupon.getRules().add(mixedMinimumItems);

            Discount mixedDiscount = new Discount();
            mixedDiscount.setDiscountType(DiscountType.GREATER);
            mixedDiscount.setPercentage(10);
            mixedDiscount.setFixedAmount(10);
            mixedDiscount = discountRepository.save(mixedDiscount);
            mixedCoupon.getDiscounts().add(mixedDiscount);

            coupons.add(mixedCoupon);
            couponRepository.saveAll(coupons);


            Coupon rejectedCoupon = new Coupon();
            mixedCoupon.setCouponCode(Coupons.REJECTED10);
            Rule rejectedMinimumValue = new Rule();
            rejectedMinimumValue.setType(RuleType.MIN_CART_VALUE);
            rejectedMinimumValue.setValue(1000);
            Rule rejectedMinimumItems = new Rule();
//            rejectedMinimumItems.setType(RuleType.MIN_CART_ITEMS);
//            rejectedMinimumItems.setValue(3);
            rejectedMinimumValue = ruleRepository.save(rejectedMinimumValue);
//            rejectedMinimumItems = ruleRepository.save(rejectedMinimumItems);

            fixedCoupon.getRules().add(rejectedMinimumValue);
//            fixedCoupon.getRules().add(rejectedMinimumItems);

            Discount rejectedDiscount = new Discount();
            rejectedDiscount.setDiscountType(DiscountType.FIXED);
            rejectedDiscount.setDiscountType(DiscountType.PERCENTAGE);
            rejectedDiscount.setPercentage(10);
            rejectedDiscount.setFixedAmount(10);
            rejectedDiscount = discountRepository.save(rejectedDiscount);
            rejectedCoupon.getDiscounts().add(rejectedDiscount);

            coupons.add(rejectedCoupon);
            couponRepository.saveAll(coupons);


            System.out.println("Coupons populated successfully.");
        } else {
            System.out.println("Coupons already populated.");
        }
    }
}

