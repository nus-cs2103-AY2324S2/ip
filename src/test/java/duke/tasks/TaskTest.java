package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void Task_toString() {
        assertEquals("[T][X] homework", new Task("homework", "T", true).toString());
    }

    @Test
    public void Task_toStorageFormat() {
        assertEquals("T | X | homework", new Task("homework", "T", true).convertToStorageFormat());
    }
}
