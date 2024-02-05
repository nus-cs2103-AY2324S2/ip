package BadApple.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void initialisationTest() {
        Todo todo = new Todo("CS2103");
        assertEquals("Todo [] CS2103", todo.toString());
    }

    @Test
    public void markingTest() {
        Todo todo = new Todo("CS2101");
        TaskList.tasks.add(todo);
        todo.mark(true, 1);
        assertEquals("Todo [X] CS2101", todo.toString());
    }

}
