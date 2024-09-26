package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_unMarkedDeadline_success() {
        Deadline deadline = new Deadline("return book", "24/8/2024 2359");
        assertEquals("[D][ ] return book (by: 24 Aug 2024, 2359hrs)", deadline.toString());
    }

    @Test
    public void toString_markedDeadline_success() {
        Deadline deadline = new Deadline("return book", "24/8/2024 2359");
        deadline.setDone(true);
        assertEquals("[D][X] return book (by: 24 Aug 2024, 2359hrs)", deadline.toString());
    }

    @Test
    public void toFileString_unMarkedDeadline_success() {
        Deadline deadline = new Deadline("return book", "24/8/2024 2359");
        assertEquals("D|0|return book|24/8/2024 2359", deadline.toFileString());
    }

    @Test
    public void toFileString_markedDeadline_success() {
        Deadline deadline = new Deadline("return book", "24/8/2024 2359");
        deadline.setDone(true);
        assertEquals("D|1|return book|24/8/2024 2359", deadline.toFileString());
    }
}
