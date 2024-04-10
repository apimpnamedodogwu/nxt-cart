package services.rule;

import data.dto.Cart;

public interface Rule {

    boolean ruleIsValid(Cart cart);
}
