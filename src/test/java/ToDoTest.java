import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void simpleToStrTest() {
        ToDo todo = new ToDo("Buy groceries");
        String result = todo.simpleToString();
        assertEquals("T | 0 | Buy groceries", result);

    }

    @Test
    public void toStrTest() {
        ToDo todo = new ToDo("Buy groceries");
        String result = todo.toString();
        assertEquals("[T][ ] Buy groceries", result);
    }

    @Test
    public void markTest() {
        ToDo todo = new ToDo("Buy groceries");
        todo.setDone();
        String result = todo.toString();
        assertEquals("[T][X] Buy groceries", result);
    }


}
