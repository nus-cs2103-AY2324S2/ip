package judy.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import judy.commands.AddTaskCommand;
import judy.commands.Command;
import judy.commands.DeleteTaskCommand;
import judy.commands.MarkTaskCommand;
import judy.commands.UnmarkTaskCommand;
import judy.exceptions.JudyException;
import judy.storage.Storage;
import judy.task.Deadline;
import judy.task.TaskList;
import judy.task.Todo;
import judy.ui.Ui;

public class ParserTest {
    private static TaskList taskList;
    private Parser parser;
    private Command command;

    private void createList() {
        Ui ui = new Ui();
        taskList = new TaskList();
        Storage storage = new Storage("testFile.txt");
        Todo t = new Todo("read book");
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-01-01 2359", pattern);
        Deadline d = new Deadline("project", by);

        new AddTaskCommand(t, taskList).execute(storage, ui);
        new AddTaskCommand(d, taskList).execute(storage, ui);
    }
    @Test
    public void markTaskCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(MarkTaskCommand.COMMAND_WORD + " 2", taskList);
        command = parser.parse();
        assertInstanceOf(MarkTaskCommand.class, command);
    }
    @Test
    public void markTaskCommand_invalidIndex_exceptionThrown() {
        try {
            createList();
            parser = new Parser(MarkTaskCommand.COMMAND_WORD + " 6", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! Invalid task index. Type 'list' to list out your tasks. ", e.getMessage());
        }
    }
    @Test
    public void markTaskCommand_emptyInput_exceptionThrown() {
        try {
            createList();
            parser = new Parser(MarkTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The index of task cannot be empty. ", e.getMessage());
        }
    }
    @Test
    public void markTaskCommand_wrongIndexFormat_exceptionThrown() {
        try {
            createList();
            parser = new Parser(MarkTaskCommand.COMMAND_WORD + " abc", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The index you've input is not an integer. ", e.getMessage());
        }
    }
    @Test
    public void unmarkTaskCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " 2", taskList);
        command = parser.parse();
        assertInstanceOf(UnmarkTaskCommand.class, command);
    }
    @Test
    public void unmarkTaskCommand_invalidIndex_exceptionThrown() {
        try {
            createList();
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " 6", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! Invalid task index. Type 'list' to list out your tasks. ", e.getMessage());
        }
    }
    @Test
    public void unmarkTaskCommand_emptyInput_exceptionThrown() {
        try {
            createList();
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The index of task cannot be empty. ", e.getMessage());
        }
    }
    @Test
    public void unmarkTaskCommand_wrongIndexFormat_exceptionThrown() {
        try {
            createList();
            parser = new Parser(UnmarkTaskCommand.COMMAND_WORD + " abc", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The task index you've input is not an integer. ", e.getMessage());
        }
    }
    @Test
    public void deleteTaskCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(DeleteTaskCommand.COMMAND_WORD + " 2", taskList);
        command = parser.parse();
        assertInstanceOf(DeleteTaskCommand.class, command);
    }
    @Test
    public void deleteTaskCommand_invalidIndex_exceptionThrown() {
        try {
            createList();
            parser = new Parser(DeleteTaskCommand.COMMAND_WORD + " 6", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! Invalid task index. Type 'list' to list out your tasks. ", e.getMessage());
        }
    }
    @Test
    public void deleteTaskCommand_emptyInput_exceptionThrown() {
        try {
            createList();
            parser = new Parser(DeleteTaskCommand.COMMAND_WORD, taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The index of task cannot be empty. ", e.getMessage());
        }
    }
    @Test
    public void deleteTaskCommand_wrongIndexFormat_exceptionThrown() {
        try {
            createList();
            parser = new Parser(DeleteTaskCommand.COMMAND_WORD + " abc", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The task index you've input is not an integer. ", e.getMessage());
        }
    }
    @Test
    public void addTodoCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(AddTaskCommand.TODO + " read book", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }
    @Test
    public void emptyTodoDescription_exceptionThrown() {
        try {
            createList();
            parser = new Parser(AddTaskCommand.TODO, taskList);
            command = parser.parse();
        } catch (JudyException e) {
            String expected = "OOPS! The description of a todo cannot be empty!\n"
                    + "Eg format: todo <Description>";
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void addDeadlineCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(AddTaskCommand.DEADLINE + " project /by 2024-06-06 2359", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }
    @Test
    public void addDealineCommand_emptyDescription_exceptionThrown() {
        try {
            createList();
            parser = new Parser(AddTaskCommand.DEADLINE, taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The description of a deadline cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void addDeadlineCommand_invalidCommandFormat_exceptionThrown() {
        try {
            createList();
            parser = new Parser(AddTaskCommand.DEADLINE + " 2024-12-31 0000", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            String expected = "OOPS! Invalid deadline format! Try this: \n"
                    + "deadline <Description> /by yyyy-MM-dd HHmm";
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void addEventCommand_parseSuccessfully() throws JudyException {
        createList();
        parser = new Parser(AddTaskCommand.EVENT + " event /from 2026-06-06 0000 /to 2026-08-08 0000", taskList);
        command = parser.parse();
        assertInstanceOf(AddTaskCommand.class, command);
    }

    @Test
    public void addEventCommand_emptyDescription_exceptionThrown() {
        try {
            createList();
            parser = new Parser(AddTaskCommand.EVENT , taskList);
            command = parser.parse();
        } catch (JudyException e) {
            assertEquals("OOPS! The description of an event cannot be empty. ", e.getMessage());
        }
    }
    @Test
    public void addEventCommand_invalidCommandFormat_exceptionThrown() {
        try {
            createList();
            parser = new Parser(AddTaskCommand.EVENT + " /from 2024-12-31 0000", taskList);
            command = parser.parse();
        } catch (JudyException e) {
            String expected = "OOPS! Invalid event format! Try this:\n "
                    + "event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm";
            assertEquals(expected, e.getMessage());
        }
    }
}
