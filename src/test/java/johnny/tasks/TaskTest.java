package johnny.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void toString_unMarkedTask_success() {
        Task task = new Task("do homework");
        assertEquals("[ ] do homework", task.toString());
    }

    @Test
    public void toString_markedTask_success() {
        Task task = new Task("do homework");
        task.mark();
        assertEquals("[X] do homework", task.toString());
    }

    @Test
    public void addToFile_unMarkedTask_success() {
        Task task = new Task("do homework");
        assertEquals("0 | do homework", task.addToFile());
    }

    @Test
    public void addToFile_markedTask_success() {
        Task task = new Task("do homework");
        task.mark();
        assertEquals("1 | do homework", task.addToFile());
    }

    @Test
    public void formatInputDate_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2013-01-23T21:30");
        assertEquals("2013/01/23 2130", new Task("dummy").formatInputDate(dateTime));
    }

    @Test
    public void formatOutputDate_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2013-01-23T21:30");
        assertEquals("Jan 23 2013 09:30 PM", new Task("dummy").formatOutputDate(dateTime));
    }

}
