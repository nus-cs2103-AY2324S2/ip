package SamuelBot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testGetTaskType() {
        Todo todo = new SamuelBot.Todo("Buy groceries");
        assertEquals("T", todo.getTaskType(), "Task type should be 'T'");
    }

    @Test
    public void testDescription() {
        String description = "Buy groceries";
        SamuelBot.Todo todo = new SamuelBot.Todo(description);
        assertEquals(description, todo.getDescription(), "Description should match");
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Buy groceries");
        todo.markAsDone();
        assertEquals(true, todo.isDone(), "Task should be marked as done");
    }

    @Test
    public void testMarkAsUndone() {
        Todo todo = new Todo("Buy groceries");
        todo.markAsDone();
        todo.markAsUndone();
        assertEquals(false, todo.isDone(), "Task should be marked as undone");
    }
}
