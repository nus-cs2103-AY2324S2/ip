package duke;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void addTodo() {
        Task task = new Todo("taskName");
        assertEquals("    [T][ ] taskName",
                task.add());
    }

    @Test
    public void addDeadline() {
        Task task = new Deadline("sun", "taskname");
        assertEquals("    [D][ ] taskname(by: sun)",
                task.add());
    }

    @Test
    public void addEvent() {
        Task task = new Event("mon", "tues", "taskname");
        assertEquals("    [E][ ] taskname(from: mon to: tues)",
                task.add());
    }
}
