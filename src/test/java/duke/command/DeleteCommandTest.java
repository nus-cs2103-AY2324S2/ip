package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.state.ProgramState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    @Test
    public void execute_ValidIndex_ReturnsCorrectResponse() throws DukeException {
        // Arrange
        TaskList taskList = new TaskList();
        ProgramState programState = new ProgramState();
        int index = 1;
        Task task = new Todo("Sample Task");
        taskList.addTask(task);
        DeleteCommand deleteCommand = new DeleteCommand(Integer.toString(index));

        // Act
        String response = deleteCommand.execute(taskList, programState);

        // Assert
        assertEquals("Deleted: " + task + "\nYou now have 0 tasks in the list.", response);
        assertEquals(0, taskList.size());
        assertTrue(() -> programState.isNormal());
    }

    @Test
    public void execute_InvalidIndex_ThrowsInvalidTaskIndexException() throws DukeException {
        // Arrange
        TaskList taskList = new TaskList();
        ProgramState programState = new ProgramState();
        int index = 5;
        DeleteCommand deleteCommand = new DeleteCommand(Integer.toString(index));

        // Act & Assert
        assertThrows(InvalidTaskIndexException.class, () -> deleteCommand.execute(taskList, programState));
        assertTrue(() -> programState.isNormal());
    }

    @Test
    public void execute_EmptyIndex_ThrowsEmptyTaskDescriptionException() throws DukeException {
        // Arrange
        String index = "";
        // Act & Assert
        assertThrows(EmptyTaskDescriptionException.class, () -> new DeleteCommand(index));
    }

    @Test
    public void execute_NonIntegerIndex_ThrowsInvalidTaskIndexException() throws DukeException {
        // Arrange
        String index = "abc";

        // Act & Assert
        assertThrows(InvalidTaskIndexException.class, () -> new DeleteCommand(index));
    }
}