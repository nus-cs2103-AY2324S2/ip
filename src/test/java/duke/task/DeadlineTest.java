package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void fileStringTest() {
        Deadline deadline = new Deadline("homework", LocalDate.parse("2024-04-04"));
        String expected = "D/ /homework/2024-04-04";
        String result = deadline.fileString();
        assertEquals(expected, result);
    }
}
