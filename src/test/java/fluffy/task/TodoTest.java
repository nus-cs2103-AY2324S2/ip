package fluffy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void getType_returnsT() {
        Todo todo = new Todo("description");
        assertEquals("T", todo.getType());
    }

    @Test
    public void constructor_correctlySetsValues() {
        Todo todo = new Todo("description", true);
        assertEquals("description", todo.getDescription());
        assertTrue(todo.isDone());
    }

    @Test
    public void toString_containsDescription() {
        String description = "description";
        Todo todo = new Todo(description);
        assertTrue(todo.toString().contains(description));
    }

    @Test
    public void toString_containsDescription_variations() {
        String description1 = "description with spaces";
        Todo todo1 = new Todo(description1);
        assertTrue(todo1.toString().contains(description1));

        String description2 = "description_with_underscores";
        Todo todo2 = new Todo(description2);
        assertTrue(todo2.toString().contains(description2));

        String description3 = "description-with-hyphens";
        Todo todo3 = new Todo(description3);
        assertTrue(todo3.toString().contains(description3));
    }
}
