package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals("[D][ ] test (by: Jan 01 2024)", deadline.toString());
    }

    @Test
    public void testDeadlineToSaveString() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals("D,0,test ,Jan 01 2024", deadline.toSaveString());
    }

    @Test
    public void testDeadlineGetStatusIcon() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void testDeadlineMarkAsDone() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void testDeadlineUnMark() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        deadline.markAsDone();
        deadline.unMark();
        assertEquals(" ", deadline.getStatusIcon());
    }
}
