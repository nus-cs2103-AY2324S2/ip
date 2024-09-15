import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.Task;
import duke.TaskList;

class ParserTest {
    private TaskList list;

    @BeforeEach
    void setUp() {
        list = new TaskList();
        // Mocking Ui methods or ensure that Ui methods can run in a test environment
    }

    @Test
    void handleTodo_validInput_addsTodo() {
        try {
            Parser.handleTodo(list, "todo read book");
            assertEquals(1, list.size());
            Task addedTask = list.get(0);
            assertEquals("read book", addedTask.getDescription());
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    void handleTodo_emptyDescription_throwsDukeException() {
        Executable todoWithEmptyDescription = () -> Parser.handleTodo(list, "todo");
        assertThrows(DukeException.class, todoWithEmptyDescription,
                "Exception was expected for empty todo description");
    }

    @Test
    void handleTodo_validInput_addsDeadline() {
        try {
            Parser.handleDeadline(list, "deadline read book /by 2020-02-02");
            assertEquals(1, list.size());
            Deadline addedTask = (Deadline) list.get(0);
            assertEquals("read book", addedTask.getDescription());
            assertEquals("2020-02-02", addedTask.getDate());
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    void handleDeadline_emptyDescription_throwsDukeException() {
        Executable todoWithEmptyDescription = () -> Parser.handleDeadline(list, "deadline");
        assertThrows(DukeException.class, todoWithEmptyDescription,
                "Exception was expected for empty todo description");
    }

    @Test
    void handleTodo_validInput_addsEvent() {
        try {
            Parser.handleEvent(list, "event read book /from 2020-02-02 23:00 /to 2020-02-03 23:15");
            assertEquals(1, list.size());
            Event addedTask = (Event) list.get(0);
            assertEquals("read book", addedTask.getDescription());
            assertEquals("2020-02-02T23:00", addedTask.getFromDate());
            assertEquals("2020-02-03T23:15", addedTask.getToDate());
        } catch (DukeException e) {
            fail("Exception should not be thrown for valid input");
        }
    }

    @Test
    void handleEvent_emptyDescription_throwsDukeException() {
        Executable todoWithEmptyDescription = () -> Parser.handleDeadline(list, "event");
        assertThrows(DukeException.class, todoWithEmptyDescription,
                "Exception was expected for empty todo description");
    }
}
