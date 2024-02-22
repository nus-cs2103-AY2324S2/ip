package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void add_task_test() {
        TaskList tasks = new TaskList();
        try {
            Task todoTask = new Todo("todo 1");
            tasks.addTask(todoTask);
            assertEquals(todoTask, tasks.getTask(1));
        } catch (TodoException e) {
            System.out.println(e.getMessage());
        }
        Task eventTask = new Task("event go movie /from 6pm /to 830pm");
        Task deadlineTask = new Task("deadline quiz /by 3pm");
        tasks.addTask(eventTask);
        tasks.addTask(deadlineTask);
        assertEquals(eventTask, tasks.getTask(2));
        assertEquals(deadlineTask, tasks.getTask(3));
    }

    @Test
    public void delete_task_test() {
        TaskList tasks = new TaskList();
        try {
            Task todoTask = new Todo("todo 1");
            tasks.addTask(todoTask);
        } catch (TodoException e) {
            System.out.println(e.getMessage());
        }
        Task eventTask = new Task("event go movie /from 6pm /to 830pm");
        Task deadlineTask = new Task("deadline quiz /by 3pm");
        tasks.addTask(eventTask);
        tasks.addTask(deadlineTask);
        tasks.deleteTask(1);
        assertEquals(tasks.getSize(), 2);
        assertEquals(eventTask, tasks.getTask(1));
        assertEquals(deadlineTask, tasks.getTask(2));
    }
}
