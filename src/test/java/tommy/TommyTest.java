package tommy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tommy.task.Task;
import tommy.task.TaskList;
import tommy.task.Todo;

public class TommyTest {

    /*
    Test for date formatter
    Accepted forms :
    yyyy-MM-dd
    dd-MM-YYYY
    dd/MM/yyyy
     */
    @Test
    public void formatDateTest() {
        assertEquals("Dec 31 3000", Parser.formatDate("3000-12-31"));
        assertEquals("Jan 1 0001", Parser.formatDate("0001-01-01"));
        assertEquals("Dec 31 9999", Parser.formatDate("9999-12-31"));

        assertEquals("May 5 2000", Parser.formatDate("05-05-2000"));
        assertEquals("Jan 1 0001", Parser.formatDate("01-01-0001"));
        assertEquals("Dec 31 9999", Parser.formatDate("31-12-9999"));

        assertEquals("Jan 1 2003", Parser.formatDate("01/01/2003"));
        assertEquals("Jan 1 0001", Parser.formatDate("01/01/0001"));
        assertEquals("Dec 31 9999", Parser.formatDate("31/12/9999"));

    }

    // test getSize() function in TaskList
    @Test
    public void getTaskListSizeTest() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.getSize());

        for (int i = 0; i < 50; i++) {
            taskList.addTask(new Todo("blah"));
        }
        assertEquals(50, taskList.getSize());

        for (int i = 0; i < 50; i++) {
            taskList.addTask(new Todo("blah"));
        }

        assertEquals(100, taskList.getSize());
    }

    // test mark task function
    @Test
    public void markTaskTest() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 50; i++) {
            taskList.addTask(new Todo("blah"));
        }

        // marking task at position 20
        taskList.markTask(20);
        Task taskToTest = taskList.getTaskAtPosition(20);

        assertEquals("[X]", taskToTest.getStatusIcon());

        // marking task at position 1
        taskList.markTask(1);
        taskToTest = taskList.getTaskAtPosition(1);

        assertEquals("[X]", taskToTest.getStatusIcon());

        // marking task at position 50
        taskList.markTask(50);
        taskToTest = taskList.getTaskAtPosition(50);

        assertEquals("[X]", taskToTest.getStatusIcon());
    }

    @Test
    public void unmarkTaskTest() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 50; i++) {
            taskList.addTask(new Todo("blah"));
            taskList.markTask(i + 1);
        }

        // marking task at position 20
        taskList.unmarkTask(20);
        Task taskToTest = taskList.getTaskAtPosition(20);

        assertEquals("[ ]", taskToTest.getStatusIcon());

        // marking task at position 1
        taskList.unmarkTask(1);
        taskToTest = taskList.getTaskAtPosition(1);

        assertEquals("[ ]", taskToTest.getStatusIcon());

        // marking task at position 50
        taskList.unmarkTask(50);
        taskToTest = taskList.getTaskAtPosition(50);

        assertEquals("[ ]", taskToTest.getStatusIcon());
    }
}
