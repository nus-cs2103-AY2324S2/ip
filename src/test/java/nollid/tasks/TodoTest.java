package nollid.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void createTodo_descriptionProvided_success() {
        Todo todo = new Todo("Buy groceries");

        String result = todo.toString();

        assertEquals("[T][ ] Buy groceries", result);
    }

    @Test
    public void setDone_markAsDone_success() {
        Todo todo = new Todo("Read a book");

        todo.setDone(true);

        assertEquals("[T][X] Read a book", todo.toString());
    }
}
