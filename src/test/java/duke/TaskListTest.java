package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addDeleteTest() {
        TaskList t = new TaskList();
        assertEquals(t.length(), 0);
        Todo L = new Todo("bruh", false);
        t.add(L);
        assertEquals(t.length(), 1);
        Task L2 = t.delete(0);
        assertEquals(t.length(), 0);
        assertEquals(L, L2);
    }

}
