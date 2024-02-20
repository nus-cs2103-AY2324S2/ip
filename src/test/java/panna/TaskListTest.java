package panna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void addTest() throws PannaException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("Task1"));
        assertEquals("Task1", tasks.get(0).taskName);
    }

    @Test
    public void deleteTest() throws PannaException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("Task1"));
        tasks.add(new Task("Task2"));
        tasks.delete(0);
        assertEquals(tasks.get(0).taskName, "Task2");
    }


    @Test
    public void sizeTest() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("test"));
        int size = tasks.size();
        tasks.add(new Task("Test2"));
        int size2 = tasks.size();
        assertEquals(size + 1, size2);
    }

    @Test
    public void printListTest() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test"));
        tasks.add(new Todo("test2"));

        String expected = "1. [T] [ ] test\n"
                          + "2. [T] [ ] test2\n";
        String actual = tasks.printList();
        assertEquals(expected, actual);
    }
}
