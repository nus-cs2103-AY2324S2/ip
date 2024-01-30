import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void testRemoveTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test"));

        int initialSize = tasks.getSize();
        tasks.removeTask(0);

        assertEquals(initialSize - 1, tasks.getSize());
    }
}
