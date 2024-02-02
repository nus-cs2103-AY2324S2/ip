package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    ToDos test = new ToDos("Test", false);

    @Test
    public void testSaveOutput() {
        assertEquals(test.saveOutput(), "T | 0 | Test");
    }

    @Test
    public void testTaskInfo() {
        assertEquals(test.taskInfo(), "[T][ ] Test" + System.lineSeparator());
    }
}
