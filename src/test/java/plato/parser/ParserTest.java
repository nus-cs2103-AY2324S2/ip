package plato.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import plato.PlatoException;
import plato.task.Deadline;
import plato.task.Event;
import plato.task.Task;
import plato.task.TaskManager;
import plato.task.Todo;


class ParserTest {
    private static final String RESPONSE_ADD = "Got it. I've added this task:";
    private static final String RESPONSE_VIEW_DATES = "Here are the task scheduled on that date!!";
    private static final String RESPONSE_FIND = "Here are the matching tasks in your list";

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

    @Test
    public void parse_event() throws PlatoException {
        TaskManager assertManager = new TaskManager();
        LocalDateTime from = LocalDateTime.of(2023, 4, 12, 18, 0);
        LocalDateTime by = LocalDateTime.of(2023, 4, 13, 15, 0);
        Task testItem = new Event("science test ", from, by);
        assertManager.addItem(testItem);
        String[] testValues = new String[3];
        testValues[0] = RESPONSE_ADD;
        testValues[1] = testItem.toString();
        testValues[2] = assertManager.numOfTask();
        TaskManager testManager = new TaskManager();
        assertArrayEquals(testValues, Parser.parse("event science test /from 12-04-23 1800 /to 1500 13/04/23",
                                                   testManager));
    }

    @Test
    public void parse_find() throws PlatoException {
        TaskManager assertManager = new TaskManager();
        Task testItem = new Deadline("math test ", LocalDateTime.of(2023, 4, 12, 18, 0));
        assertManager.addItem(testItem);
        String[] testValues = new String[2];
        testValues[0] = RESPONSE_FIND;
        testValues[1] = "1. " + testItem;
        TaskManager testManager = new TaskManager();
        testManager.addItem(testItem);
        assertArrayEquals(testValues, Parser.parse("find math", testManager));
    }

    @Test
    public void parse_view() throws PlatoException {
        TaskManager assertManager = new TaskManager();
        Task testItem = new Deadline("math test ", LocalDateTime.of(2023, 4, 12, 18, 0));
        assertManager.addItem(testItem);
        String[] testValues = new String[2];
        testValues[0] = RESPONSE_VIEW_DATES;
        testValues[1] = "1. " + testItem;
        TaskManager testManager = new TaskManager();
        testManager.addItem(testItem);
        assertArrayEquals(testValues, Parser.parse("view 12-04-23", testManager));
    }

}

