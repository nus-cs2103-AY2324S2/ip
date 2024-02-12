package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {

    @Test
    public void parseDelete_validInput_correctDeleteTask() throws DukeException {
        Parser parser = new Parser("delete 2");
        int result = parser.parseDelete();
        assertEquals(1, result);
    }

    @Test
    public void parseMark_validInput_correctMarkTask() throws DukeException {
        Parser parser = new Parser("mark 3");
        int result = parser.parseMark();
        assertEquals(2, result);
    }

    @Test
    public void parseUnMark_validInput_correctUnmarkTask() throws DukeException {
        Parser parser = new Parser("unmark 4");
        int result = parser.parseUnmark();
        assertEquals(3, result);
    }

    @Test
    public void parseEvent_validInput_correctEventTask() throws DukeException {
        Parser parser = new Parser("event Meeting /from 2022-02-14 13:00 /to 2022-02-14 14:00");
        Event result = parser.parseEvent();
        assertEquals(new Event("Meeting", "2022-02-14 13:00", "2022-02-14 14:00"), result);
    }

    @Test
    public void parseTodo_validInput_correctTodoTask() throws DukeException {
        Parser parser = new Parser("todo Borrow Book");
        Todo result = parser.parseTodo();
        assertEquals(new Todo("Borrow Book"), result);
    }

    @Test
    public void parseDeadline_validInput_correctDeadlineTask() throws DukeException {
        Parser parser = new Parser("deadline Assignment /by 2022-03-01 23:59");
        Deadline result = parser.parseDeadline();
        assertEquals(new Deadline("Assignment", "2022-03-01 23:59"), result);
    }

    @Test
    public void parseEvent_invalidFormat_throwsDukeExceptionInvalidEventFormat() {
        Parser parser = new Parser("event Meeting /from 2022-02-28 18:00 /to blah");
        assertThrows(DukeException.class, parser::parseEvent);
    }

    @Test
    public void parseTodo_invalidFormat_throwsDukeExceptionInvalidTodoFormat() {
        Parser parser = new Parser("todo ");
        assertThrows(DukeException.class, parser::parseTodo);
    }

    @Test
    public void parseDeadline_invalidFormat_throwsDukeExceptionInvalidDeadlineFormat() {
        Parser parser = new Parser("deadline Assignment /by blah");
        assertThrows(DukeException.class, parser::parseDeadline);
    }
}
