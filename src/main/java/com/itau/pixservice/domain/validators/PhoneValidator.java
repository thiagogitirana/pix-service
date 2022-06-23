package com.itau.pixservice.domain.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneValidator {

    private static String CELULAR = "^(?:(?:\\+|00)([1-9][0-9])\\s?)(?:(?:\\(?[1-9][0-9]\\)?)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})-?(\\d{4}))$";

    public boolean isValid(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        Pattern pattern = Pattern.compile(CELULAR);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
