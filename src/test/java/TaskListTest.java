import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.Task;
import duke.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new Task("Read book");
        tasks.add(task);
        assertEquals(1, tasks.size());
    }
}