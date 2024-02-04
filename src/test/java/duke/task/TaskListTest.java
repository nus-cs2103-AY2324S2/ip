package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    @Test
    public void addTask_validInput_addsTaskSuccessfully() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read Book");
        String result = taskList.addTask(task);
        assertEquals("Got it. I've added this task:\n   "
                + task
                + "\n Now you have 1 task(s) in the list.", result);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void deleteTask_validIndex_deletesTaskSuccessfully() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read Book");
        taskList.addTask(task);

        String result = taskList.deleteTask(0);

        assertEquals("Noted. I've removed this task:\n   "
                + task
                + "\n Now you have 0 task(s) in the list.", result);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void deleteTask_invalidIndex_throwsDukeException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DukeException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void printList_emptyList_returnsNoTasksMessage() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals("There are no tasks in the list.", taskList.printList());
    }

    @Test
    public void markTask_validIndex_marksAsDoneSuccessfully() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read Book");
        taskList.addTask(task);

        String result = taskList.markTask(0);

        assertEquals("Nice! I've marked this task as done:\n   " + task, result);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void markTask_invalidIndex_throwsDukeException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DukeException.class, () -> taskList.markTask(0));
    }

    @Test
    public void unmarkTask_validIndex_marksAsNotDoneSuccessfully() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read Book");
        task.setDone(true);
        taskList.addTask(task);

        String result = taskList.unmarkTask(0);

        assertEquals("OK, I've marked this task as not done yet:\n   " + task, result);
        assertFalse(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void unmarkTask_invalidIndex_throwsDukeException() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertThrows(DukeException.class, () -> taskList.unmarkTask(0));
    }

}
