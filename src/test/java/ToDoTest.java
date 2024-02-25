import exceptions.JojoTaskNoDescException;
import jojo.Parser;
import jojo.TaskList;
import jojo.ToDo;
import jojo.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testUnmark() {
        ToDo todo = new ToDo("Mop the floor");
        todo.setUndone();
        String result = todo.toString();
        assertEquals("[T][ ] Mop the floor", result);
    }

    @Test
    public void testParseEmptyEvent() {
        String cmd = "event";
        assertThrows(JojoTaskNoDescException.class, () -> Parser.parse(cmd, new Ui(), new TaskList(new ArrayList<>())));
    }

    @Test
    public void testParseTrailingToDo() {
        String cmd = "todo hw ";
        String desc = Parser.parseToDoOrFind(cmd);
        assertEquals(desc, "hw");
    }
}
