package teemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void test1() {
        TaskList tl = new TaskList();
        tl.add(new Event("event", LocalDate.parse("2024-02-02"), LocalDate.parse("2025-02-02")));
        Task todo = new ToDo("todo");
        todo.mark();
        tl.add(todo);
        assertEquals(tl.size(), 2);
        tl.delete(2);
        assertEquals(tl.size(), 1);
    }

    @Test
    public void test2() {
        TaskList tl = new TaskList();
        tl.add(new Event("event", LocalDate.parse("2024-02-02"), LocalDate.parse("2025-02-02")));
        Task todo = new ToDo("todo");
        todo.mark();
        tl.add(todo);
        assertEquals(tl.list(), "\t1.[E][ ] event (from: 2024-02-02 to: 2025-02-02)\n"
                + "\t2.[T][X] todo\n");
    }
}
