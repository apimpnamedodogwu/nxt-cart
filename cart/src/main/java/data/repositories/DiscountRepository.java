package data.repositories;

import data.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByDiscountType(String discountType);
}
