package Luna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addTaskListTest(){
        TaskList tl = new TaskList();
        assertEquals(tl.size(), 0);
        tl.add(new ListEntryTodo("Item 1", false));

        assertEquals(tl.size(), 1);

        tl.add(new ListEntryTodo("Item 2", false));
        tl.add(new ListEntryTodo("Item 3", false));
        tl.add(new ListEntryTodo("Item 4", false));
        assertEquals(tl.size(), 4);

    }

    @Test
    public void markAndUnmarkTest(){
        TaskList tl = new TaskList();

        assertEquals(tl.size(), 0);
        tl.add(new ListEntryTodo("Item 1", false));
        tl.add(new ListEntryTodo("Item 2", false));
        assertEquals(tl.size(), 2);
        assertFalse(tl.get(0).isDone);
        assertFalse(tl.get(1).isDone);

        tl.get(1).markEntry();


        assertFalse(tl.get(0).isDone);
        assertTrue(tl.get(1).isDone);
        tl.get(0).unmarkEntry();

        assertFalse(tl.get(0).isDone);
        assertTrue(tl.get(1).isDone);

        tl.get(1).unmarkEntry();

        assertFalse(tl.get(0).isDone);
        assertFalse(tl.get(1).isDone);


    }
}