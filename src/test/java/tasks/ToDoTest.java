package tasks;

import org.junit.jupiter.api.Test;

import anxi.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testSaveFileSyntax() {
        assertEquals("T | 0 | play", new ToDo("play").saveFileString());
    }
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] play", new ToDo("play").toString());
    }
}
