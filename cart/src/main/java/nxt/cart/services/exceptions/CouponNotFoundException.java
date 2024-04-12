package nxt.cart.services.exceptions;

public class CouponNotFoundException extends Exception{

    public CouponNotFoundException (String message) {
        super(String.format(message));
    }
}
