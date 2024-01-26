import duke.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(DukeException.class, todoWithEmptyDescription, "Exception was expected for empty todo description");
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
}
