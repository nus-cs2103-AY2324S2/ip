package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoCreation_basicDescription_stringRepresentationCorrect() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void markAsDone_todoNotDoneBefore_markedAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void markAsUndone_todoInitiallyDone_markedAsNotDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.unmark();
        assertEquals("[T][ ] read book", todo.toString());
    }
}
