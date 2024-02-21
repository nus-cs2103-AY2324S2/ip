package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTaskTest {
    @Test
    public void testShowAll() {
        TodoTask t1 = new TodoTask("do work", "f");
        assertEquals("[T][ ] do work", t1.showAll());

        TodoTask t2 = new TodoTask("do stuff", "t");
        assertEquals("[T][X] do stuff", t2.showAll());
    }

    @Test
    public void testToString() {
        TodoTask t1 = new TodoTask("do work", "f");
        assertEquals("T / f / do work", t1.toString());

        TodoTask t2 = new TodoTask("do stuff", "t");
        assertEquals("T / t / do stuff", t2.toString());
    }
}