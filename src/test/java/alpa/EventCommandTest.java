package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.Event;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class EventCommandTest {

    @Mock
    private TaskList mockTaskList;
    @Mock
    private Ui mockUi;
    @Mock
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeCommand_validEventDetails_addsEvent() throws AlpaException {
        // Arrange
        String details = "team meeting /from 2 Dec 10 AM /to 11 AM";
        EventCommand command = new EventCommand(details);
        LocalDateTime startDateTime = LocalDateTime.of(2024, 12, 2, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 12, 2, 11, 0);

        // Act
        command.executeCommand(mockTaskList, mockUi, mockStorage);

        // Assert
        verify(mockTaskList).addTask(argThat(argument -> 
            argument instanceof Event && 
            ((Event) argument).getStartDateTime().equals(startDateTime) &&
            ((Event) argument).getEndDateTime().equals(endDateTime) &&
            ((Event) argument).getDescription().equals("team meeting")
        ));
        verify(mockUi).showAddedTask(any(Event.class), anyInt());
        verify(mockStorage).saveTasks(anyList());
    }
}

