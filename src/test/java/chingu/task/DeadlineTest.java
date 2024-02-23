package chingu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline testDeadline = new Deadline("Finalized the software product", "2024/06/28", "High");
        String expected = "[D][ ] Finalized the software product Priority: High (by: Jun 28 2024)";

        assertEquals(expected, testDeadline.toString());
    }

    @Test
    public void toStringTest2() {
        Deadline testDeadline = new Deadline("Hand in the report", "2024/08/15", "High");
        String expected = "[D][ ] Hand in the report Priority: High (by: Aug 15 2024)";

        assertEquals(expected, testDeadline.toString());
    }
}
