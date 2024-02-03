package duchess.task;

import duchess.DuchessException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest(){
        try {
            Deadline deadline = new Deadline("buy book", "18-01-2003 1800");
            assertEquals(deadline.toString(), "[D][ ] buy book (by: Jan 18 2003 06:00 PM)");
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }
}
