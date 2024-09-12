package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todo_toStringTest() {
        Todo tsk = new Todo("borrow books");
        assertEquals("[T][ ] borrow books", tsk.toString());
    }

    @Test
    public void todo_toSaveTest() {
        Todo tsk = new Todo("borrow books");
        assertEquals("T|0|borrow books", tsk.toSave());
    }
}
