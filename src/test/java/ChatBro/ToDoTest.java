package ChatBro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void testTodoToString() {
        ToDo todo = new ToDo("Hello!");
        assertEquals(todo.toString(), "[T][ ] Hello!");
    }
    @Test
    public void testTodoToStorageFormat() {
        ToDo todo = new ToDo("Hello!");
        assertEquals(todo.toStorageFormat(), "T♢ ♢Hello!");
    }
}
