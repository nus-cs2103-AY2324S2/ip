package harvard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import harvard.exceptions.HarvardException;
import harvard.tasks.Todo;

public class TaskListTest {
    @Test
    public void taskList_printTaskNotFound_exceptionThrown() {
        TaskList tL = new TaskList();
        tL.add(new Todo("eat"));
        try {
            tL.printString(1);
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, this task could not be found.", e.getMessage());
        }
    }

    @Test
    public void taskList_printTaskFound_success() {
        TaskList tL = new TaskList();
        tL.add(new Todo("eat"));

        try {
            assertEquals("[T][ ] eat", tL.printString(0));
        } catch (HarvardException e) {
            fail();
        }
    }
}
