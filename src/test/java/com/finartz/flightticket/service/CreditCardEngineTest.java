package com.finartz.flightticket.service;

import com.finartz.flightticket.web.exception.FlightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardEngineTest {
    CreditCardEngine creditCardEngine;

    @BeforeEach
    void setUp() {
        creditCardEngine = new CreditCardEngine();
    }

    @Test
    void shouldFail_ifCreditCardNumberIsLessThan16(){
        String creditCardNumber = "1234567890";
        Throwable throwable = catchThrowable(() -> creditCardEngine.validateCreditCard(creditCardNumber));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Credit card number is not valid.", throwable.getMessage());
    }

    @Test
    void shouldFail_ifCreditCardNumberIsGreaterThan19() {
        String creditCardNumber = "1234-5678-9012-3456-7890";
        Throwable throwable = catchThrowable(() -> creditCardEngine.validateCreditCard(creditCardNumber));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Credit card number is not valid.", throwable.getMessage());

    }

    @Test
    void shouldFail_ifCreditCardNumberIsBetween16And19() {
        String creditCardNumber = "1234-5678-9012-34";
        Throwable throwable = catchThrowable(() -> creditCardEngine.validateCreditCard(creditCardNumber));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Credit card number is not valid.", throwable.getMessage());

    }

    @Test
    void shouldFail_ifCreditCardNumberIs16ButContainsNotDigit() {
        String creditCardNumber = "1234567890123xyz";
        Throwable throwable = catchThrowable(() -> creditCardEngine.validateCreditCard(creditCardNumber));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Credit card number is not valid.", throwable.getMessage());
    }

    @Test
    void shouldFail_ifCreditCardNumberIs19ButContainsNotDigit() {
        String creditCardNumber = "1234-5678-9012-3xyz";
        Throwable throwable = catchThrowable(() -> creditCardEngine.validateCreditCard(creditCardNumber));
        assertNotNull(throwable);
        assertTrue(throwable instanceof FlightException);
        assertEquals("Credit card number is not valid.", throwable.getMessage());
    }

    @Test
    void maskCreditCard_IfCreditCardNumber16(){
        String creditCardNumber = "1234567890123456";
        String maskedCreditCard = creditCardEngine.maskCreditCard(creditCardNumber);

        assertEquals("123456******3456", maskedCreditCard);
    }

    @Test
    void maskCreditCard_IfCreditCardNumber19() {
        String creditCardNumber = "1234-5678-9012-3456";
        String maskedCreditCard = creditCardEngine.maskCreditCard(creditCardNumber);

        assertEquals("123456******3456", maskedCreditCard);
    }

}