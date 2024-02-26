package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Borrow book");
        tasks.addTask(task);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList();
        Task task1 = new ToDo("Borrow book");
        Task task2 = new ToDo("Return book");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.deleteTask(1);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testDisplayTasks() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("Borrow book");
        tasks.addTask(task);
        String output = tasks.displayTasks();
        assertEquals("1.[T][ ] Borrow book\n", output);
    }
}
