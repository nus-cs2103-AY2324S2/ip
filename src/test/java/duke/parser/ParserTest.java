package duke.parser;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ParserTest {

    @Test
    public void parse_todo() throws DukeException {
        String RESPONSE_ADD = "Got it. I've added this task:";
        TaskManager assertManager = new TaskManager();
        Task testItem = new Todo("Haha");
        assertManager.addItem(testItem);
        ArrayList<String> testValues = new ArrayList<>();
        testValues.add(RESPONSE_ADD);
        testValues.add(testItem.toString());
        testValues.add(assertManager.numOfTask());
        TaskManager testManager = new TaskManager();
        assertEquals(testValues, Parser.parse("todo Haha", testManager));
    }
    @Test
    public void parse_deadline() throws DukeException {
        String RESPONSE_ADD = "Got it. I've added this task:";
        TaskManager assertManager = new TaskManager();
        Task testItem = new Deadline("math test ",
                LocalDateTime.of(2023, 4, 12, 18, 0));
        assertManager.addItem(testItem);
        ArrayList<String> testValues = new ArrayList<>();
        testValues.add(RESPONSE_ADD);
        testValues.add(testItem.toString());
        testValues.add(assertManager.numOfTask());
        TaskManager testManager = new TaskManager();
        assertEquals(testValues, Parser.parse("deadline math test /by 12-04-23 1800", testManager));
    }

}

