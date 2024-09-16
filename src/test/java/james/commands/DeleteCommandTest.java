package james.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.tasks.Task;
import james.tasks.Todo;
import james.ui.Ui;

class DeleteCommandTest {

    private james.tasklist.TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Task task;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
        ui = mock(Ui.class);
        storage = mock(Storage.class);
        task = mock(Task.class);

        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);

    }

    @Test
    @DisplayName("Delete command should remove a task from the list")
    void deleteCommand_removesTaskFromList() throws IOException, DukeException {
        Todo task2 = new Todo("Task 2");
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(taskList, ui, storage);
        assertEquals(1, taskList.getSize());
        assertEquals(task2.toString(), taskList.getTasks().get(0).toString());
    }

    @Test
    @DisplayName("Delete command with invalid index should throw DukeException")
    void deleteCommand_invalidIndex_throwsDukeException() {
        int invalidIndex = 10; // Assuming there's only 1 task in the list and index 10 is invalid
        DeleteCommand deleteCommand = new DeleteCommand(invalidIndex);

        DukeException exception = assertThrows(DukeException.class, () -> {
            deleteCommand.execute(taskList, ui, storage);
        });

        String expectedMessage = "The task number you are trying to delete does not exist.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test if necessary
    }
}
