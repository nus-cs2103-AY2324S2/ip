package atsisbot;

import atsisbot.task.TaskList;
import atsisbot.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testLoadData() {
        File file = new File("data/tasks.txt");
        if (file.exists()) {
            file.delete();
        }
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList = storage.loadData();
        assertEquals(0, taskList.size());
    }

    @Test
    public void testSaveList() {
        Storage storage = new Storage("data/tasks.txt");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        storage.saveList(taskList);
        TaskList loadedTaskList = storage.loadData();
        assertEquals(1, loadedTaskList.size());
        assertEquals("[T][ ] read book - low\n", loadedTaskList.getTask(1).toString());
    }
}
