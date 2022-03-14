/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Shado
 */
public class NumberUtil {

    public static int getNumber(String number, int defaultValue) {
        int intNumber;
        try {
            intNumber = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            intNumber = defaultValue;
        }
        return intNumber;
    }
}
