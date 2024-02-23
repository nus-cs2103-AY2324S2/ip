package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import destiny.Deadline;
import destiny.DestinyException;
import destiny.Event;
import destiny.Task;
import destiny.TaskList;
import destiny.ToDo;


public class ListCmdTest {
    @Test
    public void getListAsString_validInput_properString() throws DestinyException {
        TaskList tempTL = new TaskList(new ArrayList<Task>());
        tempTL.addTask(new ToDo("test"));
        tempTL.addTask(new Deadline("test", "29/10/2024 1234"));
        tempTL.addTask(new Event("test", "12/12/2024 1234", "13/12/2024 1234"));
        assertEquals("Here are the tasks in your list:\n"
                + " 1. [T][ ] test\n"
                + " 2. [D][ ] test (by: Oct 29 2024 12:34PM)\n"
                + " 3. [E][ ] test (from: Dec 12 2024 12:34PM  to: Dec 13 2024 12:34PM)",
                new ListCmd().execute(tempTL));
    }

    @Test
    public void getListAsString_invalidInput_exceptionThrown() {
        try {
            TaskList tempTL = new TaskList(new ArrayList<Task>());
            assertEquals("",
                    new ListCmd().execute(tempTL));
        } catch (DestinyException e) {
            assertEquals("Something went wrong...\nThere's nothing in your list so far",
                    e.getMessage());
        }
    }
}
