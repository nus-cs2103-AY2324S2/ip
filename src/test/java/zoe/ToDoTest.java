package zoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest extends Task {
    @Test
    public void testSaveTask() {
        //test that input matches the required save format when it is newly created
        ToDo testTD = new ToDo("read book");
        assertEquals("todo_read book_0",testTD.saveTask());
    }

    @Test
    public void testSaveTaskNoSpace() {
        //test that input matches the required save format when it is newly created
        ToDo testTD = new ToDo("readbook");
        assertEquals("todo_readbook_0",testTD.saveTask());
    }

    @Test
    public void testSaveTaskEmpty() {
        //test that input matches the required save format when it is newly created, in this case an empty instance
        //can be created, but the UI does not allow this behaviour when user interacts with zoe
        ToDo testTD = new ToDo("");
        assertEquals("todo__0",testTD.saveTask());
    }

    @Test
    public void testSaveTaskMarked() {
        //test that input matches the required save format when it is created
        ToDo testTD = new ToDo("read book", "1");
        assertEquals("todo_read book_1",testTD.saveTask());
    }
}