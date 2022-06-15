package com.itau.pixservice.domain.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static String EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static int EMAIL_SIZE = 77;

    public boolean isValid(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }

        if(email.length() > EMAIL_SIZE){
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
}
