package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.CommandNotFoundException;
import exceptions.ErrorMessages;
import exceptions.IncorrectParametersException;
import exceptions.MissingParametersException;
import exceptions.ParseDateException;
import logic.Validator;
import tasks.TaskList;

public class ParserTest {

    @Test
    public void byeCommand_hasParameters_exceptionThrown() {
        Exception thrown = assertThrows(CommandNotFoundException.class, () ->
            Validator.validateByeCommand("bye now")
        );

        String expectedMessage = ErrorMessages.COMMAND_NOT_FOUND;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void listCommand_hasParameters_exceptionThrown() {
        Exception thrown = assertThrows(CommandNotFoundException.class, () ->
                Validator.validateListCommand("list all tasks")
        );

        String expectedMessage = ErrorMessages.COMMAND_NOT_FOUND;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void markCommand_tooManyParameters_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateMarkCommand("mark 2 3", existingTaskList)
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void markCommand_taskNumberOutOfBounds_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () ->
                Validator.validateMarkCommand("mark 50", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void markCommand_taskNumberNotInteger_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(NumberFormatException.class, () ->
                Validator.validateMarkCommand("mark fifty", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_PARSE_ERROR;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void unmarkCommand_tooManyParameters_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateUnmarkCommand("unmark 2 3", existingTaskList)
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void unmarkCommand_taskNumberOutOfBounds_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () ->
                Validator.validateUnmarkCommand("unmark 50", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void unmarkCommand_taskNumberNotInteger_exceptionThrown() {
        TaskList existingTaskList = new TaskList();
        Exception thrown = assertThrows(NumberFormatException.class, () ->
                Validator.validateUnmarkCommand("unmark fifty", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_PARSE_ERROR;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void deleteTask_tooManyParameters_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateDeleteCommand("delete 5 5", existingTaskList)
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void deleteTask_taskNumberOutOfBounds_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () ->
                Validator.validateDeleteCommand("delete 50", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void deleteTask_taskNumberIsString_exceptionThrown() {
        TaskList existingTaskList = new TaskList();

        Exception thrown = assertThrows(NumberFormatException.class, () ->
                Validator.validateDeleteCommand("delete fifty", existingTaskList)
        );

        String expectedMessage = ErrorMessages.TASK_NUMBER_PARSE_ERROR;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void deleteAllTasks_tooManyParameters_exceptionThrown() {
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateDeleteAllCommand("delete all 50")
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createTodo_missingDescription_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateTodoCommand("todo")
        );

        String expectedMessage = ErrorMessages.MISSING_TASK_DESCRIPTION;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createTodo_extraDueDateParameter_exceptionThrown() {
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateTodoCommand("todo /by 2024-10-10")
        );

        String expectedMessage = ErrorMessages.DUE_DATE_NOT_NEEDED;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createTodo_extraFromToDateParameter_exceptionThrown() {
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateTodoCommand("todo /from 2024-10-10 /to 2024-10-10")
        );

        String expectedMessage = ErrorMessages.FROM_TO_DATE_NOT_NEEDED;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createDeadline_missingDescription_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateDeadlineCommand("deadline /by 2024-10-10")
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createDeadline_missingDueDate_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateDeadlineCommand("deadline Assignment")
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createDeadline_extraFromToDate_exceptionThrown() {
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateDeadlineCommand("deadline Assignment /by 2024-10-10 /from 2024-10-10 /to 2024-10-11")
        );

        String expectedMessage = ErrorMessages.FROM_TO_DATE_NOT_NEEDED;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createDeadline_wrongDateFormat_exceptionThrown() {
        Exception thrown = assertThrows(ParseDateException.class, () ->
                Validator.validateDeadlineCommand("deadline Sleep /by 14/09/2011")
        );

        String expectedMessage = ErrorMessages.INCORRECT_DATE_FORMAT;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createEvent_missingDescription_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateEventCommand("event /from 2024-10-10 /to 2024-10-11")
        );

        String expectedMessage = ErrorMessages.INCORRECT_PARAMETERS;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createEvent_missingFromDate_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateEventCommand("event Assignment /to 2024-10-10")
        );

        String expectedMessage = ErrorMessages.MISSING_FROM_DATE;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createEvent_missingToDate_exceptionThrown() {
        Exception thrown = assertThrows(MissingParametersException.class, () ->
                Validator.validateEventCommand("event Assignment /from 2024-10-10")
        );

        String expectedMessage = ErrorMessages.MISSING_TO_DATE;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void createEvent_extraDueDate_exceptionThrown() {
        Exception thrown = assertThrows(IncorrectParametersException.class, () ->
                Validator.validateEventCommand("event Assignment /by 2024-10-10 /from 2024-10-09 /to 2024-10-10")
        );

        String expectedMessage = ErrorMessages.DUE_DATE_NOT_NEEDED;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void launchHelpMenu_tooManyParameters_exceptionThrown() {
        Exception thrown = assertThrows(CommandNotFoundException.class, () ->
                Validator.validateHelpCommand("help me")
        );

        String expectedMessage = ErrorMessages.COMMAND_NOT_FOUND;
        String actualMessage = thrown.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}
