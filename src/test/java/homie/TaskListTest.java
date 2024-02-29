package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void findTask_noMatchingTask_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        assertEquals(1, tasks.getSize());
        assertEquals(todoTask, tasks.getTask(1));
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        assertEquals(2, tasks.getSize());
        assertEquals(eventTask, tasks.getTask(2));
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        assertEquals(3, tasks.getSize());
        assertEquals(deadlineTask, tasks.getTask(3));
        assertEquals("", tasks.findTask("fly"));
    }
    @Test
    public void findTask_oneMatchingTask_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        assertEquals(1, tasks.getSize());
        assertEquals(todoTask, tasks.getTask(1));
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        assertEquals(2, tasks.getSize());
        assertEquals(eventTask, tasks.getTask(2));
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        assertEquals(3, tasks.getSize());
        assertEquals(deadlineTask, tasks.getTask(3));
        String expectedText = "\t1. " + todoTask + "\n";
        assertEquals(expectedText, tasks.findTask("read"));
    }
    @Test
    public void getTask_oneTaskInTaskList_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        assertEquals(1, tasks.getSize());
        assertEquals(todoTask, tasks.getTask(1));
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        assertEquals(2, tasks.getSize());
        assertEquals(eventTask, tasks.getTask(2));
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        assertEquals(3, tasks.getSize());
        assertEquals(deadlineTask, tasks.getTask(3));
    }
}
