package alpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alpa.commands.EventCommand;
import alpa.tasks.Event;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

class EventCommandTest {

    private TaskList taskList;
    @Mock
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new TaskList();
    }

    @Test
    void executeCommand_validEventDetails_correctToFileFormat() throws Exception {
        // Arrange
        String details = "project meeting /from 12 Feb 2 PM /to 3 PM";
        EventCommand command = new EventCommand(details);
        String expectedFormat = "E | 0 | project meeting | 2024-02-12 14:00 - 2024-02-12 15:00";

        // Act
        command.executeCommand(taskList, mockStorage);

        // Assert
        assertEquals(1, taskList.getSize()); // Ensure an event was added
        Event addedEvent = (Event) taskList.getTasks().get(0);

        // Format the start and end times as expected in the toFileFormat output
        String actualFormat = addedEvent.toFileFormat();
        assertEquals(expectedFormat, actualFormat);
    }
}
