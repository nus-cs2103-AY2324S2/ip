package tasks;
import org.junit.jupiter.api.Test;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void newTodoTask() {
        Todo todo = new Todo("read", false);
        assertEquals("[T] [ ] read", todo.toString());
    }

    @Test
    public void markTodo() {
        Todo todo = new Todo("read", false);
        todo.markAsDone();
        assertEquals("[T] [X] read", todo.toString());
    }
}
