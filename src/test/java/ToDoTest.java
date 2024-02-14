import jojo.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void testSimpleToStr() {
        ToDo todo = new ToDo("Mop the floor");
        String result = todo.simpleToString();
        assertEquals("T | 0 | Mop the floor", result);

    }

    @Test
    public void testToStr() {
        ToDo todo = new ToDo("Mop the floor");
        String result = todo.toString();
        assertEquals("[T][ ] Mop the floor", result);
    }

    @Test
    public void testMark() {
        ToDo todo = new ToDo("Mop the floor");
        todo.setDone();
        String result = todo.toString();
        assertEquals("[T][X] Mop the floor", result);
    }

    @Test
    public void unmarkTest() {
        ToDo todo = new ToDo("Mop the floor");
        todo.setUndone();
        String result = todo.toString();
        assertEquals("[T][ ] Mop the floor", result);
    }
}
