package tasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineToString_inputCorrectDateTime_success() {
        Task deadline = new Deadline("return book", "2024-05-05");
        String deadlineSuccess = deadline.toString();
        assertEquals(deadlineSuccess, "[D][ ]return book(May 05 2024)");
    }
    @Test
    public void DeadlineStorageToString_inputWrongDateTime_failure() {
        Task deadline = new Deadline("return book", "2024-05-05");
        String deadlineSuccess = deadline.saveStorage();
        assertEquals(deadlineSuccess, "D|0|return book|2024-05-05T00:00:00");
    }
}
