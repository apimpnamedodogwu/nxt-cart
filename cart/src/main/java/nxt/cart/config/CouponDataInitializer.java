package nxt.cart.config;

import lombok.AllArgsConstructor;
import nxt.cart.data.models.Coupon;
import nxt.cart.data.models.enums.Coupons;
import nxt.cart.data.repositories.CouponRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CouponDataInitializer implements ApplicationRunner {

    private final CouponRepository couponRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (couponRepository.count() == 0) {
            List<Coupon> coupons = new ArrayList<>();
            for (Coupons coupon : Coupons.values()) {
                Coupon newCoupon = new Coupon();
                newCoupon.setCouponCode(coupon);
                coupons.add(newCoupon);
            }
            couponRepository.saveAll(coupons);
            System.out.println("Coupons populated successfully.");
        } else {
            System.out.println("Coupons already populated.");
        }
    }
}

