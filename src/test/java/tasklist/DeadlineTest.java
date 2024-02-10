package tasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    /**
     * Tests whether the Deadline task prints the correct String
     */
    @Test
    public void deadlineToString_inputCorrectDateTime_success() {
        Task deadline = new Deadline("return book", "2024-05-05");
        String deadlineSuccess = deadline.toString();
        assertEquals(deadlineSuccess, "[D][ ]return book(May 05 2024)");
    }

    /**
     * Tests whether the Deadline saves the correct String
     */
    @Test
    public void deadlineStorageToString_inputCorrectDateTime_success() {
        Task deadline = new Deadline("return book", "2024-05-05");
        String deadlineSuccess = deadline.saveStorage();
        assertEquals(deadlineSuccess, "D|0|return book|2024-05-05T00:00:00");
    }
}
