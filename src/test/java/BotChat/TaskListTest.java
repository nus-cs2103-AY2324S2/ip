package BotChat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

<<<<<<< HEAD
/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {
    /**
     * Tests the addTask method to ensure a task is successfully added to the task list.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
public class TaskListTest {
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddTask() throws BotChatException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Todo");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

<<<<<<< HEAD
    /**
     * Tests the addTask method when the task list limit is exceeded.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddTaskLimitExceeded() throws BotChatException {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 100; i++) {
            taskList.addTask(new Todo("Task " + i));
        }
        assertThrows(BotChatException.class, () -> taskList.addTask(new Todo("Task 100")));
    }

<<<<<<< HEAD
    /**
     * Tests the deleteTask method to ensure a task is successfully deleted from the task list.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testDeleteTask() throws BotChatException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Todo");
        taskList.addTask(task);
        taskList.deleteTask("delete 1");
        assertEquals(0, taskList.getTasks().size());
    }

<<<<<<< HEAD
    /**
     * Tests the deleteTask method with an invalid task index.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testDeleteInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.deleteTask("delete 1"));
    }

<<<<<<< HEAD
    /**
     * Tests the markTask method with an invalid task index.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testMarkInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.markTask("done 1"));
    }

<<<<<<< HEAD
    /**
     * Tests the unmarkTask method with an invalid task index.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testUnmarkInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.unmarkTask("undone 1"));
    }

<<<<<<< HEAD
    /**
     * Tests the addEventTask method to ensure an event task is successfully added to the task list.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddEventTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addEventTask("event Meeting /from 2024-01-01 /to 2024-01-02");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Event);
    }

<<<<<<< HEAD
    /**
     * Tests the addEventTask method with an invalid event task input.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddInvalidEventTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class,
                () -> taskList.addEventTask("event"));
    }

<<<<<<< HEAD
    /**
     * Tests the addDeadlineTask method to ensure a deadline task is successfully added to the task list.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddDeadlineTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addDeadlineTask("deadline Project /by 2024-01-15");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Deadline);
    }

<<<<<<< HEAD
    /**
     * Tests the addDeadlineTask method with an invalid deadline task input.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddInvalidDeadlineTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.addDeadlineTask("deadline InvalidDeadlineTask /by invalid-date"));
    }

<<<<<<< HEAD
    /**
     * Tests the addTodoTask method to ensure a todo task is successfully added to the task list.
     *
     * @throws BotChatException If an exception occurs during the test.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddTodoTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addTodoTask("todo New Task");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Todo);
    }

<<<<<<< HEAD
    /**
     * Tests the addTodoTask method with an invalid todo task input.
     */
=======
>>>>>>> branch-A-CodingStandard
    @Test
    public void testAddInvalidTodoTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.addTodoTask("todo"));
    }
}