package mychats.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    void deadlineTest() {
        Deadline output = new Deadline("reach home", "2023-10-09 2200");
        assertEquals("[D][ ] reach home (by: Oct 09 2023 22:00)", output.toString(), "toString() passed");
        assertEquals("D | 0 | reach home | 2023-10-09 2200", output.toFileString(), "toFileString() passed");
        output.markTask();
        assertEquals("[D][X] reach home (by: Oct 09 2023 22:00)", output.toString(), "markTask() passed");
        assertEquals("D | 1 | reach home | 2023-10-09 2200", output.toFileString(), "markTask() passed");
        output.unmarkTask();
        assertEquals("[D][ ] reach home (by: Oct 09 2023 22:00)", output.toString(), "unmarkTask() passed");
        assertEquals("D | 0 | reach home | 2023-10-09 2200", output.toFileString(), "unmarkTask() passed");
    }
}
