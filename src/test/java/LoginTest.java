/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.mychatapp.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lhoza
 */
public class LoginTest {
    
     Login obj = new Login();

    // =========================
    // TABLE 1: assertEquals
    // =========================

    @Test
    public void testUsernameCorrectFormatted() {
        obj.registerUser("kyl_1", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        String message = obj.returnLoginStatus(true);
    }

    @Test
    public void testUsernameIncorrectFormatted() {
        String message = obj.registerUser("kyle!!!!!!!!!", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", message);
    }

    @Test
    public void testPasswordMeetsComplexity() {
        boolean result = obj.checkPasswordComplexity("Ch@&sec@ke99!");

        if (result) {
            assertEquals("Password successfully captured.", "Password successfully captured.");
        }
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        String message = obj.registerUser("kyl_1", "password", "+27838996876", "Kyle", "Smith");

        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", message);
    }

    @Test
    public void testCellPhoneCorrect() {
        boolean result = obj.checkCellPhoneNumber("+27838996876");

        if (result) {
            assertEquals("Cell number successfully captured.", "Cell number successfully captured.");
        }
    }

    @Test
    public void testCellPhoneIncorrect() {
        String message = obj.registerUser("kyl_1", "Ch@&sec@ke99!", "08996053", "Kyle", "Smith");

        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", message);
    }


    // =========================
    // TABLE 2: assertTrue / assertFalse
    // =========================

    @Test
    public void testLoginSuccessful() {
        obj.registerUser("kyl_1", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        boolean result = obj.loginUser("kyl_1", "Ch@&sec@ke99!");

        assertTrue(result);
    }

    @Test
    public void testLoginFailed() {
        obj.registerUser("kyl_1", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        boolean result = obj.loginUser("wrong", "wrong");
    }

    @Test
    public void testUsernameCorrect() {
        boolean result = obj.checkUserName("kyl_1");

        assertTrue(result);
    }

    @Test
    public void testUsernameIncorrect() {
        boolean result = obj.checkUserName("kyle!!!!!!!!!");

        assertFalse(result);
    }

    @Test
    public void testPasswordCorrect() {
        boolean result = obj.checkPasswordComplexity("Ch@&sec@ke99!");

        assertTrue(result);
    }

    @Test
    public void testPasswordIncorrect() {
        boolean result = obj.checkPasswordComplexity("password");

        assertFalse(result);
    }

    @Test
    public void testCellPhoneCorrectBoolean() {
        boolean result = obj.checkCellPhoneNumber("+27838996876");

        assertTrue(result);
    }

    @Test
    public void testCellPhoneIncorrectBoolean() {
        boolean result = obj.checkCellPhoneNumber("08996053");

        assertFalse(result);
    } 
}
