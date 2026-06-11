package com.mycompany.mychatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Message Class
 */
public class Message {

    // =========================================
    // INSTANCE VARIABLES
    // =========================================

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Message counter
    private static int messageCount = 0;

    // =========================================
    // ARRAYLISTS
    // =========================================

    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> storedMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();

    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();
    private static ArrayList<String> recipients = new ArrayList<>();

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Message(String recipient, String messageText) {

        this.recipient = recipient;
        this.messageText = messageText;

        messageCount++;

        this.messageID = createMessageID();

        this.messageHash = createMessageHash();
    }

    // =========================================
    // GENERATE MESSAGE ID
    // 10 digit random number
    // =========================================

    private String createMessageID() {

        Random random = new Random();

        long number = 1000000000L
                + (long)(random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // =========================================
    // CREATE MESSAGE HASH
    // Example:
    // 00:0:HITHERE
    // =========================================

    private String createMessageHash() {

        String[] words = messageText.trim().split(" ");

        String firstWord = "";
        String lastWord = "";

        if (words.length >= 1) {
            firstWord = words[0];
            lastWord = words[words.length - 1];
        }

        String hash = messageCount
                + ":"
                + messageID.substring(0, 2)
                + ":"
                + (firstWord + lastWord).toUpperCase();

        return hash;
    }

    // =========================================
    // CHECK MESSAGE LENGTH
    // =========================================

    public String checkMessageLength() {

        if (messageText.length() <= 250) {

            return "Message ready to send.";

        } else {

            int excess = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + excess + " characters.";
        }
    }

    // =========================================
    // CHECK RECIPIENT FORMAT
    // =========================================

    public boolean checkRecipientCell() {

        return recipient.startsWith("+")
                && recipient.length() <= 13;
    }

    // =========================================
    // SENT MESSAGE MENU
    // =========================================

    public String sentMessage() {

        return """
               Choose option:
               1) Send Message
               2) Disregard Message
               3) Store Message
               """;
    }

    // =========================================
    // STORE MESSAGE TO JSON
    // =========================================

    public void storeMessage(String filename) {

        try {

            JSONObject obj = new JSONObject();

            obj.put("MessageID", messageID);
            obj.put("Recipient", recipient);
            obj.put("Message", messageText);
            obj.put("MessageHash", messageHash);

            JSONArray array = new JSONArray();

            if (Files.exists(Paths.get(filename))) {

                String content = new String(
                        Files.readAllBytes(Paths.get(filename))
                );

                if (!content.isEmpty()) {

                    array = new JSONArray(content);
                }
            }

            array.put(obj);

            FileWriter writer = new FileWriter(filename);

            writer.write(array.toString(4));

            writer.close();

        } catch (IOException e) {

            System.out.println("Error storing message.");
        }
    }

    // =========================================
    // ADD SENT MESSAGE
    // =========================================

    public void addSentMessage() {

        sentMessages.add(messageText);

        storedMessages.add(messageText);

        messageHashes.add(messageHash);

        messageIDs.add(messageID);

        recipients.add(recipient);
    }

    // =========================================
    // ADD DISREGARDED MESSAGE
    // =========================================

    public void addDisregardedMessage() {

        disregardedMessages.add(messageText);
    }

    // =========================================
    // ADD STORED MESSAGE
    // =========================================

    public void addStoredMessage() {

        storedMessages.add(messageText);
    }

    // =========================================
    // PRINT MESSAGE DETAILS
    // =========================================

    public void printMessageDetails() {

        System.out.println("\n==============================");
        System.out.println("MESSAGE DETAILS");
        System.out.println("==============================");

        System.out.println("Message ID   : " + messageID);
        System.out.println("Message Hash : " + messageHash);
        System.out.println("Recipient    : " + recipient);
        System.out.println("Message      : " + messageText);

        System.out.println("==============================");
    }

    // =========================================
    // LOAD STORED MESSAGES
    // =========================================

    public static void loadStoredMessages() {

        try {

            if (!Files.exists(Paths.get("messages.json"))) {
                return;
            }

            String content = new String(
                    Files.readAllBytes(Paths.get("messages.json"))
            );

            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = array.getJSONObject(i);

                storedMessages.add(
                        obj.getString("Message")
                );
            }

        } catch (Exception e) {

            System.out.println("Could not load stored messages.");
        }
    }

    // =========================================
    // DISPLAY LONGEST MESSAGE
    // =========================================

    public static String displayLongestMessage() {

        if (storedMessages.isEmpty()) {

            return "No stored messages.";
        }

        String longest = storedMessages.get(0);

        for (String msg : storedMessages) {

            if (msg.length() > longest.length()) {

                longest = msg;
            }
        }

        return longest;
    }

    // =========================================
    // SEARCH BY MESSAGE ID
    // =========================================

    public static String searchByMessageID(String id) {

        for (int i = 0; i < messageIDs.size(); i++) {

            if (messageIDs.get(i).equals(id)) {

                return """
                       Message Found
                       -------------------
                       Recipient: %s
                       Message: %s
                       """.formatted(
                        recipients.get(i),
                        sentMessages.get(i)
                );
            }
        }

        return "Message not found.";
    }

    // =========================================
    // SEARCH BY RECIPIENT
    // =========================================

    public static String searchByRecipient(String recipient) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < recipients.size(); i++) {

            if (recipients.get(i).equals(recipient)) {

                result.append("Message: ")
                        .append(sentMessages.get(i))
                        .append("\n");
            }
        }

        if (result.length() == 0) {

            return "No messages found.";
        }

        return result.toString();
    }

    // =========================================
    // DELETE BY HASH
    // =========================================

    public static String deleteByHash(String hash) {

        for (int i = 0; i < messageHashes.size(); i++) {

            if (messageHashes.get(i).equals(hash)) {

                String deletedMessage = sentMessages.get(i);

                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                sentMessages.remove(i);

                return "Deleted Message:\n" + deletedMessage;
            }
        }

        return "Hash not found.";
    }

    // =========================================
    // FULL REPORT
    // =========================================

    public static String printMessages() {

        StringBuilder report = new StringBuilder();

        report.append("\n========== MESSAGE REPORT ==========\n");

        for (int i = 0; i < sentMessages.size(); i++) {

            report.append("\nMessage ID: ")
                    .append(messageIDs.get(i));

            report.append("\nHash: ")
                    .append(messageHashes.get(i));

            report.append("\nRecipient: ")
                    .append(recipients.get(i));

            report.append("\nMessage: ")
                    .append(sentMessages.get(i));

            report.append("\n------------------------------------");
        }

        return report.toString();
    }

    // =========================================
    // DISPLAY ALL STORED MESSAGES
    // =========================================

    public static String displayAllStoredMessages() {

        if (storedMessages.isEmpty()) {

            return "No stored messages.";
        }

        StringBuilder result = new StringBuilder();

        result.append("\n======= STORED MESSAGES =======\n");

        for (String msg : storedMessages) {

            result.append(msg).append("\n");
        }

        return result.toString();
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }
}