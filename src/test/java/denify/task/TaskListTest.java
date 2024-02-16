package denify.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import denify.exception.DenifyException;
import denify.ui.Ui;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    @Test
    public void addTask_validInput_addsTaskSuccessfully() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read Book");
        taskList.addTask(task);
        Ui testUi = new Ui();
        String result = testUi.showAddTaskMessage(task, taskList.getTasks().size());
        assertEquals("Got it. I've added this task:\n   "
                + task
                + "\n Now you have 1 task(s) in the list.", result);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void deleteTask_validIndex_deletesTaskSuccessfully() throws DenifyException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task t1 = new Todo("Read Book");
        taskList.addTask(t1);
        Task t2 = taskList.deleteTask(0);
        Ui testUi = new Ui();
        String result = testUi.showDeleteTaskMessage(t2, taskList.getTasks().size());
        assertEquals("Noted. I've removed this task:\n   "
                + t1
                + "\n Now you have 0 task(s) in the list.", result);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void deleteTask_invalidIndex_throwsDenifyException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DenifyException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void printList_emptyList_returnsNoTasksMessage() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui testUi = new Ui();
        String result = testUi.showAllTasks(taskList.getTasks());
        assertEquals("There are no tasks in the list.", result);
    }

    @Test
    public void markTask_validIndex_marksAsDoneSuccessfully() throws DenifyException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task t1 = new Todo("Read Book");
        taskList.addTask(t1);
        Task t2 = taskList.markTask(0);
        Ui testUi = new Ui();
        String result = testUi.showMarkTaskMessage(t2);
        assertEquals("Nice! I've marked this task as done:\n   " + t1, result);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void markTask_invalidIndex_throwsDenifyException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DenifyException.class, () -> taskList.markTask(0));
    }

    @Test
    public void unmarkTask_validIndex_marksAsNotDoneSuccessfully() throws DenifyException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task t1 = new Todo("Read Book");
        taskList.addTask(t1);
        Task t2 = taskList.unmarkTask(0);
        Ui testUi = new Ui();
        String result = testUi.showUnmarkTaskMessage(t2);
        assertEquals("OK, I've marked this task as not done yet:\n   " + t1, result);
        assertFalse(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void unmarkTask_invalidIndex_throwsDenifyException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DenifyException.class, () -> taskList.unmarkTask(0));
    }

}
