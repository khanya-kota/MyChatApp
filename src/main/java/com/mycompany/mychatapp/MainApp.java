package com.mycompany.mychatapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MainApp - ChatApp entry point
 * Handles registration, login, and menu
 * @author Student
 */
public class MainApp {
    
    public static void main(String[] args) {
        
        // Create scanner for user input
        Scanner input = new Scanner(System.in);
        
        // Create login object
        Login obj = new Login();
        
        // Variables to store user data
        String username = "";
        String password = "";
        String phone = "";
        String firstName = "";
        String lastName = "";
        
        // ArrayList to store messages
        ArrayList<Message> messageList = new ArrayList<Message>();
        
        // ========== REGISTRATION SECTION ==========
        System.out.println("========================================");
        System.out.println("        WELCOME TO CHATAPP");
        System.out.println("========================================");
        
        System.out.println("\n=== REGISTRATION ===\n");
        
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
        
        // ========== MAIN MENU SECTION ==========
        boolean keepRunning = true;
        
        while (keepRunning) {
            
            System.out.println("========================================");
            System.out.println("              MAIN MENU");
            System.out.println("========================================");
            System.out.println("1. Send Message");
            System.out.println("2. View Messages");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            
            int choice = 0;
            
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.\n");
                continue;
            }
            
            // Switch statement for menu
            switch (choice) {
                
                case 1:
                  
                    // SEND MESSAGE
                    System.out.println("\n--- SEND MESSAGE ---\n");

                    System.out.println("How many messages would you like to send?");
                    
                    int messagesNum = input.nextInt();
                    input.nextLine(); // clear the newline

                    // Loop exactly numMessages times
                    for (int i = 1; i <= messagesNum; i++) {

                        System.out.println("\n=== Message " + i + " of " + messagesNum + " ===");

                        System.out.print("Enter recipient: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message (max 250 chars): ");
                        String msgText = input.nextLine();

                        // Check message length
                        Message newMsg = new Message(msgText);
                        String lengthCheck = newMsg.checkMessageLength();

                        if (lengthCheck.equals("Message ready to send.")) {

                            // Show send options
                            System.out.println("\n" + newMsg.sentMessage());
                            System.out.print("Choose option (S/D/T): ");
                            String sendOption = input.nextLine().toUpperCase();

                            // Create and save message
                            Message finalMsg = new Message(msgText);
                            finalMsg.storeMessage("messages.json");
                            messageList.add(finalMsg);

                            System.out.println("\nMessage sent!");
                            finalMsg.printMessageDetails();

                        } else {
                            System.out.println(lengthCheck);
                        }

                        System.out.println();
                    }

                    System.out.println("Total messages processed: " + messagesNum);
                    break;

                    
                case 2:
                    // VIEW MESSAGES
                    System.out.println("\n--- YOUR MESSAGES ---\n");
                    
                    if (messageList.isEmpty()) {
                        System.out.println("No messages yet.\n");
                    } else {
                        for (int i = 0; i < messageList.size(); i++) {
                            Message msg = messageList.get(i);
                            System.out.println("Message " + (i + 1) + ":");
                            msg.printMessageDetails();
                            System.out.println();
                        }
                    }
                    break;
                    
                case 3:
                    // EXIT
                    System.out.println("\nGoodbye, " + firstName + "!");
                    keepRunning = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Choose 1, 2, or 3.\n");
                    break;
            }
        }
        
        input.close();
    }
}
