package test.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import main.java.emis.exceptions.EmisException;
import main.java.emis.task.Task;
import main.java.emis.TaskList;
import main.java.emis.task.ToDo;

public class TaskListTest {
    @Test
    public void markAsDone_exceptionThrown() throws EmisException {
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new ToDo("read book"));
        testTasks.add(new ToDo("read magazine"));
        testTasks.add(new ToDo("say hi"));
        TaskList testTasklist = new TaskList(testTasks);
        try {
            testTasklist.markAsDone(0);
            fail(); // the test should not reach this line
        } catch (EmisException e) {
            assertEquals("This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceptionThrown() {
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new ToDo("read book"));
        testTasks.add(new ToDo("read magazine"));
        testTasks.add(new ToDo("say hi"));
        TaskList testTasklist = new TaskList(testTasks);
        try {
            testTasklist.deleteTask(0);
            fail(); // the test should not reach this line
        } catch (EmisException e) {
            assertEquals("This task does not exist!", e.getMessage());
        }
    }
}