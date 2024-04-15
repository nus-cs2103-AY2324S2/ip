package chatbot.parse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbot.action.Action;
import chatbot.action.AddDeadlineAction;
import chatbot.action.AddEventAction;
import chatbot.action.AddTodoAction;
import chatbot.action.ByeAction;
import chatbot.action.DeleteAction;
import chatbot.action.FindAction;
import chatbot.action.ListAction;
import chatbot.action.MarkAction;
import chatbot.action.UnmarkAction;
import chatbot.action.exception.ActionException;
import chatbot.action.exception.MissingArgumentException;
import chatbot.action.exception.MissingArgumentValueException;
import chatbot.action.exception.UnexpectedArgumentValueException;
import chatbot.action.exception.UnrecognizedArgumentException;
import chatbot.action.exception.UnrecognizedCommandException;

public class InputParserTest {
    @Test
    public void getParsedInput_empty_exceptionThrown() {
        assertThrows(UnrecognizedCommandException.class, () ->
                InputParser.getParsedInput(""));
    }

    @Test()
    public void getParsedInput_action_expectedBehaviour() {
        try {
            Action action = InputParser.getParsedInput("bye");
            assertTrue(action instanceof ByeAction);
        } catch (ActionException e) {
            fail("ActionException should not be thrown!");
        }

        try {
            Action action = InputParser.getParsedInput("list");
            assertTrue(action instanceof ListAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("todo work");
            assertTrue(action instanceof AddTodoAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("deadline work /by Sunday");
            assertTrue(action instanceof AddDeadlineAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("event work /from Sunday /to Monday");
            assertTrue(action instanceof AddEventAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("unmark 1");
            assertTrue(action instanceof UnmarkAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("mark 1");
            assertTrue(action instanceof MarkAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("delete 1");
            assertTrue(action instanceof DeleteAction);
        } catch (ActionException e) {
            fail();
        }

        try {
            Action action = InputParser.getParsedInput("find something");
            assertTrue(action instanceof FindAction);
        } catch (ActionException e) {
            fail();
        }
    }

    @Test()
    public void getParsedInput_unexpectedArgument_exceptionThrown() {
        assertThrows(UnexpectedArgumentValueException.class, () ->
                InputParser.getParsedInput("list 10"));
    }

    @Test()
    public void getParsedInput_unrecognizedArgument_exceptionThrown() {
        assertThrows(UnrecognizedArgumentException.class, () ->
                InputParser.getParsedInput("todo work /by 10pm"));

        assertThrows(UnrecognizedArgumentException.class, () ->
                InputParser.getParsedInput("deadline work /from 10pm"));
    }

    @Test()
    public void getParsedInput_missingArgument_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                InputParser.getParsedInput("event work"));

        assertThrows(MissingArgumentException.class, () ->
                InputParser.getParsedInput("deadline work"));
    }

    @Test()
    public void getParsedInput_missingArgumentValue_exceptionThrown() {
        assertThrows(MissingArgumentValueException.class, () ->
                InputParser.getParsedInput("event work /from /to 4pm"));

        assertThrows(MissingArgumentValueException.class, () ->
                InputParser.getParsedInput("deadline /by today"));

        assertThrows(MissingArgumentValueException.class, () ->
                InputParser.getParsedInput("deadline homework /by "));
    }
}
