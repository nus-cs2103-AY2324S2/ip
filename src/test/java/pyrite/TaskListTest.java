package pyrite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import pyrite.task.Event;
import pyrite.task.Deadline;
import pyrite.task.ToDo;
import java.time.LocalDateTime;


public class TaskListTest {
    @Test
    public void toString_emptyList_success(){
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }
    @Test public void toString_singleTask_success(){
        TaskList tasks = new TaskList();
        tasks.add(new ToDo ("test"));
        assertEquals("1. [T][ ] test", tasks.toString());
    }
    @Test public void toString_multipleTasks_success(){
        TaskList tasks = new TaskList();
        tasks.add(new ToDo ("test"));
        tasks.add(
                new Deadline ( "test", LocalDateTime.parse("2024-01-01T00:00")));
        tasks.add(
                new Event("test",
                        LocalDateTime.parse("2024-01-01T00:00"),
                        LocalDateTime.parse("2024-01-01T00:00")));
        assertEquals(
                "1. [T][ ] test\n" +
                        "2. [D][ ] test (by: Jan 1 2024, 00:00)\n" +
                        "3. [E][ ] test (from: Jan 1 2024, 00:00 to: Jan 1 2024, 00:00)",
                tasks.toString());
    }
}
