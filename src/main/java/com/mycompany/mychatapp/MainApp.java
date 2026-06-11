package com.mycompany.mychatapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Application
 */
public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Login object
        Login login = new Login();

        // Runtime message list
        ArrayList<Message> messageList = new ArrayList<>();

        // Load previous stored messages
        Message.loadStoredMessages();

        // =========================================
        // VARIABLES
        // =========================================

        String firstName;
        String lastName;
        String username;
        String password;
        String phoneNumber;

        // =========================================
        // WELCOME
        // =========================================

        System.out.println("====================================");
        System.out.println("          WELCOME TO CHATAPP");
        System.out.println("====================================");

        // =========================================
        // REGISTRATION
        // =========================================

        System.out.println("\n=========== REGISTRATION ===========");

        System.out.print("Enter first name: ");
        firstName = input.nextLine();

        System.out.print("Enter last name: ");
        lastName = input.nextLine();

        // USERNAME VALIDATION
        while (true) {

            System.out.print("Enter username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) {

                System.out.println("Username successfully captured.");
                break;

            } else {

                System.out.println("""
                        Username is not correctly formatted.
                        Username must:
                        - contain an underscore (_)
                        - be no more than 5 characters
                        """);
            }
        }

        // PASSWORD VALIDATION
        while (true) {

            System.out.print("Enter password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {

                System.out.println("Password successfully captured.");
                break;

            } else {

                System.out.println("""
                        Password is not correctly formatted.
                        Password must contain:
                        - at least 8 characters
                        - a capital letter
                        - a number
                        - a special character
                        """);
            }
        }

        // PHONE VALIDATION
        while (true) {

            System.out.print("Enter phone number (+27...): ");
            phoneNumber = input.nextLine();

            if (login.checkCellPhoneNumber(phoneNumber)) {

                System.out.println("Phone number successfully added.");
                break;

            } else {

                System.out.println("""
                        Cell phone number incorrectly formatted.
                        Must contain international code.
                        """);
            }
        }

        // REGISTER USER
        login.registerUser(
                username,
                password,
                phoneNumber,
                firstName,
                lastName
        );

        // =========================================
        // LOGIN
        // =========================================

        System.out.println("\n=============== LOGIN ===============");

        while (true) {

            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            boolean status = login.loginUser(
                    loginUsername,
                    loginPassword
            );

            if (status) {

                System.out.println(
                        login.returnLoginStatus(true)
                );

                break;

            } else {

                System.out.println(
                        login.returnLoginStatus(false)
                );
            }
        }

        // =========================================
        // MAIN MENU
        // =========================================

        boolean running = true;

        while (running) {

            System.out.println("""
                    
                    ====================================
                                MAIN MENU
                    ====================================
                    1. Send Message
                    2. View Sent Messages
                    3. Stored Messages
                    4. Exit
                    """);

            System.out.print("Choose option: ");

            int choice;

            try {

                choice = Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Invalid number.");
                continue;
            }

            switch (choice) {

                // =========================================
                // SEND MESSAGE
                // =========================================
                case 1:

                    System.out.print(
                            "How many messages would you like to send? "
                    );

                    int numberOfMessages;

                    try {

                        numberOfMessages =
                                Integer.parseInt(input.nextLine());

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid number.");
                        break;
                    }

                    for (int i = 1; i <= numberOfMessages; i++) {

                        System.out.println(
                                "\n=========== MESSAGE "
                                + i
                                + " ==========="
                        );

                        // Recipient
                        System.out.print("Enter recipient (+27...): ");
                        String recipient = input.nextLine();

                        // Message
                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();

                        // Create message object
                        Message message =
                                new Message(recipient, messageText);

                        // Validate recipient
                        if (!message.checkRecipientCell()) {

                            System.out.println("""
                                    Cell number is incorrectly formatted.
                                    """);

                            continue;
                        }

                        // Validate message length
                        String messageResult =
                                message.checkMessageLength();

                        if (!messageResult.equals(
                                "Message ready to send."
                        )) {

                            System.out.println(messageResult);

                            continue;
                        }

                        // Send menu
                        System.out.println(message.sentMessage());

                        System.out.print("Choose option: ");

                        int sendOption;

                        try {

                            sendOption =
                                    Integer.parseInt(input.nextLine());

                        } catch (NumberFormatException e) {

                            System.out.println("Invalid option.");
                            continue;
                        }

                        switch (sendOption) {

                            // SEND
                            case 1:

                                message.storeMessage(
                                        "messages.json"
                                );

                                message.addSentMessage();

                                messageList.add(message);

                                System.out.println("""
                                        
                                        Message successfully sent!
                                        """);

                                message.printMessageDetails();

                                break;

                            // DISREGARD
                            case 2:

                                message.addDisregardedMessage();

                                System.out.println("""
                                        
                                        Message disregarded.
                                        """);

                                break;

                            // STORE
                            case 3:

                                message.storeMessage(
                                        "messages.json"
                                );

                                message.addStoredMessage();

                                System.out.println("""
                                        
                                        Message stored successfully.
                                        """);

                                break;

                            default:

                                System.out.println("""
                                        Invalid option selected.
                                        """);
                        }
                    }

                    break;

                // =========================================
                // VIEW SENT MESSAGES
                // =========================================
                case 2:

                    System.out.println("""
                            
                            ========= SENT MESSAGES =========
                            """);

                    if (messageList.isEmpty()) {

                        System.out.println("""
                                No messages sent yet.
                                """);

                    } else {

                        for (Message msg : messageList) {

                            msg.printMessageDetails();
                        }
                    }

                    break;

                // =========================================
                // STORED MESSAGES MENU
                // =========================================
                case 3:

                    boolean subMenu = true;

                    while (subMenu) {

                        System.out.println("""
                                
                                ===== STORED MESSAGES MENU =====
                                a) Display all stored messages
                                b) Display longest message
                                c) Search by message ID
                                d) Search by recipient
                                e) Delete by hash
                                f) Display full report
                                g) Back
                                """);

                        System.out.print("Choose option: ");

                        String subChoice =
                                input.nextLine().toLowerCase();

                        switch (subChoice) {

                            case "a":

                                System.out.println(
                                        Message.displayAllStoredMessages()
                                );

                                break;

                            case "b":

                                System.out.println(
                                        Message.displayLongestMessage()
                                );

                                break;

                            case "c":

                                System.out.print(
                                        "Enter Message ID: "
                                );

                                String id = input.nextLine();

                                System.out.println(
                                        Message.searchByMessageID(id)
                                );

                                break;

                            case "d":

                                System.out.print(
                                        "Enter recipient: "
                                );

                                String recipient =
                                        input.nextLine();

                                System.out.println(
                                        Message.searchByRecipient(
                                                recipient
                                        )
                                );

                                break;

                            case "e":

                                System.out.print(
                                        "Enter hash: "
                                );

                                String hash =
                                        input.nextLine();

                                System.out.println(
                                        Message.deleteByHash(hash)
                                );

                                break;

                            case "f":

                                System.out.println(
                                        Message.printMessages()
                                );

                                break;

                            case "g":

                                subMenu = false;
                                break;

                            default:

                                System.out.println("""
                                        Invalid option.
                                        """);
                        }
                    }

                    break;

                // =========================================
                // EXIT
                // =========================================
                case 4:

                    running = false;

                    System.out.println("""
                            
                            Thank you for using ChatApp.
                            Goodbye!
                            """);

                    break;

                default:

                    System.out.println("""
                            Invalid menu option.
                            """);
            }
        }

        input.close();
    }
}