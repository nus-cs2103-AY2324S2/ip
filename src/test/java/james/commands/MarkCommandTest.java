package james.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasks.Task;
import james.ui.Ui;


class MarkCommandTest {

    private james.tasklist.TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Task task;

    @BeforeEach
    void setUp() {
        taskList = new james.tasklist.TaskList(new ArrayList<>());
        ui = mock(Ui.class);
        storage = mock(Storage.class);
        task = mock(Task.class);

        // Add the task to the task list and ensure it starts as not done
        taskList.addTask(task);
        when(task.isDone()).thenReturn(false);
    }

    @Test
    @DisplayName("Mark command should mark a task as done")
    void markCommand_marksTaskAsDone() throws IOException, DukeException {
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(taskList, ui, storage);

        verify(task, times(1)).markAsDone();
        verify(storage, times(1)).save(anyList());
        verify(ui, times(1)).showMarkedTask(task);
    }

    @Test
    @DisplayName("Mark command with invalid index should throw DukeException")
    void markCommand_invalidIndex_throwsDukeException() {
        int invalidIndex = 10;
        MarkCommand markCommand = new MarkCommand(invalidIndex);

        DukeException exception = assertThrows(DukeException.class, () -> {
            markCommand.execute(taskList, ui, storage);
        });

        String expectedMessage = "The task number you are trying to access does not exist.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @AfterEach
    void tearDown() {
        // Clean up after each test
    }
}
