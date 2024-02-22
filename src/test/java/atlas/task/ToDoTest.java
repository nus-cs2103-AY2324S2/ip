package atlas.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private static final int DEFAULT_PRIORITY = 3;
    @Test
    public void createToDoShouldHaveCorrectToString() {
        ToDo td = new ToDo("Read book", DEFAULT_PRIORITY);
        assertEquals(td.toString(), "[T][ ] Read book (P:" + DEFAULT_PRIORITY + ")");
    }
    @Test
    public void toggleShouldHaveCorrectToString() {
        ToDo td = new ToDo("Read book", DEFAULT_PRIORITY);
        td.toggle();
        assertEquals(td.toString(), "[T][X] Read book (P:" + DEFAULT_PRIORITY + ")");
    }
}