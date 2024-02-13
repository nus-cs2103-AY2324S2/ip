package lite.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void saveToFileTest() {
        Task task = new Task("something");
        assertEquals("0!something", task.saveToFile());
        task.setDone();
        assertEquals("1!something", task.saveToFile());
        task.setUndone();
        assertEquals("0!something", task.saveToFile());
    }
}
