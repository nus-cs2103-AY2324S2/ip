package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toString_unMarkedTask_success() {
        Task task = new Task("study for finals");
        assertEquals("[ ] study for finals", task.toString());
    }

    @Test
    public void toString_markedTask_success() {
        Task task = new Task("study for finals");
        task.setDone(true);
        assertEquals("[X] study for finals", task.toString());
    }

    @Test
    public void toFileString_unMarkedTask_success() {
        Task task = new Task("study for finals");
        assertEquals("|0|study for finals", task.toFileString());
    }

    @Test
    public void toFileString_markedTask_success() {
        Task task = new Task("study for finals");
        task.setDone(true);
        assertEquals("|1|study for finals", task.toFileString());
    }
}
