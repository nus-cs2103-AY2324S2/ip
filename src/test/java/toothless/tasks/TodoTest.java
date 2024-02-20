package toothless.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void createTodo() {
        Todo todo = new Todo("Make sandwich");
        assertEquals("Make sandwich", todo.toString());
    }

    @Test
    public void markAndUnmarkTodo() {
        Todo todo = new Todo("Make sandwich");
        todo.markAsDone();
        assertEquals("X", todo.getStatusIcon());
        todo.markAsNotDone();
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void saveTodo() {
        Todo todo = new Todo("Make sandwich");
        assertEquals("T | 0 | Make sandwich", todo.toWrite());
    }
}
