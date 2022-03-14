/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Shado
 */
public class StringUtil {

    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PASSWORD_REGEX
            = "((?=.*\\d)(?=.*[a-z]).{6,20})";

    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Validate email with regular expression
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        email = email.trim();
        pattern = Pattern.compile(EMAIL_REGEX);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate password with regular expression
     *
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        password = password.trim();
        pattern = Pattern.compile(PASSWORD_REGEX);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validatePasswordConfirm(String password, String passwordCf) {
        if (validatePassword(password)) {
            return password.equals(passwordCf);
        }
        return false;
    }
}
