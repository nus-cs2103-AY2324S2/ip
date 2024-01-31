package duke.command;

import duke.DukeException;
import duke.command.EmptyDateTimeException;
import duke.command.EmptyTaskDescriptionException;
import duke.state.ProgramState;
import duke.task.Event;
import duke.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void execute_ValidEventCommand_AddsEventToTaskList() throws DukeException {
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
    public void execute_EmptyTaskDescription_ThrowsEmptyTaskDescriptionException() {
        // Arrange
        String body = "/from 2022-01-01 10:00 /to 2022-01-01 12:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyTaskDescriptionException.class, () -> eventCommand.execute(taskList, programState));
    }

    @Test
    public void execute_EmptyStartTime_ThrowsEmptyDateTimeException() {
        // Arrange
        String body = "Event description /to 2022-01-01 12:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyDateTimeException.class, () -> eventCommand.execute(taskList, programState));
    }

    @Test
    public void execute_EmptyEndTime_ThrowsEmptyDateTimeException() {
        // Arrange
        String body = "Event description /from 2022-01-01 10:00";
        eventCommand = new EventCommand(body);

        // Act & Assert
        assertThrows(EmptyDateTimeException.class, () -> eventCommand.execute(taskList, programState));
    }
}