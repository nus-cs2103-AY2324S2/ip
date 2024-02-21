package plato.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import plato.PlatoException;
import plato.task.Deadline;
import plato.task.Task;
import plato.task.TaskManager;
import plato.task.Todo;


class ParserTest {
    private static final String RESPONSE_ADD = "Got it. I've added this task:";

    @Test
    public void parse_todo() throws PlatoException {
        TaskManager assertManager = new TaskManager();
        Task testItem = new Todo("Haha");
        assertManager.addItem(testItem);
        String[] testValues = new String[3];
        testValues[0] = RESPONSE_ADD;
        testValues[1] = testItem.toString();
        testValues[2] = assertManager.numOfTask();
        TaskManager testManager = new TaskManager();
        assertArrayEquals(testValues, Parser.parse("todo Haha", testManager));
    }

    @Test
    public void parse_deadline() throws PlatoException {
        TaskManager assertManager = new TaskManager();
        Task testItem = new Deadline("math test ", LocalDateTime.of(2023, 4, 12, 18, 0));
        assertManager.addItem(testItem);
        String[] testValues = new String[3];
        testValues[0] = RESPONSE_ADD;
        testValues[1] = testItem.toString();
        testValues[2] = assertManager.numOfTask();
        TaskManager testManager = new TaskManager();
        assertArrayEquals(testValues, Parser.parse("deadline math test /by 12-04-23 1800", testManager));
    }

}

