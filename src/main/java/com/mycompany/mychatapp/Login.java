/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

/**
 *
 * @author Lhoza
 */
public class Login {
  
    String username;
    String password;

    // Check username
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // Check password
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() >= 8) {

            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);

                if (Character.isUpperCase(ch)) {
                    hasCapital = true;
                } else if (Character.isDigit(ch)) {
                    hasNumber = true;
                } else if (!Character.isLetterOrDigit(ch)) {
                    hasSpecial = true;
                }
            }

            if (hasCapital && hasNumber && hasSpecial) {
                return true;
            }
        }

        return false;
    }

    // Check SA phone number using regex
    public boolean checkCellPhoneNumber(String number) {
        // Reference: Basic regex pattern for SA numbers with international code
        // Format example: +27831234567
        if (number.matches("^\\+27\\d{9}$")) {
            return true;
        } else {
            return false;
        }
    }

    // Register user
    public String registerUser(String username, String password, String phone, String fName, String lName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Save details if everything is correct
        this.username = username;
        this.password = password;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // Login check
    public boolean loginUser(String username, String password) {
        if (username.equals(username) && password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // Return login message
    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + username + "; it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }  
}
