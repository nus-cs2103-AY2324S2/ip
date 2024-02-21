package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void testChangeStatus() {
        Task t1 = new Task("do work", "f", "[T]");
        t1.changeStatus("mark");
        assertEquals("[T][X] do work", t1.showAll());

        Task t2 = new Task("do stuff", "t", "[T]");
        t2.changeStatus("unmark");
        assertEquals("[T][ ] do stuff", t2.showAll());
    }
}