package duke;

import duke.command.TodoCommand;
import duke.common.TaskList;
import duke.storage.Storage;
import duke.task.Todo;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TodoTest {
    @Test
    public void testToDo() {
        Storage mockStorage = mock(Storage.class);
        Ui mockUi = mock(Ui.class);
        TaskList mockTaskList = mock(TaskList.class);

        String description = "hello";
        new TodoCommand(description).execute(mockTaskList, mockUi, mockStorage);

        verify(mockTaskList).addTask(argThat(task -> {
            assertTrue(task instanceof Todo);
            assertEquals(task.getDescription(), description);
            return true;
        }));

        verify(mockUi).showNewTask(argThat(task -> {
            assertTrue(task instanceof Todo);
            assertEquals(task.getDescription(), description);
            return true;
        }), eq(mockTaskList));
    }
}
