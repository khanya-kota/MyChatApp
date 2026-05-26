package com.mycompany.mychatapp;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Message class - Creates and stores messages
 * @author Student
 */
public class Message {
    
    // Variables to store message data
    private String messageId;
    private String messageText;
    private String hashCode;
    
    /**
     * Constructor - Creates a new message
     * @param text The message content
     */
    public Message(String text) {
        this.messageText = text;
        
        // Generate a unique ID (simple way)
        long time = System.currentTimeMillis();
        this.messageId = "MSG" + time;
        
        // Create a hash from the first 2 characters of the ID
        this.hashCode = messageId.substring(0, 2).toUpperCase();
    }
    
    /**
     * Get the message ID
     * @return message ID
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * Get the hash code
     * @return hash code
     */
    public String getHashCode() {
        return hashCode;
    }
    
    /**
     * Get the message text
     * @return message text
     */
    public String getMessageText() {
        return messageText;
    }
    
    /**
     * Save message to a JSON file
     * @param filename Name of the file to save to
     */
    public void storeMessage(String filename) {
        try {
            // Create a JSON object for this message
            JSONObject messageObj = new JSONObject();
            messageObj.put("id", messageId);
            messageObj.put("hash", hashCode);
            messageObj.put("text", messageText);
            
            // Read existing messages from file (if it exists)
            JSONArray messagesArray = new JSONArray();
            
            if (Files.exists(Paths.get(filename))) {
                String content = new String(Files.readAllBytes(Paths.get(filename)));
                messagesArray = new JSONArray(content);
            }
            
            // Add the new message to the array
            messagesArray.put(messageObj);
            
            // Write back to the file
            FileWriter file = new FileWriter(filename);
            file.write(messagesArray.toString(4));
            file.close();
            
            System.out.println("Message saved! ID: " + messageId);
            
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    
    /**
     * Check if message is within 250 characters
     * @return true if OK, false if too long
     */
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message too long. Max 250 characters.";
        }
    }
    
    /**
     * Show send options (S/D/T)
     * @return result message
     */
    public String sentMessage() {
        System.out.println("S = Send now");
        System.out.println("D = Save for later");
        System.out.println("T = Send with timestamp");
        return "Please choose an option.";
    }
    
    /**
     * Print all message details
     */
    public void printMessageDetails() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Hash Code: " + hashCode);
        System.out.println("Message: " + messageText);
    }
}
