package jivox;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jivox.task.Todo;



public class TodoTest {

    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Take out trash");
    }

    @Test
    public void testGetType() {
        assertEquals("T", todo.getType());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] Take out trash", todo.toString());
    }

    @Test
    public void testSaveFormat() {
        assertEquals("T | 0 | Take out trash tag None", todo.saveFormat());

        todo.mark();
        assertEquals("T | 1 | Take out trash tag None", todo.saveFormat());
    }

    @Test
    public void testMarkDone() {
        assertFalse(todo.getStatus());
        todo.mark();
        assertTrue(todo.getStatus());
    }
}
