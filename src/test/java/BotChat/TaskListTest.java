package BotChat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void testAddTask() throws BotChatException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Todo");
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void testAddTaskLimitExceeded() throws BotChatException {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 100; i++) {
            taskList.addTask(new Todo("Task " + i));
        }
        assertThrows(BotChatException.class, () -> taskList.addTask(new Todo("Task 100")));
    }

    @Test
    public void testDeleteTask() throws BotChatException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test Todo");
        taskList.addTask(task);
        taskList.deleteTask("delete 1");
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    public void testDeleteInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.deleteTask("delete 1"));
    }

    @Test
    public void testMarkInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.markTask("done 1"));
    }

    @Test
    public void testUnmarkInvalidTaskIndex() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.unmarkTask("undone 1"));
    }

    @Test
    public void testAddEventTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addEventTask("event Meeting /from 2024-01-01 /to 2024-01-02");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Event);
    }

    @Test
    public void testAddInvalidEventTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class,
                () -> taskList.addEventTask("event"));
    }

    @Test
    public void testAddDeadlineTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addDeadlineTask("deadline Project /by 2024-01-15");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Deadline);
    }

    @Test
    public void testAddInvalidDeadlineTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.addDeadlineTask("deadline InvalidDeadlineTask /by invalid-date"));
    }

    @Test
    public void testAddTodoTask() throws BotChatException {
        TaskList taskList = new TaskList();
        taskList.addTodoTask("todo New Task");
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Todo);
    }

    @Test
    public void testAddInvalidTodoTask() {
        TaskList taskList = new TaskList();
        assertThrows(BotChatException.class, () -> taskList.addTodoTask("todo"));
    }
}