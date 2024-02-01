package aurora.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Test Task");
    }

    @Test
    public void creatingTask_shouldNotBeDone() {
        assertFalse(task.getStatus(), "Newly created task should not be marked as done.");
    }

    @Test
    public void setDone_onTask_shouldBeDone() {
        task.setDone();
        assertTrue(task.getStatus(), "Task should be marked as done after calling setDone.");
    }

    @Test
    public void setNotDone_onTask_shouldNotBeDone() {
        task.setDone();
        task.setNotDone();
        assertFalse(task.getStatus(), "Task should be marked as not done after calling setNotDone.");
    }

    @Test
    public void toFileString_shouldReturnDescription() {
        assertEquals("Test Task", task.toFileString(), "toFileString should return the description of the task.");
    }

    @Test
    public void toString_notDoneTask_shouldContainEmptyBrackets() {
        String expectedString = "[ ] Test Task";
        assertEquals(expectedString, task.toString(), "toString of a not done task should contain [ ].");
    }

    @Test
    public void toString_doneTask_shouldContainXBrackets() {
        task.setDone();
        String expectedString = "[X] Test Task";
        assertEquals(expectedString, task.toString(), "toString of a done task should contain [X].");
    }
}
