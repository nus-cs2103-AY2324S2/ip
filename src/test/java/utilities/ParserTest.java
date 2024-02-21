package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Commands;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Test class for the Parser in the utilities packages
 */
public class ParserTest {
    private TaskListStub taskListStub;
    private StorageStub storageStub;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        taskListStub = new TaskListStub();
        storageStub = new StorageStub("noStringPath");
        parser = new Parser(taskListStub, storageStub);
    }

    /**
     * Tests the parseCommands method for toDo switch case for the correct input (assertTrue if it
     * is added successfully, assertFalse if its not added successfully)
     */
    @Test
    public void parseCommands_toDoCommand_addsToDo() {
        String additionalInfo = "Read Book";
        parser.parseCommands(Commands.TODO, additionalInfo);
        assertFalse(taskListStub.getTasks().isEmpty());
        assertTrue(taskListStub.getTasks().get(0) instanceof Todo);
    }

    /**
     * Tests the parseCommands method for toDo switch case for the incorrect input (assertTrue since
     * it should not be added, and list should be empty)
     */
    @Test
    public void parseCommands_incorrectToDoCommand_doesNotAddTask() {
        parser.parseCommands(Commands.TODO, "");
        assertTrue(taskListStub.getTasks().isEmpty());
    }

    /**
     * Tests the parseCommands method for deadline switch case for the correct input (assertTrue if
     * it is added successfully, assertFalse if its not added successfully)
     */
    @Test
    public void parseCommands_deadlineCommand_addsDeadline() {
        String additionalInfo = "CS2103 Assignment 1 /by 12/12/2023 1800";
        parser.parseCommands(Commands.DEADLINE, additionalInfo);
        assertFalse(taskListStub.getTasks().isEmpty());
        assertTrue(taskListStub.getTasks().get(0) instanceof Deadline);
    }

    /**
     * Tests the parseCommands method for deadline switch case for the incorrect input (assertTrue
     * since it should not be added, and list should be empty)
     */
    @Test
    public void parseCommands_incorrectDeadlineCommand_doesNotAddTask() {
        parser.parseCommands(Commands.DEADLINE, "CS2103 Assignment 1");
        parser.parseCommands(Commands.DEADLINE, "CS2103 Assignment 1 /by");
        parser.parseCommands(Commands.DEADLINE, "CS2103 Assignment 1 /by 12/12/2023 6pm");
        assertTrue(taskListStub.getTasks().isEmpty());
    }

    /**
     * Tests the parseCommands method for event switch case for the correct input (assertTrue if it
     * is added successfully, assertFalse if its not added successfully)
     */
    @Test
    public void parseCommands_eventCommand_addsDeadline() {
        String additionalInfo = "CS2103 Assignment 2 /from 12/12/2023 1800 /to 13/12/2023 1700";
        parser.parseCommands(Commands.EVENT, additionalInfo);
        assertFalse(taskListStub.getTasks().isEmpty());
        assertTrue(taskListStub.getTasks().get(0) instanceof Event);
    }

    /**
     * Tests the parseCommands method for event switch case for the incorrect input (assertTrue
     * since it should not be added, and list should be empty)
     */
    @Test
    public void parseCommands_incorrectEventCommand_doesNotAddTask() {
        parser.parseCommands(Commands.EVENT, "CS2103 Assignment 2");
        parser.parseCommands(Commands.EVENT, "CS2103 Assignment 2 /from 12/12/2023 1800 ");
        parser.parseCommands(Commands.EVENT, "CS2103 Assignment 2 /from 12/12/2023 1800 /to ");
        parser.parseCommands(Commands.EVENT,
                "CS2103 Assignment 2 /from 12/12/2023 1800 /to 11/12/2023 1900");
        parser.parseCommands(Commands.EVENT,
                "CS2103 Assignment 2 /from 12/12/2023 6pm /to 12/12/2023 7pm");
        assertTrue(taskListStub.getTasks().isEmpty());
    }

    /**
     * Tests the parseCommands method for delete switch case, check whether a task has been deleted
     * successfully
     */
    @Test
    public void parseCommands_deleteCommand_deleteTaskFromList() {
        Todo todo = new Todo("Test Task Added To File");
        taskListStub.addTask(todo);

        // Since there is only one task, the ID of the task is 1
        parser.parseCommands(Commands.DELETE, "1");
        assertTrue(taskListStub.getTasks().isEmpty());
    }

    /**
     * Tests the parseCommands method for mark switch case, check whether a task has been marked
     * successfully
     */
    @Test
    public void parseCommands_markCommand_markTaskAsDone() {
        Todo todo = new Todo("Test Task Added To File", false, "HIGH");
        taskListStub.addTask(todo);

        // Since there is only one task, the ID of the task is 1
        parser.parseCommands(Commands.MARK, "1");
        assertEquals("X", taskListStub.getTasks().get(0).getStatusIcon());
    }

    /**
     * Tests the parseCommands method for mark switch case, check whether a task has been unmarked
     * successfully
     */
    @Test
    public void parseCommands_markCommand_markTaskAsNotDone() {
        Todo todo = new Todo("Test Task Added To File", true, "HIGH");
        taskListStub.addTask(todo);

        // Since there is only one task, the ID of the task is 1
        parser.parseCommands(Commands.UNMARK, "1");
        assertEquals(" ", taskListStub.getTasks().get(0).getStatusIcon());
    }

    /**
     * Tests the parseCommands method for priority switch case for the incorrect input
     */
    @Test
    public void parseCommands_incorrectPriorityCommand_doesNotAddTask() {
        Todo todo = new Todo("Test Task Added To File", true, "HIGH");
        taskListStub.addTask(todo);

        // Since there is only one task, the ID of the task is 1
        parser.parseCommands(Commands.PRIORITY, "");
        parser.parseCommands(Commands.PRIORITY, "/id 1");
        parser.parseCommands(Commands.PRIORITY, "/id 1 /priority ");
        parser.parseCommands(Commands.PRIORITY, "/id 2 /priority high");
        parser.parseCommands(Commands.PRIORITY, "/id 1 /priority wrongInput");
        assertEquals("HIGH", taskListStub.getTasks().get(0).getPriority());
    }
}
