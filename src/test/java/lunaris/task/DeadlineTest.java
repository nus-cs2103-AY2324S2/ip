package lunaris.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTestFile() {
        assertEquals(new Deadline("0", "homework", "2023-02-01 2359").toFile(),
                "D | 0 |  homework | Feb 01 2023 2359");
    }

    @Test
    public void deadlineTestString() {
        assertEquals(new Deadline("0", "homework", "2023-02-01 2359").toString(),
                "[D][ ]  homework (by: Feb 01 2023 2359)");
    }
}
