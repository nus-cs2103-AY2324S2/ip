package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private TaskList list;

    @BeforeEach
    void setUpEmptyList() {
        list = new TaskList();
        // Mocking Ui methods or ensure that Ui methods can run in a test environment
    }

    @Test
    public void addTodo_validInput_successfulAddition() throws DukeException {
        String command = "todo read magazine";
        Parser.addTodo(list, command, command.split(" "));
        assertEquals(1, list.size(), "TaskList should have one task after adding todo");
        Task addedTask = list.get(0);
        assertTrue(addedTask instanceof Todo, "Added task should be of type Todo");
        assertEquals("[T][ ] read magazine", addedTask.toString(), "Todo task description should match");
    }

    @Test
    public void checkCmd_testInvalidDeadlineCommand_exceptionThrown() {
        String command = "deadline read book";
        Exception exception = assertThrows(DukeException.class, () -> {
            Ui.checkCmd(list, command);
        });
        String expectedMessage = " Sorry buddy, the date of a deadline cannot be empty :(";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}