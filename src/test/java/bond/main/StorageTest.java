package bond.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bond.task.DeadlineTask;
import bond.task.EventTask;
import bond.task.Task;
import bond.task.TodoTask;

/**
 * Tests for the Parser class.
 *
 * @author Benny Loh
 * @version 0.1
 */
public class StorageTest {

    @Test
    public void parseAndAddTask_invalidDateFormatDeadline_exceptionThrown() {
        String taskFromFile = "[D] [ ] sleep (by: 29 Feb 2024 10pm)";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            assert false;
        } catch (BondException e) {
            assertEquals("Give the DATE(s) in the CORRECT FORMAT!!!", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parseAndAddTask_invalidDateFormatEvent_exceptionThrown() {
        String taskFromFile = "[E] [ ] sleep (from: 29 Feb 2024 10pm to: Mar 01 2024 10am)";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            assert false;
        } catch (BondException e) {
            assertEquals("Give the DATE(s) in the CORRECT FORMAT!!!", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parseAndAddTask_invalidTask_exceptionThrown() {
        String taskFromFile = "[A] [ ] sleep (from: 29 Feb 2024 10pm to: Mar 01 2024 10am)";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            assert false;
        } catch (BondException e) {
            assertEquals("I COULD NOT LOAD your tasks!!!", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parseAndAddTask_todo_success() {
        String taskFromFile = "[T] [ ] task 1";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            TodoTask task = (TodoTask) tasks.get(0);
            assertEquals(task.toString(), taskFromFile);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parseAndAddTask_deadline_success() {
        String taskFromFile = "[D] [ ] sleep (by: Feb 29 2024 10pm)";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            DeadlineTask task = (DeadlineTask) tasks.get(0);
            assertEquals(task.toString(), taskFromFile);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parseAndAddTask_event_success() {
        String taskFromFile = "[E] [ ] sleep (from: Feb 29 2024 10pm to: Mar 01 2024 10am)";
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            storage.readAndAddTask(taskFromFile, tasks);
            EventTask task = (EventTask) tasks.get(0);
            assertEquals(task.toString(), taskFromFile);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
