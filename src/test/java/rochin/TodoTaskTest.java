package rochin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    @Test
    public void createTodoTask() {
        TodoTask todo = new TodoTask("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void markTodoTask() {
        TodoTask todo = new TodoTask("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void unmarkTodoTask() {
        TodoTask todo = new TodoTask("read book");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] read book", todo.toString());
    }
}
