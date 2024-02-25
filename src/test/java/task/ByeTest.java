package task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;


public class ByeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testFine() {

        Ui ui = null;

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read book"));

        // Create TaskList instance
        TaskList taskList = new TaskList(tasks);

        // Check the program end
        String result = taskList.bye(ui);
        assertEquals("buzz buzz~~ Bye. Hope to see you again soon!", result);
    }
}
