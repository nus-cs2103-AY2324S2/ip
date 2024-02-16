package alpa.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alpa.tasks.Deadline;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

class DeadlineCommandTest {

    private TaskList taskList;
    @Mock
    private Ui mockUi;
    @Mock
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskList = new TaskList();
    }

    @Test
    void executeCommand_validDeadlineDetails_correctToFileFormat() throws Exception {

        String details = "finish report /by 15/12/2022";
        DeadlineCommand command = new DeadlineCommand(details);
        String expectedFormat = "D | 0 | finish report | 2022-12-15 23:59";

        command.executeCommand(taskList, mockUi, mockStorage);

        assertEquals(1, taskList.getSize()); // Ensure a deadline was added
        Deadline addedDeadline = (Deadline) taskList.getTasks().get(0);

        // Verify the toFileFormat output matches the expected format
        String actualFormat = addedDeadline.toFileFormat();
        assertEquals(expectedFormat, actualFormat);
    }
}
