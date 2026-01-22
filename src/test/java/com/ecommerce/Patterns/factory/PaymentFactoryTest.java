package com.ecommerce.Patterns.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void factory_shouldCreatePayPalPayment() {
        Payment payment = PaymentFactory.createPayment("PAYPAL");
        assertNotNull(payment);
        assertTrue(payment instanceof PayPalPayment);
    }

    @Test
    void factory_shouldCreateCreditCardPayment() {
        Payment payment = PaymentFactory.createPayment("CARD");
        assertNotNull(payment);
        assertTrue(payment instanceof CreditCardPayment);
    }
}
