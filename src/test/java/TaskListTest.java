import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Task;
import duke.TaskList;
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
