package friendlytool.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import friendlytool.command.CommandTypes;

public class TaskListTest {
    @Test
    public void addToDoTest() {
        TaskList list = new TaskList();
        try {
            list.addTask("todo task1", CommandTypes.TODO);
            String expected = "task1";
            assertEquals(expected, list.get(0).getName());
        } catch (FtException e) {
            System.out.println(e.getMessage());
            fail();
        }

        try {
            list.addTask("deadline /from 2014", CommandTypes.DEADLINE);
            fail();
        } catch (Exception e) {
            assertEquals("Error: Please tell me your task and its deadline", e.getMessage());
        }
    }

    @Test
    public void addEventTest() {
        TaskList list = new TaskList();
        try {
            list.addTask("event longEvent /from 2014-02-03T16:00 /to 2019-03-01T17:00", CommandTypes.EVENT);
            assertEquals("longEvent", list.get(0).getName());
        } catch (FtException e) {
            System.out.println(e.getMessage());
            fail();
        }

        try {
            list.addTask("event longEvent2 /from 2014-02-03T16:00 /to 2019-03", CommandTypes.EVENT);
            fail();
        } catch (FtException e) {
            assertEquals("Invalid date format. Please follow yyyy-mm-ddThh:mm format.", e.getMessage());
        }
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList();
        try {
            list.addTask("event longEvent /from 2014-02-03T16:00 /to 2019-03-01T17:00", CommandTypes.EVENT);
            list.mark("mark 1");
            assertTrue(list.get(0).isDone());
        } catch (FtException e) {
            System.out.println(e.getMessage());
        }

        try {
            list.mark("mark 2");
        } catch (FtException e) {
            assertEquals("Error: Please provide valid index", e.getMessage());
        }
    }

}
