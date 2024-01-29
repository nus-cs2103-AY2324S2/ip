package duke;

import command.*;
import model.Deadline;
import model.TaskList;
import model.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static TaskList taskList;
    private Parser parser;
    private Command command;

    @BeforeAll
    static void setUp() {
        taskList = new TaskList();
        ToDo todo = new ToDo("todo");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
        LocalDateTime time = LocalDateTime.parse("01-01-2024 0000", formatter);
        Deadline deadline = new Deadline("deadline", time);
        Storage storage = new Storage("test.dat");
        new AddTaskCommand(todo, taskList).execute(storage);
        new AddTaskCommand(deadline, taskList).execute(storage);
    }

    @AfterEach
    public void cleanup() {
        File file = new File("test.dat");
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }

    @Test
    public void listTaskCommand_writtenCorrectly() throws DukeException {
        parser = new Parser(ListTaskCommand.COMMAND_WORD, taskList);
        command = parser.parse();
        assertInstanceOf(ListTaskCommand.class, command);
    }

    @Test
    public void markTaskCommand_writtenCorrectly() throws DukeException {
        parser = new Parser(MarkTaskCommand.COMMAND_WORD + " 1", taskList);
        command = parser.parse();
        assertInstanceOf(MarkTaskCommand.class, command);
    }

    @Test
    public void unmarkTaskCommand_writtenCorrectly() throws DukeException {
        parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " 1", taskList);
        command = parser.parse();
        assertInstanceOf(UnmarkTaskCommand.class, command);
    }

    @Test
    public void listTaskCommand_invalidIndex_exceptionThrown() {
        try {
            parser = new Parser(ListTaskCommand.COMMAND_WORD + " 9", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid index.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void markTaskCommand_invalidIndex_exceptionThrown() {
        try {
            parser = new Parser(MarkTaskCommand.COMMAND_WORD + " 9", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid index.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void unmarkTaskCommand_invalidIndex_exceptionThrown() {
        try {
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " 9", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid index.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void listTaskCommand_emptyInput_exceptionThrown() {
        try {
            parser = new Parser(ListTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index of task cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void markTaskCommand_emptyInput_exceptionThrown() {
        try {
            parser = new Parser(MarkTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index of task cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void unmarkTaskCommand_emptyInput_exceptionThrown() {
        try {
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index of task cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void listTaskCommand_notAnInteger_exceptionThrown() {
        try {
            parser = new Parser(ListTaskCommand.COMMAND_WORD + " a", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index you've input is not an integer.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void markTaskCommand_notAnInteger_exceptionThrown() {
        try {
            parser = new Parser(MarkTaskCommand.COMMAND_WORD + " a", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index you've input is not an integer.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void unmarkTaskCommand_notAnInteger_exceptionThrown() {
        try {
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " a", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index you've input is not an integer.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_todo_writtenCorrectly() throws DukeException {
        parser = new Parser(AddTaskCommand.TODO + " test", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void addTaskCommandTest_event_writtenCorrectly() throws DukeException {
        parser = new Parser(AddTaskCommand.EVENT + " test /from 01-01-2024 0000 /to 2359", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void addTaskCommandTest_deadline_writtenCorrectly() throws DukeException {
        parser = new Parser(AddTaskCommand.DEADLINE + " test /by 01-01-2024 0000", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void addTaskCommandTest_todoEmptyDescription_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.TODO, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                    + "   OOPS!!! The description of a todo cannot be empty.\n"
                    + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_eventEmptyDescription_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.EVENT, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The description of an event cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_deadlineEmptyDescription_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.DEADLINE, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The description of a deadline cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_eventNoTime_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.EVENT + "test", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid input. Use: event event_title /from dd-mm-yyyy HHmm /to HHmm\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_deadlineNoTime_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.DEADLINE + "test", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid input. Use: deadline deadline_title /by dd-mm-yyyy HHmm\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_eventEmptyTime_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.EVENT + "test /from", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Invalid date & time input. Use /from dd-mm-yyyy HHmm /to HHmm\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void addTaskCommandTest_deadlineEmptyTime_exceptionThrown() {
        try {
            parser = new Parser(AddTaskCommand.DEADLINE + "test /by", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! Use dd-mm-yyyy HHmm as the date format.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void deleteTaskCommandTest_writtenCorrectly() throws DukeException {
        parser = new Parser(DeleteTaskCommand.COMMAND_WORD + " 1", taskList);
        command = parser.parse();
        assertInstanceOf(DeleteTaskCommand.class, command);
    }

    @Test
    public void deleteTaskCommand_notAnInteger_exceptionThrown() {
        try {
            parser = new Parser(DeleteTaskCommand.COMMAND_WORD + " a", taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index you've input is not an integer.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void deleteTaskCommand_emptyInput_exceptionThrown() {
        try {
            parser = new Parser(DeleteTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (DukeException e) {
            assertEquals(
                    "____________________________________________________________\n"
                            + "   OOPS!!! The index of task cannot be empty.\n"
                            + "____________________________________________________________\n",
                    e.getMessage()
            );
        }
    }

    @Test
    public void invalidCommandTest() throws DukeException {
        parser = new Parser("blah", taskList);
        command = parser.parse();
        assertInstanceOf(InvalidCommand.class, command);
    }
}
