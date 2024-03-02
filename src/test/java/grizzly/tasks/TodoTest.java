package grizzly.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodo() {
        Todo t = new Todo(false, "test");
        assertEquals(t.toString(), ("[T][ ] test"));
    }

    @Test
    public void testTodo2() {
        Todo t = new Todo(false, "test");
        t.doTask();
        assertEquals(t.toString(), ("[T][X] test"));
    }

    @Test
    public void testTodo3() {
        Todo t = new Todo(true, "test");
        assertEquals(t.toString(), ("[T][X] test"));
    }

    @Test
    public void testTodoParse() {
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "test");
        try {
            Todo t = Todo.todoParse(false, testMap);
            assertEquals(t.toString(), ("[T][ ] test"));
        } catch (Exception e) {
            fail();
        }
    }
}
