package toothless.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadline() {
        Deadline deadline = new Deadline("Eat sandwich", "2024-12-31T22:00");
        assertEquals("Eat sandwich (by: Dec 31 2024 22:00)", deadline.toString());
    }

    @Test
    public void markAndUnmarkDeadline() {
        Deadline deadline = new Deadline("Eat sandwich", "2024-12-31T22:00");
        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());
        deadline.markAsNotDone();
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void saveDeadline() {
        Deadline deadline = new Deadline("Eat sandwich","2024-12-31T22:00");
        assertEquals("D | 0 | Eat sandwich | 2024-12-31T22:00", deadline.toWrite());
    }
}
