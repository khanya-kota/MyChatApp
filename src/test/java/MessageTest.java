/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.mychatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
/**
 *
 * @author Lhoza
 */
public class MessageTest {

    
    @Test
    public void testMessageCreation() {
        Message msg = new Message("+27712345678", "Hello World");
        assertNotNull(msg.getMessageID(), "Message ID should not be null");
        assertNotNull(msg.getMessageHash(), "Hash should not be null");
        assertEquals("Hello World", msg.getMessageText(), 
                     "Message text should match");
    }
    
    @Test
    public void testUniqueIds() {
        Message msg1 = new Message("+27712345678", "Test1");
        Message msg2 = new Message("+27712345678", "Test2");
        
        assertNotEquals(msg1.getMessageID(), msg2.getMessageID(), 
                        "Each message should have unique ID");
    }
    
    @Test
    public void testHashGeneration() {
        Message msg = new Message("+27712345678", "Hello World");
        String[] hashParts = msg.getMessageHash().split(":");

        assertEquals(3, hashParts.length, "Hash should have 3 parts");
        assertEquals(msg.getMessageID().substring(0, 2), hashParts[1],
                     "Hash should include first 2 chars of ID");
        assertEquals("HELLOWORLD", hashParts[2],
                     "Hash should end with first and last words in uppercase");
    }
    
    @Test
    public void testStoreMessage() {
        Message msg = new Message("+27712345678", "Test message");
        String testFile = "test_message.json";
        File file = new File(testFile);
        
        try {
            msg.storeMessage(testFile);
            assertTrue(file.exists(), "JSON file should be created");
        } finally {
            file.delete();
        }
    }
}


