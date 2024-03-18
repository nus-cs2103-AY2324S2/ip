package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;

class DeleteTaskTest {
    private TaskList taskList;
    private Command deleteTaskCommand;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        deleteTaskCommand = new DeleteTask(taskList, 0);
    }

    @Test
    void execute_validIndex_taskRemoved() {
        Task task = new Task("Sample Task");
        taskList.add(task);

        assertDoesNotThrow(() -> deleteTaskCommand.execute());

        assertEquals(0, taskList.size());
    }

    @Test
    void execute_invalidIndex_throwsInvalidIndexException() {
        assertThrows(InvalidIndexException.class, () -> deleteTaskCommand.execute());
    }
}
