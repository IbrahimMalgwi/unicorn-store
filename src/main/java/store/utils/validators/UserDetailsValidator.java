package store.utils.validators;

import store.data.dto.CustomerRegistrationRequest;

public class UserDetailsValidator {
    private CustomerRegistrationRequest request;

    public static boolean isValidPassword(String password) {
        System.out.println("match -> "+password.matches("[a-zA-Z0-9(@#$!_)]{8,20}"));
        return password.matches("[a-zA-Z0-9(@#$!_)]{8,20}");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11;
    }

    public static boolean isValidEmailAddress(String email) {
        return email.contains("@");
    }
}
