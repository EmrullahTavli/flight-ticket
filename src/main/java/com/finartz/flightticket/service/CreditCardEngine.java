package com.finartz.flightticket.service;

import com.finartz.flightticket.web.exception.FlightException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditCardEngine {
    public void validateCreditCard(String creditCardNumber) {

        if (!StringUtils.isEmpty(creditCardNumber)) {
            if (!(creditCardNumber.length() == 16
                    || creditCardNumber.length() == 19)) {
                throw new FlightException("Credit card number is not valid.");
            } else {
                if (creditCardNumber.length() == 16
                        && !StringUtils.isNumeric(creditCardNumber)) {
                    throw new FlightException("Credit card number is not valid.");
                } else {
                    if (!(StringUtils.isNumeric(StringUtils.substring(creditCardNumber, 0, 4))
                            && StringUtils.isNumeric(StringUtils.substring(creditCardNumber, 5, 9))
                            && StringUtils.isNumeric(StringUtils.substring(creditCardNumber, 10, 14))
                            && StringUtils.isNumeric(StringUtils.substring(creditCardNumber, 15, 19)))) {
                        throw new FlightException("Credit card number is not valid.");
                    }
                }
            }
        } else {
            throw new FlightException("Credit card number is not valid.");
        }
    }

    public String maskCreditCard(String creditCardNumber) {
        if (creditCardNumber.length() == 19) {
            creditCardNumber = new StringBuilder()
                    .append(StringUtils.substring(creditCardNumber, 0, 4))
                    .append(StringUtils.substring(creditCardNumber, 5, 9))
                    .append(StringUtils.substring(creditCardNumber, 10, 14))
                    .append(StringUtils.substring(creditCardNumber, 15, 19))
                    .toString();
        }
        //number of characters to be masked
        String maskString = StringUtils.repeat("*", 6);

        //this will mask between 6 and 12 characters of the string
        return StringUtils.overlay(creditCardNumber, maskString, 6, 12);
    }
}
