/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

import java.security.SecureRandom;

/**
 *
 * @author HP
 */
public class Generate {
public String generatePassword(int lengthOfPassword) {
        String letterUpper = "ABCDEFGHIJKLMNOQPRSTUYWVZX";
        String letterLower = "abcdefghijklmnoqprstuvwyzx";
        String number = "123456789";
        String special = "!@#&^?$*";
        SecureRandom random = new SecureRandom();
        String newPassword = "";
        for (int i = 0; i < lengthOfPassword; i++) {
            int ratio = random.nextInt(4);
            if (ratio == 0) {
                newPassword += letterUpper.charAt(random.nextInt(letterUpper.length()));
            } else if (ratio == 1) {
                newPassword += letterLower.charAt(random.nextInt(letterLower.length()));
            } else if (ratio == 2) {
                newPassword += number.charAt(random.nextInt(number.length()));
            } else {
                newPassword += special.charAt(random.nextInt(special.length()));
            }
        }
        return newPassword;
    }
}
