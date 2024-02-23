package junjie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import junjie.exceptions.InvalidArgumentException;

import java.time.DateTimeException;

/**
 * Tests the TodoTask class.
 */
public class TodoTaskTest {
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";

    @Test
    public void testStringConversion() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testFileConversion() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        assertEquals("T | 0 | read book | ", todo.toFileString());
    }

    @Test
    public void testEmptyName() throws InvalidArgumentException {
        try {
            TodoTask todo = new TodoTask("");
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_NAME, e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        todo.markDone(true);
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void testMarkAsUndone() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        todo.markDone(true);
        todo.markDone(false);
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkAsDoneAndFileConversion() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        todo.markDone(true);
        assertEquals("T | 1 | read book | ", todo.toFileString());
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() throws InvalidArgumentException {
        TodoTask todo = new TodoTask("read book");
        todo.markDone(true);
        todo.markDone(false);
        assertEquals("T | 0 | read book | ", todo.toFileString());
    }

    @Test
    public void testSingleTag() throws InvalidArgumentException, DateTimeException {
        TodoTask todo = new TodoTask("read book", new String[]{"school"});
        assertEquals("[T][ ] read book (Tags: school)", todo.toString());
    }

    @Test
    public void testMultipleTags() throws InvalidArgumentException, DateTimeException {
        TodoTask todo = new TodoTask("read book", new String[]{"school", "urgent"});
        assertEquals("[T][ ] read book (Tags: school, urgent)", todo.toString());
    }

    @Test
    public void testSingleTagAndFileConversion() throws InvalidArgumentException, DateTimeException {
        TodoTask todo = new TodoTask("read book", new String[]{"school"});
        assertEquals("T | 0 | read book | school", todo.toFileString());
    }

    @Test
    public void testMultipleTagsAndFileConversion() throws InvalidArgumentException, DateTimeException {
        TodoTask todo = new TodoTask("read book", new String[]{"school", "urgent"});
        assertEquals("T | 0 | read book | school urgent", todo.toFileString());
    }
}
