/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.mychatapp.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
        Message msg = new Message("Hello World");
        assertNotNull(msg.getMessageId(), "Message ID should not be null");
        assertNotNull(msg.getHashCode(), "Hash should not be null");
        assertEquals("Hello World", msg.getMessageText(), 
                     "Message text should match");
    }
    
    @Test
    public void testUniqueIds() {
        Message msg1 = new Message("Test1");
        Message msg2 = new Message("Test2");
        
        assertNotEquals(msg1.getMessageId(), msg2.getMessageId(), 
                        "Each message should have unique ID");
    }
    
    @Test
    public void testHashGeneration() {
        Message msg = new Message("Test");
        assertEquals(2, msg.getHashCode().length(), 
                     "Hash should be 2 characters");
        assertEquals(msg.getMessageId().substring(0, 2), msg.getHashCode(), 
                     "Hash should match first 2 chars of ID");
    }
    
    @Test
    public void testStoreMessage() {
        Message msg = new Message("Test message");
        String testFile = "test_message.json";
        
        msg.storeMessage(testFile);
        
        File file = new File(testFile);
        assertTrue(file.exists(), "JSON file should be created");
        
        // Clean up
        file.delete();
    }
}


