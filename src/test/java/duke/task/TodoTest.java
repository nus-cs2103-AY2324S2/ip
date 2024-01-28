package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        Todo tsk = new Todo("borrow books");
        assertEquals("[T][ ] borrow books", tsk.toString());
    }

    @Test
    public void toSaveTest(){
        Todo tsk = new Todo("borrow books");
        assertEquals("T|0|borrow books", tsk.toSave());
    }
}
