package commands;


import org.junit.jupiter.api.Test;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {
    @Test
    public void toString_savedInTaskList_correct() {
        ToDo todo = new ToDo("Have Dinner");
        assertEquals("[T][ ] Have Dinner", todo.toString());
    }
    @Test
    public void fileString_savedInHardDisk_correct() {
        ToDo todo = new ToDo("Borrow books");
        assertEquals("T | 0 | Borrow books", todo.fileString());
    }

    @Test
    public void markAsDone_toString_savedInTaskList_correct() {
        ToDo todo = new ToDo("Have Breakfast");
        todo.markAsDone();
        assertEquals("[T][X] Have Breakfast", todo.toString());
    }

    @Test
    public void markAsDone_fileString_savedInHardDisk_correct() {
        ToDo todo = new ToDo("Return Books");
        todo.markAsDone();
        assertEquals("T | 1 | Return Books", todo.fileString());
    }

}
