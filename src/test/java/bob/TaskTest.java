package bob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void test1() {
        Task task = new Task("make dinner");

        assertEquals(false, task.equals(null));
    }

    @Test
    public void test2() {
        Task task1 = new Task("make dinner");
        Task task2 = new Task("Make dinner");

        assertEquals(false, task1.equals(task2));
    }
}
