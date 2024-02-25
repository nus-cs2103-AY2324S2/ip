package processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.TaskList;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Class for testing AddTaskProcessor class.
 */
public class AddTaskProcessorTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private AddTaskProcessor addTaskProcessor;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        addTaskProcessor = new AddTaskProcessor(taskList, chatbotUi);
    }

    @Test
    public void testProcessCommand_todo() throws IOException {
        String userInput = "todo task1";
        addTaskProcessor.processCommand(userInput);

        assertEquals(1, taskList.size());
        assertEquals("task1", taskList.getTaskAtIndex(0).getDesc());
    }

    @Test
    public void testProcessCommand_deadline() throws IOException {
        String userInput = "deadline task2 //by 12/31/2022 2359";
        addTaskProcessor.processCommand(userInput);

        assertEquals(1, taskList.size());
        assertEquals("task2 ", taskList.getTaskAtIndex(0).getDesc());
        assertEquals("Dec 31 2022, 11PM", taskList.getTaskAtIndex(0).getStart());
    }

    @Test
    public void testProcessCommand_event() throws IOException {
        String userInput = "event task3 //from 01/01/2023 0800 //to 01/02/2023 1800";
        addTaskProcessor.processCommand(userInput);

        assertEquals(1, taskList.size());
        assertEquals("task3 ", taskList.getTaskAtIndex(0).getDesc());
        assertEquals("Jan 01 2023, 8AM", taskList.getTaskAtIndex(0).getStart());
        assertEquals("Feb 01 2023, 6PM", taskList.getTaskAtIndex(0).getEnd());
    }

    @Test
    public void testProcessCommand_blankInput() throws IOException {
        String userInput = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("Can not type a blank input!", outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_blankDescription() throws IOException {
        String userInput = "todo";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("Description of todo can't be blank!", outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_invalidDeadlineSyntax() throws IOException {
        String userInput = "deadline task4 //wrongSyntax";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("You must start the statement with the word `//by`.", outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_missingByKeyword() throws IOException {
        String userInput = "deadline task5 //12/31/2022 2359";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("You must start the statement with the word `//by`.", outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_invalidDateTimeFormat() throws IOException {
        String userInput = "deadline task6 //by 31/12/2022 2359";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        String expectedOutput = "Added the task, but recommend using the date/time format `M/d/yyyy HHmm` for better experience.";
        assertTrue(outputStream.toString().trim().contains(expectedOutput));
    }

    @Test
    public void testProcessCommand_invalidEventSyntax() throws IOException {
        String userInput = "event task7 //wrongSyntax";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("Wrong syntax! Must be `event <task> //from <start date> //to <end date>`",
                outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_missingFromKeyword() throws IOException {
        String userInput = "event task8 //01/01/2023 0800 //to 01/02/2023 1800";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("You must start the statements with the words `//from` and `//to`.",
                outputStream.toString().trim());
    }

    @Test
    public void testProcessCommand_invalidStartDateTimeFormat() throws IOException {
        String userInput = "event task9 //from 01/01/2023 08:00 //to 01/02/2023 1800";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertTrue(
            outputStream.toString().trim().contains("Added the task, but recommend using the date/time format `M/d/yyyy HHmm` "
            + "on both start and end dates for better experience."));
    }

    @Test
    public void testProcessCommand_invalidEndDateTimeFormat() throws IOException {
        String userInput = "event task10 //from 01/01/2023 0800 //to 01/02/2023 6:00PM";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertTrue(outputStream.toString().contains("Added the task, but recommend using the date/time format `M/d/yyyy HHmm` "
                + "on both start and end dates for better experience."));

    }

    @Test
    public void testProcessCommand_invalidCommand() throws IOException {
        String userInput = "invalid command";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        addTaskProcessor.processCommand(userInput);

        assertEquals("Invalid command. Please enter a valid command.", outputStream.toString().trim());
    }
}