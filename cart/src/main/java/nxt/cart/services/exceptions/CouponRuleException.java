package nxt.cart.services.exceptions;

public class CouponRuleException extends Exception{

    public CouponRuleException (String message) {
        super(String.format(message));
    }
}
