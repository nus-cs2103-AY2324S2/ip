package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void findTask_noMatchingTask_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        //Test Case 1
        assertEquals("", tasks.findTask("fly"));
        //Test Case 2
        assertEquals("", tasks.findTask("booook"));
        //Test Case 3
        assertEquals("", tasks.findTask("lol"));
    }
    @Test
    public void findTask_oneMatchingTask_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        // Test case 1
        String expectedText = "\t1. " + todoTask + "\n";
        assertEquals(expectedText, tasks.findTask("read"));
        // Test case 2
        expectedText = "\t1. " + eventTask + "\n";
        assertEquals(expectedText, tasks.findTask("movie"));
        //Test case 3
        expectedText = "\t1. " + deadlineTask + "\n";
        assertEquals(expectedText, tasks.findTask("quiz"));
    }
    @Test
    public void getTask_oneTaskInTaskList_success() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("todo read book");
        tasks.addTask(todoTask);
        Task eventTask = new Task("event movie date /from 23 02 2024 1400 /to 23 02 2024 1630");
        tasks.addTask(eventTask);
        Task deadlineTask = new Task("deadline quiz /by 23 02 2024 2359");
        tasks.addTask(deadlineTask);
        // Test case 1
        assertEquals(deadlineTask, tasks.getTask(3));
        // Test case 2
        assertEquals(todoTask, tasks.getTask(1));
        // Test case 3
        assertEquals(eventTask, tasks.getTask(2));
    }
}
