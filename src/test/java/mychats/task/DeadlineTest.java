package mychats.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    void deadlineTest() {
        Deadline output = new Deadline("reach home", "2023-10-09 2200");
        assertEquals("[D][ ] reach home (by: Oct 09 2023 22:00)", output.toString(), "toString() passed");
        assertEquals("D | 0 | reach home | 2023-10-09 2200", output.toFileString(), "toFileString() passed");
        output.markAsDone();
        assertEquals("[D][X] reach home (by: Oct 09 2023 22:00)", output.toString(), "markAsDone() passed");
        assertEquals("D | 1 | reach home | 2023-10-09 2200", output.toFileString(), "markAsDone() passed");
        output.unMarkAsDone();
        assertEquals("[D][ ] reach home (by: Oct 09 2023 22:00)", output.toString(), "unMarkAsDone() passed");
        assertEquals("D | 0 | reach home | 2023-10-09 2200", output.toFileString(), "unMarkAsDone() passed");
    }
}
