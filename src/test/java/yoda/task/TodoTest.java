package yoda.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoConstructor_description_correctStringRepresentation() {
        String description = "Read book";
        Todo todo = new Todo(description);
        assertEquals("[T][ ] Read book", todo.toString());
    }

    @Test
    public void toString_notDone_todoString() {
        Todo todo = new Todo("Complete assignment");
        assertEquals("[T][ ] Complete assignment", todo.toString());
    }

    @Test
    public void toString_done_todoString() {
        Todo todo = new Todo("Finish homework");
        todo.markAsDone();
        assertEquals("[T][X] Finish homework", todo.toString());
    }
}
