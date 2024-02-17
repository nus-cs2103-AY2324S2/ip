package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class DeleteCommandTest {
    @Test
    public void execute_validIndex_returnsCorrectResponse() throws DukeException {
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
    public void execute_invalidIndex_throwsInvalidTaskIndexException() throws DukeException {
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
    public void execute_emptyIndex_throwsEmptyTaskDescriptionException() throws DukeException {
        // Arrange
        String index = "";
        // Act & Assert
        assertThrows(EmptyTaskDescriptionException.class, () -> new DeleteCommand(index));
    }

    @Test
    public void execute_nonIntegerIndex_throwsInvalidTaskIndexException() throws DukeException {
        // Arrange
        String index = "abc";

        // Act & Assert
        assertThrows(InvalidTaskIndexException.class, () -> new DeleteCommand(index));
    }
}
