package toothless.tasks;

import org.junit.jupiter.api.Test;
import toothless.Toothless;
import toothless.ToothlessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void createDeadline() {
        try {
            Deadline deadline = new Deadline("Eat sandwich", "2024-12-31T22:00");
            assertEquals("Eat sandwich (by: Dec 31 2024 22:00)", deadline.toString());
        } catch (ToothlessException e) {
            fail();
        }
    }

    @Test
    public void markAndUnmarkDeadline() {
        try {
            Deadline deadline = new Deadline("Eat sandwich", "2024-12-31T22:00");
            deadline.markAsDone();
            assertEquals("X", deadline.getStatusIcon());
            deadline.markAsNotDone();
            assertEquals(" ", deadline.getStatusIcon());
        } catch (ToothlessException e) {
            fail();
        }
    }

    @Test
    public void saveDeadline() {
        try {
            Deadline deadline = new Deadline("Eat sandwich","2024-12-31T22:00");
            assertEquals("D | 0 | Eat sandwich | 2024-12-31T22:00", deadline.toWrite());
        } catch (ToothlessException e) {
            fail();
        }
    }
}
