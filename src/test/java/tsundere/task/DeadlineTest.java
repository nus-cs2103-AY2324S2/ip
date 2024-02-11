package tsundere.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineToString_success() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals("[D][ ] test (by: Jan 01 2024)", deadline.toString());
    }

    @Test
    public void deadlineToSaveString_success() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals("D,0,test ,Jan 01 2024", deadline.toSaveString());
    }

    @Test
    public void deadlineGetStatusIcon_success() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void deadlineMarkAsDone_success() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        deadline.markTaskAsDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void deadlineUnMark_success() {
        Deadline deadline = new Deadline("test ", "Jan 01 2024");
        deadline.markTaskAsDone();
        assertEquals("X", deadline.getStatusIcon());
        deadline.unMarkTask();
        assertEquals(" ", deadline.getStatusIcon());
    }
}
