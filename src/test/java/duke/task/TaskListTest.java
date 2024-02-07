package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;

    @Test
    public void testAddTask() {
        taskList = new TaskList();

        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);

        Task[] tasks = taskList.getTasks();

        assertEquals(2, taskList.getCounter());
        assertEquals(task1, tasks[0]);
        assertEquals(task2, tasks[1]);
    }

    @Test
    public void testDeleteTask() {
        taskList = new TaskList();

        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        taskList.deleteTask(2);

        Task[] tasks = taskList.getTasks();

        assertEquals(2, taskList.getCounter());
        assertEquals(task1, tasks[0]);
        assertEquals(task3, tasks[1]);
    }

}
