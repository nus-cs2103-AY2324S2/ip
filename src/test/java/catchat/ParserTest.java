package catchat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * ParserTest class contains the test cases for Todo class
 */
public class ParserTest {
    /**
     * Tests if handleExit returns goodbye message
     */
    @Test
    public void test_returns_goodbye_message() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList taskListObj = new TaskList(new Storage(taskList), taskList);
        Parser parser = new Parser(taskListObj);
        String result = parser.handleExit();
        assertEquals("Bye. Hope to see you again soon!", result);
    }

    /**
     * Tests if an invalid index returns the error message
     */
    @Test
    public void test_mark_undone_invalid_index() {
        ArrayList<Task> taskList = new ArrayList<>();
        Storage storage = new Storage(taskList);
        TaskList newTaskList = new TaskList(storage, taskList);
        Parser parser = new Parser(newTaskList);
        parser.executeUserInput("mark undone abc");
        String result = parser.handleMarkUndone();
        assertEquals("Oops, that wasn't a valid task index :P", result);
    }
}
