package sylvia.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

public class EventCommandTest {
    private EventCommand eventCommand;
    private TaskList taskList;
    private ProgramState programState;

    @BeforeEach
    public void setUp() {
        eventCommand = new EventCommand("");
        taskList = new TaskList();
        programState = new ProgramState();
    }

    @Test
    public void execute_validEventCommand_addsEventToTaskList() throws SylviaException {
        // Arrange
        String body = "Event description /from 2022-01-01 10:00 /to 2022-01-01 12:00";
        eventCommand = new EventCommand(body);

        // Act
        String response = eventCommand.execute(taskList, programState);

        // Assert
        assertEquals(1, taskList.size());
        assertTrue(response.contains("Added:"));
        assertTrue(response.contains("Event description"));
        assertTrue(programState.isNormal());
    }

    @Test
    public void execute_emptyTaskDescription_throwsEmptyTaskDescriptionException() {
        // Arrange
        String body = "/from 2022-01-01 10:00 /to 2022-01-01 12:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyTaskDescriptionException.class, () -> eventCommand.execute(taskList, programState));
    }

    @Test
    public void execute_emptyStartTime_throwsEmptyDateTimeException() {
        // Arrange
        String body = "Event description /to 2022-01-01 12:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyDateTimeException.class, () -> eventCommand.execute(taskList, programState));
    }

    @Test
    public void execute_emptyEndTime_throwsEmptyDateTimeException() {
        // Arrange
        String body = "Event description /from 2022-01-01 10:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyDateTimeException.class, () -> eventCommand.execute(taskList, programState));
    }
}
