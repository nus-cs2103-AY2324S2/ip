package atsisbot;

import atsisbot.task.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

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
        assertEquals("[T][ ] read book\n", loadedTaskList.getTask(1).toString());
    }
}
