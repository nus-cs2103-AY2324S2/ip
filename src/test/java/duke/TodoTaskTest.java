package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void testStringConversion() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            assertEquals("[T][ ] read book", todo.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFileConversion() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            assertEquals("T | 0 | read book", todo.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            todo.markDone(true);
            assertEquals("[T][X] read book", todo.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndone() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            todo.markDone(true);
            todo.markDone(false);
            assertEquals("[T][ ] read book", todo.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsDoneAndFileConversion() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            todo.markDone(true);
            assertEquals("T | 1 | read book", todo.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() {
        TodoTask todo = null;
        try {
            todo = new TodoTask("read book");
            todo.markDone(true);
            todo.markDone(false);
            assertEquals("T | 0 | read book", todo.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
