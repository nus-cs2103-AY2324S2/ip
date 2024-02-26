package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for TaskList class.
 */
public class TaskListTest {

    /**
     * Tests the addTask method of TaskList.
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Task 1");
        taskList.addTask(task);
        assertEquals(1, TaskList.getTaskNum());
        assertEquals(task, TaskList.getTasks()[0]);
    }

    /**
     * Tests the removeTask method of TaskList.
     */
    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.removeTask(0);
        assertEquals(1, TaskList.getTaskNum());
        assertEquals(task2, TaskList.getTasks()[0]);
    }
}
