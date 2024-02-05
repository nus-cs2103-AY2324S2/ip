package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToStringTest() {
        Todo todo = new Todo("Buy candy");
        assertEquals("[T][ ] Buy candy", todo.toString());
    }

    @Test
    public void todoMarkAsDoneTest() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
        assertEquals("[T][X] Read a book", todo.toString());
    }
}
