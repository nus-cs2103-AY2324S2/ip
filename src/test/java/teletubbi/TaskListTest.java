package teletubbi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void get_validIndex_success() throws TeletubbiException {
        TaskList t = new TaskList("T| |bake cny cookie");
        assertEquals(t.get(0).getDescription(), new ToDo("bake cny cookie").getDescription());
    }

    @Test
    public void get_invalidIndex_exceptionThrown() {
        try {
            TaskList t = new TaskList("T| |bake cny cookie");
            assertEquals(t.get(1).getDescription(), new ToDo("bake cny cookie").getDescription());
            fail();
        } catch (TeletubbiException e) {
            assertEquals("Invalid task number... count properly xx", e.getMessage());
        }
    }

    @Test void delete_validIndex_success() throws TeletubbiException {
        TaskList t = new TaskList("T| |bake cny cookie");
        t.delete(1);
        assertEquals(t.size(), 0);
    }

    @Test void delete_invalidIndex_exceptionThrown() {
        try {
            TaskList t = new TaskList("T| |bake cny cookie");
            t.delete(2);
            fail();
        } catch (TeletubbiException e) {
            assertEquals("Invalid task number... count properly xx", e.getMessage());
        }
    }
}
