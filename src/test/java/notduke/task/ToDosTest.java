package notduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    private ToDos test = new ToDos("Test", false);

    @Test
    public void testSaveOutput() {
        assertEquals(test.saveOutput(), "T | 0 | Test");
    }

    @Test
    public void testTaskInfo() {
        assertEquals(test.taskInfo(), "[T][ ] Test" + System.lineSeparator());
    }
}
