package tsundere.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void taskToString_success() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void taskToSaveString_success() {
        Task task = new Task("test");
        assertEquals("0,0,test", task.toSaveString());
    }

    @Test
    public void taskGetStatusIcon_success() {
        Task task = new Task("test");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void taskMarkAsDone_success() {
        Task task = new Task("test");
        task.markTaskAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void taskUnMark_success() {
        Task task = new Task("test");
        task.markTaskAsDone();
        assertEquals("X", task.getStatusIcon());
        task.unMarkTask();
        assertEquals(" ", task.getStatusIcon());
    }
}
