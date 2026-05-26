/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import java.util.Scanner;

/**
 *
 * @author Lhoza
 */
public class MainApp {
    
 public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login obj = new Login();

        String username;
        String password;
        String phone;
        String firstName;
        String lastName;

        // ========== REGISTRATION ==========
        System.out.println("=== Registration ===");

        // First name & last name
        System.out.print("Enter first name: ");
        firstName = input.nextLine();

        System.out.print("Enter last name: ");
        lastName = input.nextLine();

        // Username loop
        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();

            if (obj.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        // Password loop
        while (true) {
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (obj.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        // Phone loop
        while (true) {
            System.out.print("Enter cell phone (+27...): ");
            phone = input.nextLine();

            if (obj.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        // Save user
        obj.registerUser(username, password, phone, firstName, lastName);

        // ========== LOGIN ==========
        System.out.println("\n=== Login ===");

        String loginUser;
        String loginPass;

        while (true) {
            System.out.print("Enter username: ");
            loginUser = input.nextLine();

            System.out.print("Enter password: ");
            loginPass = input.nextLine();

            boolean status = obj.loginUser(loginUser, loginPass);

            if (status) {
                System.out.println(obj.returnLoginStatus(true));
                break;
            } else {
                System.out.println(obj.returnLoginStatus(false));
            }
        }

        input.close();
    }
}