package kai;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void todoToStringTest() {
        Todo todo = new Todo("Buy candy");
        assert("[T][ ] Buy candy".equals(todo.toString()));
    }

    @Test
    public void todoMarkAsDoneTest() {
        Todo todo = new Todo("Read a book");
        todo.markAsDone();
        assert("[T][X] Read a book".equals(todo.toString()));
    }
}
