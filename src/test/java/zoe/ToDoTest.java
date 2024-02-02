package zoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest extends Task {
    @Test
    public void testSaveTask() {
        //test that input matches the required save format
        assertEquals("todo_read book_0",new ToDo("read book").saveTask());
    }
}
