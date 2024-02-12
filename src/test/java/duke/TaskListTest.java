package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class TaskListTest {
    private TaskList tl = new TaskList();

    @Test
    public void test1() {
        tl.add(new Event("event", LocalDate.parse("2024-02-02"), LocalDate.parse("2025-02-02")));
        Task todo = new ToDo("todo");
        todo.mark();
        tl.add(todo);
        assertEquals(tl.size(), 2);
        tl.delete(2);
        assertEquals(tl.size(), 1);
    }
}
