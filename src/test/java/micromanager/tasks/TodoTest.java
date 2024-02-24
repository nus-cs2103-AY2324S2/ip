package micromanager.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import micromanager.Launcher;

public class TodoTest {

    @Test
    public void testUnmarkedFileStringConversion() {
        Launcher.Todo testTodo = new Launcher.Todo("task");
        assertEquals("[T][ ] task", testTodo.toString());
    }

    @Test
    public void testMarkedFileStringConversion() {
        Launcher.Todo testTodo = new Launcher.Todo("task");
        testTodo.markDone();
        assertEquals("[T][X] task", testTodo.toString());
    }
}
