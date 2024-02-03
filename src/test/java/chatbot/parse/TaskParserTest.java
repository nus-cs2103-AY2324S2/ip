package chatbot.parse;

import static chatbot.parse.TaskParser.parseTaskListItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbot.task.Task;
import chatbot.task.exception.InvalidTaskStringException;

public class TaskParserTest {
    @Test
    public void parseTaskListItem_validFormat_expectedBehaviour() {
        try {
            Task task = parseTaskListItem("1. [T][ ] test cases");
            assertEquals(task.toString(), "[T][ ] test cases");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem("1. [T][X] test cases");
            assertEquals(task.toString(), "[T][X] test cases");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem(" 1.  [T] [ ]  test cases ");
            assertEquals(task.toString(), "[T][ ] test cases");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem("1.[T][ ][ ]");
            assertEquals(task.toString(), "[T][ ] [ ]");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem("0. [D][ ] do deadline (by: today)");
            assertEquals(task.toString(), "[D][ ] do deadline (by: today)");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem("999. [E][X] go to event (from: 2pm to: 4pm)");
            assertEquals(task.toString(), "[E][X] go to event (from: 2pm to: 4pm)");
        } catch (InvalidTaskStringException e) {
            fail();
        }

        try {
            Task task = parseTaskListItem("1. [E][ ] go from: home to: venue (from: 2pm to: 4pm)");
            assertEquals(task.toString(), "[E][ ] go from: home to: venue (from: 2pm to: 4pm)");
        } catch (InvalidTaskStringException e) {
            fail();
        }
    }

    @Test
    public void parseTaskListItem_invalidFormat_exceptionThrown() {
        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("1) [T][ ] todo"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("1. [?][ ] todo"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("[T][ ] todo"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem(". [T][ ] todo"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("1. [D][ ] deadline"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("1. [D][ ] deadline (from: today)"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem("1. [E][ ] event"));

        assertThrows(InvalidTaskStringException.class, () ->
                parseTaskListItem(""));
    }
}
