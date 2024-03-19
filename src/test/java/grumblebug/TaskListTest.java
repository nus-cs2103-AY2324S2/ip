package grumblebug;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addTest() {
        String testStr = "Hello";
        TaskList taskList = new TaskList();
        taskList.add(new Todo(false, testStr));
        String expected = taskList.get(1).description;
        assertEquals(testStr, expected);
    }

    @Test
    public void sizeIncreasesTest() {
        String testStr = "Hello";
        TaskList taskList = new TaskList();
        int ini = taskList.size();
        taskList.add(new Todo(false, testStr));
        int then = taskList.size();
        assertEquals(ini + 1, then);
    }

    @Test
    public void correctSizeTest() {
        // empty list size should be 0
        TaskList taskList = new TaskList();
        assertEquals(taskList.size(), 0);

        // adding first task
        String testStr = "Hey there";
        LocalDate date = LocalDate.of(2023, 1, 8);
        taskList.add(new Deadline(true, testStr, date));
        assertEquals(taskList.size(), 1);

        // adding second task
        testStr = "Hello there";
        date = LocalDate.of(2022, 10, 18);
        taskList.add(new Deadline(true, testStr, date));
        assertEquals(taskList.size(), 2);

        // deleting task
        taskList.delete(1);
        assertEquals(taskList.size(), 1);
    }
}
