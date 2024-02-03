package academicweapon.task;
import academicweapon.exceptions.DukeExceptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void testDeadlineToString() {
        Deadline task = null;
        try {
            task = new Deadline("Testing deadline", "2024-02-21 0800");
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        String expectedOutput = "[D][ ] Testing deadline (by: Feb 21 2024 0800)";
        assertEquals(expectedOutput, task.toString());
    }
}
