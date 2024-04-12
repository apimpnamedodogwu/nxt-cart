package nxt.cart.services.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private  String body;
    private final MessageSource messageSource;

    @ExceptionHandler(CouponRuleException.class)
    public ResponseEntity<Object> handleCouponRuleException(CouponRuleException couponRuleException, WebRequest request) {
        return handleExceptionInternal(couponRuleException, body=couponRuleException.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<Object> handleCouponNotFoundException(CouponNotFoundException couponNotFoundException, WebRequest request) {
        return handleExceptionInternal(couponNotFoundException, body=couponNotFoundException.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
