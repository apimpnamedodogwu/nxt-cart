package nxt.cart.data.repositories;

import nxt.cart.data.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
