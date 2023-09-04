package com.assessment.secondassessment.encription;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encriptPassword {

    public String encryptPassword(String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            

            byte[] hashBytes = digest.digest(passwordBytes);
            

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean match(String p1, String p2){
        if(p1.equals(p2))
            return true;
    
        return false;
    }

    public boolean isMatch(String input, String hashedValue) {
        try {
            // Create a MessageDigest instance using the same hashing algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Hash the input string
            byte[] hashedInput = md.digest(input.getBytes());

            // Convert the hashed input to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedInput) {
                sb.append(String.format("%02x", b));
            }

            // Compare the hashed input with the provided hashed value
            return sb.toString().equals(hashedValue);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception (e.g., log an error)
            e.printStackTrace();
            return false;
        }
    }
    
}
