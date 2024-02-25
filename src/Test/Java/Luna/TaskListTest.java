package Luna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(tl.get(0).check, false);
        assertEquals(tl.get(1).check, false);

        tl.get(1).markEntry();

        assertEquals(tl.get(0).check, false);
        assertEquals(tl.get(1).check, true);

        tl.get(0).unmarkEntry();

        assertEquals(tl.get(0).check, false);
        assertEquals(tl.get(1).check, true);

        tl.get(1).unmarkEntry();

        assertEquals(tl.get(0).check, false);
        assertEquals(tl.get(1).check, false);



    }
}