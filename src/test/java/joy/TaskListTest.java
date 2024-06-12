package joy;
import joy.task.Task;
import joy.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit test cases to verify the functionality of the TaskList class.
 * It tests various methods of the TaskList class, for manipulating tasks from the list of tasks.
 */
public class TaskListTest {
    private static class UiMock extends Ui {
        private String output;

        public String showError(String str) {

            output = " Error: " + str;
            return output;
        }

        public String deleteMessage(String str) {
            output = str;
            return output;
        }
        @Override
        public String showMessage(String str) {
            output = str;
            return output;
        }

        public String getOutput() {
            return output;
        }
    }
    @Test
    public void addTasks_validTask_addedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Sample Task");
        taskList.addTasks(todo);
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().contains(todo));
    }

    @Test
    public void markTasks_validIndex_taskMarkedAsDone() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Sample Task");
        taskList.addTasks(todo);
        UiMock uiMock = new UiMock();
        taskList.markTasks(1, uiMock);
        assertEquals("X", todo.getStatusIcon());

    }

    @Test
    public void markTasks_invalidIndex_showErrorMessage() {
        TaskList taskList = new TaskList();
        UiMock uiMock = new UiMock();
        taskList.markTasks(1, uiMock);
        assertTrue(uiMock.getOutput().contains("OOPS!!! The task number is out of bounds. "
                + "Please provide a valid task number."));
    }

    @Test
    public void removeTasks_validIndex_taskRemovedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Sample Task");
        taskList.addTasks(todo);
        UiMock uiMock = new UiMock();
        taskList.removeTasks(1, uiMock);
        assertEquals(0, taskList.getTasks().size());

    }

    @Test
    public void removeTasks_invalidIndex_showErrorMessage() {
        TaskList taskList = new TaskList();
        UiMock uiMock = new UiMock();
        taskList.removeTasks(1, uiMock);
        assertTrue(uiMock.getOutput().contains("Error: OOPS!!! The task number is out of bounds."));
    }

    @Test
    public void listTasks_emptyTaskList_showNoTasksMessage() {
        TaskList taskList = new TaskList();
        UiMock uiMock = new UiMock();
        taskList.listTasks(uiMock);
        assertTrue(uiMock.getOutput().contains("There are no tasks in the list."));
    }

    @Test
    public void listTasks_nonEmptyTaskList_showTasksSuccessfully() {
        TaskList taskList = new TaskList();
        taskList.addTasks(new Todo("Sample Task 1"));
        //taskList.addTasks(new Deadline("Sample Task 2", "2022-12-31"));
        UiMock uiMock = new UiMock();
        taskList.listTasks(uiMock);
        //assertTrue(uiMock.getOutput().contains("Here are the tasks in your list:"));
        assertTrue(uiMock.getOutput().contains("1. [T][ ] Sample Task 1"));
        //assertTrue(uiMock.getOutput().contains("2. [D][ ] Sample Task 2 (by: Dec 31 2022)"));
    }
}
