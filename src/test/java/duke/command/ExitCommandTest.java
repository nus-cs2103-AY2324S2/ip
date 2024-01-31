package duke.command;

import duke.task.TaskList;
import duke.state.ProgramState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void execute_ExitCommand_SetsProgramStateToExit() {
        // Arrange
        ExitCommand exitCommand = new ExitCommand();
        TaskList taskList = new TaskList();
        ProgramState programState = new ProgramState();

        // Act
        String response = exitCommand.execute(taskList, programState);

        // Assert
        assertEquals("Cya!", response);
        assertEquals(true, programState.isExit());
    }
}