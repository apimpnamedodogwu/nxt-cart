package nxt.cart.data.repositories;

import nxt.cart.data.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
