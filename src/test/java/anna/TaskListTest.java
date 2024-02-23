package anna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void test1() {
        Task todo = TaskFactory.createTodo("task desc", false);
        assertEquals("[T][ ] task desc", todo.toString());
    }

    @Test
    public void test2() {
        Task deadline = TaskFactory.createDeadline(
            "task desc",
            "2/12/2019 18:00",
            false
        );
        assertEquals(
            "[D][ ] task desc (by: Dec 02 2019 18:00)",
            deadline.toString()
        );
    }

    @Test
    public void test3() {
        Task event = TaskFactory.createEvent(
            "task desc",
            "2/12/2019 18:00",
            "2/12/2019 19:00",
            false
        );
        assertEquals(
            "[E][ ] task desc (from: Dec 02 2019 18:00 to: Dec 02 2019 19:00)",
            event.toString()
        );
    }
}
