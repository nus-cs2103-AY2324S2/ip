package task;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import command.Command;

import exceptions.DukeException;

import utilities.Parser;
import utilities.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void get_validTask_success() {
        Task expected = new Task("dummy");
        ArrayList<Task> taskList1 = new ArrayList<>();
        taskList1.add(expected);
        TaskList taskList2 = new TaskList(taskList1);
        assertEquals(expected, taskList2.getTask(0));
    }

    @Test
    public void get_invalidTask_exceptionThrown() throws DukeException {
        try {
            Storage storage = new Storage("duke.txt", "./src/main/data");
            TaskList taskList = new TaskList();
            Command command = Parser.parse("mark -1");
            command.execute(taskList, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input. Please provide a valid task index or check that the task exists.", e.getMessage());
        }
    }

    @Test
    public void addTask_success() {
        TaskList taskList = new TaskList();
        Task dummy1 = new Task("dummy");
        Task dummy2 = new Task("dummy");
        taskList.addTask(dummy1);
        taskList.addTask(dummy2);
        assertEquals(2, taskList.length());
    }

    @Test
    public void delete_validTask_success() {
        TaskList taskList = new TaskList();
        Task dummy1 = new Task("dummy");
        Task dummy2 = new Task("dummy");
        taskList.addTask(dummy1);
        taskList.addTask(dummy2);
        taskList.deleteTask(0);
        taskList.deleteTask(0);
        assertEquals(0, taskList.length());
    }
}
