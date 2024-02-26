package dude.Utils;

import dude.Exceptions.TaskListFullException;
import dude.Tasks.Task;
import dude.Tasks.TaskList;
import dude.Tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testSavingAndLoadingTasks() throws TaskListFullException {
        Storage storage = new Storage("data/testdata.ser");

        TaskList taskList = new TaskList();
        taskList.add_task(new Todo("test"));
        taskList.add_task(new Todo("test2"));
        taskList.add_task(new Todo("test3"));

        try {
            storage.saveTasks(taskList);
        } catch (Exception e) {
            assert (false);
        }

        TaskList loadedTaskList = null;
        try {
            loadedTaskList = storage.loadTasks();
        } catch (Exception e) {
            assert (false);
        }

        //checked if the loaded tasks from the
        ArrayList<Task> expected = taskList.getList();
        ArrayList<Task> actual = loadedTaskList.getList();

        System.out.println(expected);
        System.out.println(actual);

        assertEquals(taskList.getList(), loadedTaskList.getList());
    }
}
