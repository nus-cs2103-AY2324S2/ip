package tasklist;

import jux.JuxException;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    /**
     * Tests whether the Deadline task prints the correct String
     */
    @Test
    public void deadlineToString_inputCorrectDateTime_success() throws Exception {
            Task deadline = new Deadline("return book", "2024-05-05");
            String deadlineSuccess = deadline.toString();
            assertEquals(deadlineSuccess, "[D][ ]return book(May 05 2024)");

    }
    @Test
    public void deadlineToString_inputIncorrectDateTime_success(){
        try {
            Task deadline = new Deadline("return book", "21331");
            Task deadline2 = new Deadline("return book", "Tuedadad");
            fail();
        } catch (JuxException e) {
            assertEquals( "enter date and time using yyyy MM dd HH:mm", e.getMessage());
        }
        try {
            Task deadline3 = new Deadline("return book", "Tue 1900");
            Task deadline4 = new Deadline("return book", "Tue wrong");
            fail();
        } catch (JuxException e) {
            assertEquals( "Invalid time, use the format of \"day HH:mm\"", e.getMessage());
        }

    }

    /**
     * Tests whether the Deadline saves the correct String
     */
    @Test
    public void deadlineStorageToString_inputCorrectDateTime_success() throws Exception {
            Task deadline = new Deadline("return book", "2024-05-05");
            String deadlineSuccess = deadline.saveStorage();
            assertEquals(deadlineSuccess, "D|0|return book|2024-05-05T00:00:00");
    }
}
