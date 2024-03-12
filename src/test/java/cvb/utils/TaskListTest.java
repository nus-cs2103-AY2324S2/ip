package cvb.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cvb.exceptions.ConvoBotException;
import cvb.tasks.Task;
import cvb.tasks.ToDo;

class TaskListTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = mock(Storage.class);
        taskList = new TaskList(storage);
    }

    @Test
    void addTask_sampleToDo_singleTaskAdded() {
        Task task = mock(ToDo.class);
        taskList.add(task);
        assertEquals(1, taskList.size());
    }

    @Test
    void markTaskAsDone_sampleToDo_taskMarkedAsDone() throws ConvoBotException {
        Task task = mock(ToDo.class);
        taskList.add(task);
        taskList.mark(0, true);
        verify(task, times(1)).markAsDone();
    }

    @Test
    void getTaskString_validTaskIndex_taskStringReturned() throws ConvoBotException {
        Task task = mock(ToDo.class);
        taskList.add(task);
        when(task.toString()).thenReturn("[T][ ] Sample Task");
        String taskString = taskList.getTaskString(0);
        assertEquals("[T][ ] Sample Task", taskString);
    }

    @Test
    void deleteTask_validTaskIndex_taskDeleted() throws ConvoBotException {
        Task task = mock(ToDo.class);
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    void markTaskAsDone_invalidIndex_exceptionThrown() {
        assertThrows(ConvoBotException.class, () -> taskList.mark(-1, true));
        assertThrows(ConvoBotException.class, () -> taskList.mark(10, true));
    }

    @Test
    void getTaskString_invalidIndex_exceptionThrown() {
        assertThrows(ConvoBotException.class, () -> taskList.getTaskString(-1));
        assertThrows(ConvoBotException.class, () -> taskList.getTaskString(10));
    }

    @Test
    void deleteTask_invalidIndex_exceptionThrown() {
        assertThrows(ConvoBotException.class, () -> taskList.delete(-1));
        assertThrows(ConvoBotException.class, () -> taskList.delete(10));
    }
}
