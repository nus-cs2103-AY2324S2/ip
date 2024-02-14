package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.TaskList;
import alpa.tasks.Deadline;
import alpa.ui.Ui;
import alpa.utils.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class DeadlineCommandTest {

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
    void executeCommand_validDetails_addsDeadline() throws AlpaException {
        // Arrange
        String details = "submit report /by 14 Feb 3pm";
        DeadlineCommand command = new DeadlineCommand(details);
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 2, 14, 15, 0);

        // Act
        command.executeCommand(mockTaskList, mockUi, mockStorage);

        // Assert
        verify(mockTaskList).addTask(any(Deadline.class));
        verify(mockUi).showAddedTask(any(Deadline.class), anyInt());
        verify(mockStorage).saveTasks(anyList());
    }
}

