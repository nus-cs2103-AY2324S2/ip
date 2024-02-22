package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testToDoConstruction() {
        String description = "Read book";
        ToDo todo = new ToDo(description);
        assertEquals("[T] [ ] " + description, todo.toString(), "ToDo.toString() should return the correct format.");
    }
}
