package atlas.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createToDo_ShouldHaveCorrectToString() {
        ToDo td = new ToDo("Read book");
        assertEquals("[T][ ] Read book", td.toString());
    }
    @Test
    public void toggle_ShouldHaveCorrectToString() {
        ToDo td = new ToDo("Read book");
        td.toggle();
        assertEquals("[T][X] Read book", td.toString());
    }
}